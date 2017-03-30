package com.example.liwensheng.sharing.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.CommentEntity;
import com.example.liwensheng.sharing.entity.ThingEntity;
import com.example.liwensheng.sharing.entity.UserEntity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by liWensheng on 2017/3/10.
 */

public class CommentActivity extends AppCompatActivity {

    @BindView(R.id.comment_content)
    TextView mContent;
    @BindView(R.id.comment_tip)
    TextView mTip;

    @BindView(R.id.comment_button)
    TextView comment_btn;

    private ProgressDialog progressDialog;

    private ThingEntity commentThing;
    private String thingId;
    private String authorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        ButterKnife.bind(this);

        commentThing = new ThingEntity();
        Bundle bundle = this.getIntent().getExtras();
        commentThing = (ThingEntity) bundle.getSerializable("thing");
        authorId = commentThing.getAuthor().getObjectId();

        initView();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Init View.
     */
    private void initView() {
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTip.setText(s.length() + "/64");
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 发布评论
     * 发布结束后跳转回详情页面
     * 注意对象的传递
     */
    private void toPublish(){
        final String commitContent = mContent.getText().toString().trim();
        if (TextUtils.isEmpty(commitContent)) {
            Toast.makeText(CommentActivity.this, "输入框不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        final CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserEntity(BmobUser.getCurrentUser(UserEntity.class));
        commentEntity.setComments(commitContent);
        commentEntity.setThingEntity(commentThing);
        commentEntity.setRead(false);
        showPublishDialog();
        commentEntity.save(new SaveListener<String>() {
            String commentId;
            @Override
            public void done(String s, BmobException e) {
                progressDialog.dismiss();
                commentId = commentEntity.getObjectId();
                Toast.makeText(CommentActivity.this, "评论成功！", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CommentActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thing", commentThing);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     * 显示发布时的Dialog
     */
    private void showPublishDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("发布中..");
        progressDialog.setCancelable(false); // 设置点击Dialog以外的界面不消失，按返回键也不起作用
        progressDialog.show();
    }

    @OnClick(R.id.comment_button)
    public void pushComment() {
        toPublish();
    }
}

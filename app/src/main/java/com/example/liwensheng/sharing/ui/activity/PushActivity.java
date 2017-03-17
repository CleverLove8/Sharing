package com.example.liwensheng.sharing.ui.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.ThingEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.utils.ConstantUtils;
import com.example.liwensheng.sharing.utils.ImageCompressHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;



/**
 * Created by liWensheng on 2017/3/7.
 */

public class PushActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText mName;
    @BindView(R.id.et_content)
    EditText mContent;
    @BindView(R.id.tv_tip)
    TextView mTip;
    @BindView(R.id.et_location)
    EditText mLocation;
    @BindView(R.id.layout_img)
    LinearLayout mLayoutImg;
    @BindView(R.id.et_time)
    EditText mTime;
    @BindView(R.id.et_type)
    RadioGroup mRadioGroup;

    @BindView(R.id.push_button)
    TextView push_btn;

    private ProgressDialog progressDialog;
    List<String> listImg = new ArrayList<>();
    public static final int IMAGE_REQUEST_CODE = 101;


    private boolean flag = true;
    private long firstTime = 0;

    private int checkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_layout);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();
        flag = bundle.getBoolean("type");
        initView();
        chooseType();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(PushActivity.this,"再按一次返回主界面",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
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
     * 添加图片（加号图片） 点击事件
     */
    @OnClick(R.id.img_plus)
    public void onForget(View view) {
        if (listImg.size() == 1) {
            // 兼容的 Material Design AlertDialog
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage("图片最多为一张")
                    // .setCancelable(false) // 设置点击Dialog以外的界面不消失，按返回键也不起作用
                    .setPositiveButton(getString(R.string.text_ok), null)
                    .show();
            return;
        }
        toPicture();
    }

    /**
     * 跳转相册
     */
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IMAGE_REQUEST_CODE: // 相册数据
                // 做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
                if (data != null) {
                    // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
                    ContentResolver resolver = getContentResolver();
                    Uri originalUri = data.getData(); // 获得图片的uri
                    String[] proj = {MediaStore.Images.Media.DATA};
                    // 好像是android多媒体数据库的封装接口，具体的看Android文档
                    Cursor cursor = getContentResolver().query(originalUri, proj, null, null, null);
                    // 按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    // 最后根据索引值获取图片路径
                    String path = cursor.getString(column_index);
                    listImg.add(ImageCompressHelper.getInstance().compressIMG(this,path));
                    // listImg.add(path);
                    updateImg();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 更新显示图片
     */
    private void updateImg() {
        mLayoutImg.removeAllViews();
        final LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < listImg.size(); i++) {
            RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.layout_pic, null);
            ImageView imageView = (ImageView) layout.findViewById(R.id.img);
            ImageView imageClose = (ImageView) layout.findViewById(R.id.img_delete);
            imageClose.setTag(i);

            Glide.with(this).load(listImg.get(i)).into(imageView);
            imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Object obj = view.getTag();
                    int tag = Integer.parseInt(String.valueOf(obj));
                    listImg.remove(tag);
                    updateImg();
                }
            });
            mLayoutImg.addView(layout);
        }
    }

    private void chooseType() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.checkDianzi:
                        checkType = 1;
                        break;
                    case R.id.checkTushu:
                        checkType = 2;
                        break;
                    case R.id.checkYishi:
                        checkType = 3;
                        break;
                    case R.id.checkShenghuo:
                        checkType = 4;
                        break;
                    default:
                        checkType = 1;
                        break;
                }
            }
        });
    }

    /**
     * 发布
     */
    private void toPublish() {
        String commitName = mName.getText().toString().trim();
        String commitContent = mContent.getText().toString().trim();
        String commitLocation = mLocation.getText().toString().trim();
        String commitTime = mTime.getText().toString().trim();
        if (TextUtils.isEmpty(commitName) || TextUtils.isEmpty(commitContent)
                || TextUtils.isEmpty(commitLocation) || TextUtils.isEmpty(commitTime)) {
            Toast.makeText(PushActivity.this, "输入框不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        showPublishDialog();
        if (mLayoutImg == null) {
            publishWithoutFigure(commitName ,commitContent, commitLocation, commitTime, null);
//            Toast.makeText(PushActivity.this, "图片不能为空", Toast.LENGTH_LONG).show();
        } else {
            publish(commitName ,commitContent, commitLocation, commitTime);
        }
    }

    /**
     * 发表带图片的新鲜事
     */
    private void publish(final String commitName ,final String commitContent,
                         final String commitLocation, final String commitTime) {

        // 批量上传图片到Bomb
        BmobFile.uploadBatch((String[])listImg.toArray(new String[listImg.size()]), new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                // 1.files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
                // 2.urls-上传文件的完整url地址
                if(urls.size()==listImg.size()){ // 如果数量相等，则代表文件全部上传完成
                    // do something

                    publishWithoutFigure(commitName ,commitContent, commitLocation, commitTime, files);
                }
            }
            @Override
            public void onError(int statuscode, String errormsg) {
                progressDialog.dismiss();
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total,int totalPercent) {
                // 1.curIndex--表示当前第几个文件正在上传
                // 2.curPercent--表示当前上传文件的进度值（百分比）
                // 3.total--表示总的上传文件数
                // 4.totalPercent--表示总的上传进度（百分比）
            }
        });
    }


    private void publishWithoutFigure(final String commitName ,final String commitContent,
                                      final String commitLocation, final String commitTime, final List<BmobFile> files) {
        if (files.size() == 0) {
            Toast.makeText(PushActivity.this, "图片不能为空", Toast.LENGTH_LONG).show();
        }
        UserEntity userEntity = BmobUser.getCurrentUser(UserEntity.class);
        ThingEntity thingEntity = new ThingEntity();
        thingEntity.setAuthor(userEntity);
        thingEntity.setName(commitName);
        thingEntity.setContent(commitContent);
        thingEntity.setImg(files.get(0));
        thingEntity.setSend(flag);
        thingEntity.setLocation(commitLocation);
        thingEntity.setTime(commitTime);
        thingEntity.setType(checkType);
        thingEntity.setOver(false);
        thingEntity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    progressDialog.dismiss();
                    // 发布成功
                    Toast.makeText(PushActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                    PushActivity.this.setResult(ConstantUtils.RESULT_UPDATE_INFO, new Intent());
                    startActivity(new Intent(PushActivity.this, MainActivity.class));
                }
                else {
//                    Toast.makeText(PushActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }

    @OnClick(R.id.push_button)
    public void push() {
        toPublish();


    }

    /**
     * 显示发布时的Dialog
     */
    private void showPublishDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("发布中。。。");
        progressDialog.setCancelable(false); // 设置点击Dialog以外的界面不消失，按返回键也不起作用
        progressDialog.show();
    }

}

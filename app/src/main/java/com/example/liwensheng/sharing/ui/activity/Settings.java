package com.example.liwensheng.sharing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.UserEntity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by liWensheng on 2017/3/28.
 */

public class Settings extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progressNum)
    TextView progressNum;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.birth)
    EditText birth;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.wechat)
    EditText wechat;
    @BindView(R.id.habbit)
    EditText habbit;

    @BindView(R.id.saveBtn)
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        clickSave();
        loadInfo();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String[]info = (String[])msg.obj;
                    name.setText(info[0]);
                    sex.setText(info[1]);
                    birth.setText(info[2]);
                    phone.setText(info[3]);
                    wechat.setText(info[4]);
                    break;
            }
        }
    };

    private void loadInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] info = new String[6];
                UserEntity user = BmobUser.getCurrentUser(UserEntity.class);
                info[0] = user.getUser_name();
                info[1] = user.getSex();
                info[2] = user.getBirth();
                info[3] = user.getUser_phoneNum();
                info[4] = user.getUser_qq();

                Message message = new Message();
                message.what = 1;
                message.obj = info;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void clickSave() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_name = name.getText().toString().trim();
                String User_sex = sex.getText().toString().trim();
                String User_birth = birth.getText().toString().trim();
                String User_phone = phone.getText().toString().trim();
                String User_wechat = wechat.getText().toString().trim();
                String User_habbit = habbit.getText().toString().trim();

                if (TextUtils.isEmpty(User_name)) {
                    Toast.makeText(Settings.this, "用户名不可为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserEntity userentity =  BmobUser.getCurrentUser(UserEntity.class);
                    BmobUser user = BmobUser.getCurrentUser(UserEntity.class);
                    userentity.setUser_name(User_name);
                    userentity.setSex(User_sex);
                    userentity.setBirth(User_birth);
                    userentity.setUser_phoneNum(User_phone);
                    userentity.setUser_qq(User_wechat);
                    user.setUsername(User_name);
                    user.setMobilePhoneNumber(User_phone);
                    user.setMobilePhoneNumberVerified(true);

                    userentity.setObjectId(user.getObjectId());

                    userentity.update(user.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e != null)
                                e.printStackTrace();
                            Toast.makeText(Settings.this, "保存成功！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Settings.this, MainActivity.class));
                            finish();
                        }
                    });
                }
            }
        });
    }

    @OnClick(R.id.quit)
    public void quitLogin() {
        startActivity(new Intent(Settings.this, LoginPage.class));
        finish();
    }
}

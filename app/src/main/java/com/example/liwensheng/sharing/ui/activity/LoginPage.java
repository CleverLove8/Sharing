package com.example.liwensheng.sharing.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.utils.SPUtils;
import com.example.liwensheng.sharing.view.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by liwensheng on 2017/3/4.
 */
public class LoginPage extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    //登录按钮
    @BindView(R.id.bt_go)
    Button btGo;
//    @BindView(R.id.keep_password)
//    CheckBox keepPassword;

    //注册按钮
    @BindView(R.id.fab)
    TextView fab;
    @BindView(R.id.forget)
    TextView forget;

    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 保存状态
        SPUtils.putBoolean(this, "keeppass", true);
        // 是否记住密码
//        if (keepPassword.isChecked()) {
//            // 记住用户名和密码
//            SPUtils.putString(this, "phone", etUsername.getText().toString().trim());
//            SPUtils.putString(this, "password", etPassword.getText().toString().trim());
//        } else {
//            SPUtils.remove(this, "phone");
//            SPUtils.remove(this, "password");
//        }
        //默认记住密码
        SPUtils.putString(this, "phone", etUsername.getText().toString().trim());
        SPUtils.putString(this, "password", etPassword.getText().toString().trim());
    }

    private void initView() {
        boolean isKeep = SPUtils.getBoolean(this, "keeppass", false);
//        keepPassword.setChecked(isKeep);
        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loading, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        dialog.setCancelable(false);
        if (isKeep) {
            String phone = SPUtils.getString(this, "phone", "");
            String password = SPUtils.getString(this, "password", "");
            etUsername.setText(phone);
            etPassword.setText(password);
        }
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                    startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.bt_go:
                String user_name = etUsername.getText().toString().trim();
                String user_passworld = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_passworld)) {
                    Toast.makeText(this, "输入框不能为空！", Toast.LENGTH_LONG).show();
                }
                else {
                    dialog.show();
                    boolean flag = true;
                    BmobUser.loginByAccount(user_name, user_passworld, new LogInListener<UserEntity>() {
                        @Override
                        public void done(UserEntity user, BmobException e) {
                            dialog.dismiss();
                            if (user != null) {
                                initClient();
                                Toast.makeText(LoginPage.this, "登录成功！", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginPage.this, MainActivity.class));
                                finish();
                                dialog.dismiss();
                            }
                            else {
                                Toast.makeText(LoginPage.this, "登录失败："+e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                break;
        }
    }

    private void initClient() {
        LCChatKit.getInstance().open(BmobUser.getCurrentUser(UserEntity.class).getUsername()
                , new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient avimClient, AVIMException e) {
                        if (e != null)
                            e.printStackTrace();
                    }
                });
    }
}

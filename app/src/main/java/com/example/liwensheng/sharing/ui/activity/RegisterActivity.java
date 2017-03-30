package com.example.liwensheng.sharing.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by liwensheng on 2017/3/4.
 */
public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText phoneNum;
    @BindView(R.id.et_code)
    EditText yanzhengma;
    @BindView(R.id.tv_code)
    TextView mBtnCode;
    @BindView(R.id.et_password)
    EditText mima;

    private MyCountTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
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

    @OnClick(R.id.tv_code)
    public void getYanZheng() {
        String phonenum = phoneNum.getText().toString().trim();
        if (phonenum.isEmpty()) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        timer = new MyCountTimer(60000, 1000);
        timer.start();
        BmobSMS.requestSMSCode(phonenum, "default", new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "验证码发送成功!", Toast.LENGTH_SHORT).show();
                }
                else if (e != null) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.bt_go)
    public void Register() {
        final String phone = phoneNum.getText().toString().trim();
        String code = yanzhengma.getText().toString().trim();
        String password = mima.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "输入不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        UserEntity user = new UserEntity();
        user.setMobilePhoneNumberVerified(true);
        user.setUser_phoneNum(phone);
        user.setUser_name(phone);
        user.setMobilePhoneNumber(phone);
        user.setPassword(password);
        user.signOrLogin(code, new SaveListener<UserEntity>() {
            @Override
            public void done(UserEntity user,BmobException e) {
                if (e == null) {
                    initClient(phone);
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
            }
        });
//        BmobUser.signOrLoginByMobilePhone(phone, password, new LogInListener<UserEntity>() {
//            @Override
//            public void done(UserEntity userentity, BmobException e) {
//                if (e == null) {
//
//                }
//                else {
//                    e.printStackTrace();
//                    return;
//                }
//            }
//        });
    }

    /**
     * 倒计时器
     * 停止倒计时器用 timer.cancel();
     */
    class MyCountTimer extends CountDownTimer {
        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mBtnCode.setText((millisUntilFinished / 1000) +"秒后重发");
        }
        @Override
        public void onFinish() {
            mBtnCode.setText("重新发送验证码");
        }
    }

    private void initClient(String name) {
        LCChatKit.getInstance().open(name
                , new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient avimClient, AVIMException e) {
                        if (e != null)
                            e.printStackTrace();
                    }
                });
    }
}

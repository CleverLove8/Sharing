package com.example.liwensheng.sharing.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by liWensheng on 2017/3/31.
 */

public class FindPassword extends AppCompatActivity{

    @BindView(R.id.et_phone)
    EditText mPhone;
    @BindView(R.id.et_code) EditText mCode;
    @BindView(R.id.tv_code)
    TextView mBtnCode;

    @BindView(R.id.et_new_pass) EditText mNewPass;
    @BindView(R.id.et_new_password) EditText mNewPassword;
    @BindView(R.id.btn_reset_password)

    private MyCountTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
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


    /**
     * 发送验证码
     * @param view
     */
    @OnClick(R.id.tv_code)
    public void onCode(View view) {
        String phoneNum = mPhone.getText().toString().trim();
        if (phoneNum.isEmpty()) {
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        timer = new MyCountTimer(60000, 1000);
        timer.start();
        BmobSMS.requestSMSCode(phoneNum,"default", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId,BmobException e) {
                if (e==null) {//验证码发送成功
                    Toast.makeText(FindPassword.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });
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

    /**
     * 重置密码
     * @param view
     */
    @OnClick(R.id.btn_reset_password)
    public void onResetPassword(View view) {
        String code = mCode.getText().toString().trim();
        String newPass = mNewPass.getText().toString().trim();
        String newPassword = mNewPassword.getText().toString().trim();
        if(!TextUtils.isEmpty(newPass) & !TextUtils.isEmpty(newPassword)){
            if(newPass.equals(newPassword)) {
                BmobUser.resetPasswordBySMSCode(code,newPassword, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(FindPassword.this, "重置密码成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(FindPassword.this, "重置失败："+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else {
                Toast.makeText(this, "两次输入不一致", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}

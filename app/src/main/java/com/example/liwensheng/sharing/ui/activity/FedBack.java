package com.example.liwensheng.sharing.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liWensheng on 2017/3/28.
 */

public class FedBack extends AppCompatActivity {

    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    private boolean[] flag;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fedback);
        ButterKnife.bind(this);

        flag = new boolean[5];
        for (int i = 0; i < 5; i++) {
            flag[i] = false;
        }
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

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void clickReason(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                flag[1] = !flag[1];
                if (flag[1]) {
                    btn1.setBackgroundResource(R.drawable.btn_green);
                }
                else {
                    btn1.setBackgroundResource(R.drawable.btn_gray);
                }
                break;
            case R.id.btn2:
                flag[2] = !flag[2];
                if (flag[2]) {
                    btn2.setBackgroundResource(R.drawable.btn_green);
                }
                else {
                    btn2.setBackgroundResource(R.drawable.btn_gray);
                }
                break;
            case R.id.btn3:
                flag[3] = !flag[3];
                if (flag[3]) {
                    btn3.setBackgroundResource(R.drawable.btn_green);
                }
                else {
                    btn3.setBackgroundResource(R.drawable.btn_gray);
                }
                break;
            case R.id.btn4:
                flag[4] = !flag[4];
                if (flag[4]) {
                    btn4.setBackgroundResource(R.drawable.btn_green);
                }
                else {
                    btn4.setBackgroundResource(R.drawable.btn_gray);
                }
                break;
        }
    }

    @OnClick(R.id.confirm)
    public void postReason() {
        Toast.makeText(this, "提交成功！", Toast.LENGTH_SHORT).show();
        finish();
    }
}

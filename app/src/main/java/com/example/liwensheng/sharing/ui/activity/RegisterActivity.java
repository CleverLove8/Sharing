package com.example.liwensheng.sharing.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.UserEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by liwensheng on 2017/3/4.
 */
public class RegisterActivity extends AppCompatActivity {

//    @BindView(R.id.fab)
//    FloatingActionButton fab;
//    @BindView(R.id.cv_add)
//    CardView cvAdd;
//
//    @BindView(R.id.et_username)
//    EditText reg_name;
//    @BindView(R.id.et_password)
//    EditText reg_password;
//    @BindView(R.id.et_repeatpassword)
//    EditText reg_repeat;
//    @BindView(R.id.number)
//    EditText reg_phone;
//    @BindView(R.id.weichat)
//    EditText reg_qq;
//    @BindView(R.id.yanzgengma)
//    EditText reg_yanzheng;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        ButterKnife.bind(this);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ShowEnterAnimation();
//        }
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                animateRevealClose();
//            }
//        });
//    }
//
//    private void ShowEnterAnimation() {
//        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
//        getWindow().setSharedElementEnterTransition(transition);
//
//        transition.addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//                cvAdd.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//                transition.removeListener(this);
//                animateRevealShow();
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//
//
//        });
//    }
//
//    public void animateRevealShow() {
//        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
//        mAnimator.setDuration(500);
//        mAnimator.setInterpolator(new AccelerateInterpolator());
//        mAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                cvAdd.setVisibility(View.VISIBLE);
//                super.onAnimationStart(animation);
//            }
//        });
//        mAnimator.start();
//    }
//
//    public void animateRevealClose() {
//        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
//        mAnimator.setDuration(500);
//        mAnimator.setInterpolator(new AccelerateInterpolator());
//        mAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                cvAdd.setVisibility(View.INVISIBLE);
//                super.onAnimationEnd(animation);
//                fab.setImageResource(R.drawable.plus);
//                RegisterActivity.super.onBackPressed();
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
//        });
//        mAnimator.start();
//    }
//    @Override
//    public void onBackPressed() {
//        animateRevealClose();
//    }
//
//    @OnClick({R.id.bt_go, R.id.fab})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_go:
//                String name = reg_name.getText().toString().trim();
//                String password = reg_password.getText().toString().trim();
//                String repeat = reg_repeat.getText().toString().trim();
//                String phone = reg_phone.getText().toString().trim();
//                String qq = reg_qq.getText().toString().trim();
//                String yanzhen = reg_yanzheng.getText().toString().trim();
//
//                if (TextUtils.isEmpty(name)) {
//                    Toast.makeText(RegisterActivity.this, "用户名不能为空！", Toast.LENGTH_LONG).show();
//                }
//                else if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
//                }
//                else if (!password.equals(repeat)) {
//                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致！", Toast.LENGTH_LONG).show();
//                }
//                else if (TextUtils.isEmpty(phone)) {
//                    Toast.makeText(RegisterActivity.this, "电话号码不能为空！", Toast.LENGTH_LONG).show();
//                }
//                else if (TextUtils.isEmpty(qq)) {
//                    Toast.makeText(RegisterActivity.this, "社交账号不能为空！", Toast.LENGTH_LONG).show();
//                }
//                else  {
//                    UserEntity userEntity = new UserEntity();
//                    userEntity.setUsername(name);
//                    userEntity.setPassword(password);
//                    userEntity.setMobilePhoneNumber(phone);
//                    userEntity.setUser_name(name);
//                    userEntity.setUser_phoneNum(phone);
//                    userEntity.setUser_credit(0);
//                    userEntity.setUser_Position("这个人很神秘，还没有设置地址");
//                    userEntity.setUser_qq(qq);
//
////                userEntity.signOrLogin(yanzhen, new SaveListener<UserEntity>() {
////                    @Override
////                    public void done(UserEntity user,BmobException e) {
////                        if(e==null){
////                            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
////                            finish();
////                        }else{
////                            Toast.makeText(RegisterActivity.this, "注册失败:" + e.toString(), Toast.LENGTH_LONG).show();
////                        }
////                    }
////
////                });
//                    userEntity.signUp(new SaveListener<UserEntity>() {
//                        @Override
//                        public void done(UserEntity s, BmobException e) {
//                            if(e == null) {
//
//                            } else {
//                                Toast.makeText(RegisterActivity.this, "注册失败:" + e.toString(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                }
//
//
//        }
//    }
}

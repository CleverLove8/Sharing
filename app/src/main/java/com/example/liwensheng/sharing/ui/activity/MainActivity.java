package com.example.liwensheng.sharing.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.frame.AboutMe;
import com.example.liwensheng.sharing.ui.frame.CirclePageFragment;
import com.example.liwensheng.sharing.ui.frame.MainPageFragment;
import com.example.liwensheng.sharing.ui.frame.MessagesPageFragment;
import com.example.liwensheng.sharing.utils.IMMLeaks;
import com.example.liwensheng.sharing.utils.PopupMenuUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;
    private MainPageFragment mainPageFragment;
    private CirclePageFragment circlePageFragment;
    private MessagesPageFragment messagesPageFragment;
    private AboutMe aboutMe;

    @BindView(R.id.main_radioGroup) RadioGroup radioGroup;

//    @BindView(R.id.iv_img)
//    ImageView addButton;
    @BindView(R.id.rl_click)
    FloatingActionButton rlClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavBottom();
        initeFragment();

        //连接服务器
        UserEntity user = BmobUser.getCurrentUser(UserEntity.class);

        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuUtils.getInstance()._show(MainActivity.this, rlClick);
//                startActivityForResult(new Intent(MainActivity.this, PushActivity.class), ConstantUtils.RESULT_UPDATE_INFO);
//                finish();
            }
        });
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
     * inite nav bottom
     * */
    private void initNavBottom() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mainpage_radio:
                        // 主页
                        switchFragment(mainPageFragment);
                        break;
                    case R.id.circlrpage_radio:
                        // 圈子
                        switchFragment(circlePageFragment);
                        break;
                    case R.id.mespage_radio:
                        //消息
                        switchFragment(messagesPageFragment);
                        break;
                    case  R.id.personpage_radio:
                        //个人中心
                        switchFragment(aboutMe);
                        break;
                    default:
                        switchFragment(mainPageFragment);
                        break;
                }
            }
        });
        radioGroup.check(R.id.mainpage_radio);
    }

    /**
     * inite fragment
     * start with mainPage
     * */
    private void initeFragment() {
        mainPageFragment = new MainPageFragment();
        circlePageFragment = new CirclePageFragment();
        messagesPageFragment = new MessagesPageFragment();
        aboutMe = new AboutMe();
        setDefaultFragment(mainPageFragment);
    }

    /**
     * Set default fragment.
     *  @param fragment
     */
    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).addToBackStack(null).commit();
        currentFragment = fragment;
    }

    /**
     * Switch fragment.
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        if (fragment != currentFragment) {
            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(currentFragment)
                        .add(R.id.frame_layout, fragment).addToBackStack(null).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(currentFragment)
                        .show(fragment).addToBackStack(null).commit();
            }
            currentFragment = fragment;
        }
    }

}

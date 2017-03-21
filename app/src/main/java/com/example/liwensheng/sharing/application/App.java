package com.example.liwensheng.sharing.application;

import android.app.Application;

import com.example.liwensheng.sharing.utils.ConfigUtils;
import com.example.liwensheng.sharing.utils.ConstantUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by liWensheng on 2017/3/5.
 */

public class App extends Application {
    public static App app;

    private static App INSTANCE;
    public static App INSTANCE(){
        return INSTANCE;
    }
    private void setInstance(App app) {
        setBmobIMApplication(app);
    }
    private static void setBmobIMApplication(App a) {
        App.INSTANCE = a;
    }

    private final String APP_ID = "SLNGVyvvkAapbSUaWQ8XYGQ6-gzGzoHsz";
    private final String APP_KEY = "OsbUJu5jq9QHpEYnOLQPyG4Q";

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
        setInstance(this);

        // 读取配置文件
        ConfigUtils.getInstance().readConfig();

        // 初始化Bmob
        if (!ConstantUtils.BMOB_APP_ID.isEmpty()) {
            BmobConfig config =new BmobConfig.Builder(this)
                    .setApplicationId(ConstantUtils.BMOB_APP_ID)// 设置appkey
                    .setConnectTimeout(30)// 请求超时时间（单位为秒）：默认15s
                    .setUploadBlockSize(1024*1024)// 文件分片上传时每片的大小（单位字节），默认512*1024
                    .setFileExpiration(2500)// 文件的过期时间(单位为秒)：默认1800s
                    .build();
            Bmob.initialize(config);
        }

        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
    }
}

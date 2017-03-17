package com.example.liwensheng.sharing.application;

import android.app.Application;

import com.example.liwensheng.sharing.base.UniversalImageLoader;
import com.example.liwensheng.sharing.handler.DemoMessageHandler;
import com.example.liwensheng.sharing.utils.ConfigUtils;
import com.example.liwensheng.sharing.utils.ConstantUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

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

        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            //im初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new DemoMessageHandler(this));
        }
        //uil初始化
        UniversalImageLoader.initImageLoader(this);
    }

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

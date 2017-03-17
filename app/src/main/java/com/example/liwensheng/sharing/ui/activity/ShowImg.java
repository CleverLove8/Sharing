package com.example.liwensheng.sharing.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.ThingEntity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by liWensheng on 2017/3/9.
 */

public class ShowImg extends AppCompatActivity {

    @BindView(R.id.showBackground)
    ImageView imageView;

    private final int SUCCESS = 0;
    private final int FAIL = 1;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showimg_layout);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();
        url = bundle.getString("url");
        loadImg();
    }

    @OnClick(R.id.showBackground)
    public void closeImg(View view) {
        finish();
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    imageView.setImageBitmap(bitmap); //设置imageView显示的图片
                    break;
                case FAIL:
                    Toast.makeText(ShowImg.this, "图片加载失败", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    public void loadImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getImageFromNet(url);
                if (bitmap != null) {
                    Message msg = new Message();
                    msg.what = SUCCESS;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = FAIL;
                    handler.sendMessage(msg);
                }

            }
        }).start();


    }

    private Bitmap getImageFromNet(String url) {
        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("GET"); //设置请求方法
            conn.setConnectTimeout(10000); //设置连接服务器超时时间
            conn.setReadTimeout(5000);  //设置读取数据超时时间

            conn.connect(); //开始连接

            int responseCode = conn.getResponseCode(); //得到服务器的响应码
            if (responseCode == 200) {
                //访问成功
                InputStream is = conn.getInputStream(); //获得服务器返回的流数据
                Bitmap bitmap = BitmapFactory.decodeStream(is); //根据流数据 创建一个bitmap对象
                return bitmap;

            } else {
                //访问失败
                Log.d("lyf--", "访问失败===responseCode：" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

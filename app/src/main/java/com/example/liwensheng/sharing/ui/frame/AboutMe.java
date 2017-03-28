package com.example.liwensheng.sharing.ui.frame;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.entity.GoodEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.activity.FedBack;
import com.example.liwensheng.sharing.ui.activity.HistoryActivity;
import com.example.liwensheng.sharing.utils.ConstantUtils;
import com.example.liwensheng.sharing.utils.SPUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liWensheng on 2017/2/28.
 */

public class AboutMe extends BaseFragment {

    @BindView(R.id.user_icon)
    CircleImageView User_icon;
    @BindView(R.id.user_name)
    TextView User_name;
    @BindView(R.id.user_phoneNum)
    TextView User_phoneNum;
    @BindView(R.id.user_qq)
    TextView User_qq;
    @BindView(R.id.user_credit)
    TextView User_credit;
    @BindView(R.id.good_number)
    TextView User_good;

    @BindView(R.id.user_history)
    LinearLayout User_history;
    @BindView(R.id.user_circle)
    LinearLayout User_circle;
    @BindView(R.id.user_setting)
    LinearLayout User_setting;
    @BindView(R.id.user_yijian)
    LinearLayout User_yijian;
    @BindView(R.id.user_aboutUs)
    LinearLayout User_abouUs;

    private android.support.v7.app.AlertDialog photoDialog;

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;
    private static final int REQUEST_BLUETOOTH_PERMISSION = 10;
    private File tempFile = null;

    private int goodNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("我");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.aboutme_layout, null);
        ButterKnife.bind(this, view);
        onClickListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(UserEntity.getCurrentUser() != null) {
            initView();
        }
    }
    /**
     * 初始化View
     * */
    public void initView() {
        goodNum = 0;
        if (BmobUser.getCurrentUser()!= null) {
            UserEntity userEntity = BmobUser.getCurrentUser(UserEntity.class);
            if (userEntity.getUser_icon()!=null) {
                Glide.with(getActivity()).load(userEntity.getUser_icon().getFileUrl()).into(User_icon);
            }
            User_name.setText(userEntity.getUser_name());
            User_phoneNum.setText(userEntity.getUser_phoneNum());
            User_qq.setText(userEntity.getUser_qq());
            User_credit.setText(userEntity.getUser_credit()+"");
            User_phoneNum.setText(userEntity.getUser_phoneNum());
            getNumGood();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int num = (int)msg.obj;
                    User_good.setText(num+"");
                    break;
            }
        }
    };

    private void getNumGood() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<GoodEntity> query = new BmobQuery<>();
                query.include("thingEntity");
                final String name = BmobUser.getCurrentUser(UserEntity.class).getUser_name();
                query.findObjects(new FindListener<GoodEntity>() {
                    @Override
                    public void done(List<GoodEntity> list, BmobException e) {
                        if (list != null)
                            for (GoodEntity goodEntity:list) {
                                if (goodEntity.getThingEntity().getAuthor().getUser_name()
                                        .equals(name)){
                                    goodNum++;
                                }
                            }
                    }
                });
                Message message = new Message();
                message.obj = goodNum;
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 登录
            case ConstantUtils.RESULT_UPDATE_INFO:
                if (UserEntity.getCurrentUser()!=null) {
                    initView();
                }
                break;
            // 退出登录
            case ConstantUtils.RESULT_UPDATE_EXIT_INFO:
                if (UserEntity.getCurrentUser()==null) {
                    //设置view
                }
                break;
            case IMAGE_REQUEST_CODE: // 相册数据
                if (data != null) {
                    startPhotoZoom(data.getData());
                }
                break;
            case RESULT_REQUEST_CODE: // 有可能点击舍弃
                if (data != null) {
                    // 拿到图片设置, 然后需要删除tempFile
                    setImageToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtils.putImage(getActivity(), "profile_image", User_icon); // 保存
    }


    /**
     * 点击头像更换头像
     * @param view
     */
    @OnClick(R.id.user_icon)
    public void onProfileImage(View view) {
        if (BmobUser.getCurrentUser() == null) {
            Toast.makeText(getContext(), "你还没有登录！", Toast.LENGTH_LONG).show();
        } else {
            toPicture();
        }
    }

    /**
     * 设置头像跳转，图库或相机
     * */
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }


    /**
     * 裁剪
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // 裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        // 发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 设置头像并上传服务器
     * @param data
     */
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            final Bitmap bitmap = bundle.getParcelable("data");
            final BmobFile bmobFile = new BmobFile(bitmapToFile(bitmap));

            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null) {
                        // 此时上传成功
                        UserEntity userEntity = new UserEntity();
                        userEntity.setUser_icon(bmobFile);// 获取文件并赋值给实体类
                        BmobUser bmobUser = BmobUser.getCurrentUser();
                        userEntity.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    User_icon.setImageBitmap(bitmap);
                                    Toast.makeText(getContext(), "头像修改成功！", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "头像修改失败！", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "头像修改失败！", Toast.LENGTH_LONG).show();
                    }
                    // 既然已经设置了图片，我们原先的就应该删除
                    if (tempFile != null) {
                        tempFile.delete();
                    }
                }
                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }
    }

    /**
     * Bitmap转File
     */
    public File bitmapToFile(Bitmap bitmap) {
        tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempFile));
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)) {
                bos.flush();
                bos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
     * 点击查看历史
     * */
    private void onClickListener() {
        User_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryActivity.class));
            }
        });

        User_yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FedBack.class));
            }
        });
    }
}

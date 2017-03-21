package com.example.liwensheng.sharing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.CollectEntity;
import com.example.liwensheng.sharing.entity.Friend;
import com.example.liwensheng.sharing.entity.GoodEntity;
import com.example.liwensheng.sharing.entity.ThingEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.frame.DetailpageFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by liWensheng on 2017/3/9.
 */

public class DetailActivity extends AppCompatActivity {

    private Fragment fragment;
    private DetailpageFragment detailpageFragment;

    String thingId;
    ThingEntity detailThing;


    @BindView(R.id.good_icon)
    ImageView good_icon;

    private boolean flag;
    private String collectId;
    private boolean isGood;
    private String goodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        ButterKnife.bind(this);

        detailThing = new ThingEntity();
        Bundle bundle = this.getIntent().getExtras();
        detailThing = (ThingEntity) bundle.getSerializable("thing");
        thingId = detailThing.getObjectId();

        detailpageFragment = new DetailpageFragment();
        setDefaultFragment(detailpageFragment);

//        initCollect();
        setGoodState();
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
     * Set default fragment.
     *  @param fragment
     */
    private void setDefaultFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("thing", detailThing);
        fragment.setArguments(bundle);

//        Fragment chatFrame = new LCIMConversationListFragment();
        transaction.replace(R.id.detail_frame, fragment).addToBackStack(null).commit();
    }

    /**
     * 点击联系物主
     * */
    @OnClick(R.id.contact_button)
    public void ContactClick() {
        Friend newFriend = new Friend();
        newFriend.setUser(BmobUser.getCurrentUser(UserEntity.class));
        newFriend.setFriendUser(detailThing.getAuthor());
        newFriend.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });
        UserEntity user = BmobUser.getCurrentUser(UserEntity.class);
        BmobIMUserInfo info = new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getUser_icon().getFileUrl());
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
        Bundle bundle = new Bundle();
        bundle.putSerializable("c", c);
        Intent intent = new Intent(DetailActivity.this, ChatActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 点赞
     * */
    @OnClick(R.id.googBtn)
    public void GoodClick() {
        //查询当前用户是否点赞该消息
        if (isGood) {
            deleteGood();

        }
        else {
            addGood();
        }
    }

    private void setGoodState() {
        isGood = false;
        BmobQuery<GoodEntity> query = new BmobQuery<>();
        query.addWhereEqualTo("thingEntity", detailThing);
        query.addWhereEqualTo("userEntity", BmobUser.getCurrentUser(UserEntity.class));
        query.include("userEntity");
        query.findObjects(new FindListener<GoodEntity>() {
            @Override
            public void done(List<GoodEntity> list, BmobException e) {
//                goodNum = list.size();
                //判断当前用户是否点赞
                if (list.size() != 0) {
                    isGood = true;
                    goodId = list.get(0).getObjectId();
                    good_icon.setBackgroundResource(R.mipmap.good_2);
                }
            }
        });
    }


    private void addGood() {
        final GoodEntity goodEntity = new GoodEntity();
        goodEntity.setUserEntity(BmobUser.getCurrentUser(UserEntity.class));
        goodEntity.setThingEntity(detailThing);
        goodEntity.setRead(false);
        goodEntity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                goodId = goodEntity.getObjectId();
                isGood = true;
                good_icon.setBackgroundResource(R.mipmap.good_2);
            }
        });
    }

    private void deleteGood() {
        final GoodEntity goodEntity = new GoodEntity();
        goodEntity.delete(goodId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                isGood = false;
                good_icon.setBackgroundResource(R.mipmap.good);
            }
        });
    }

    /**
     * 点击评论
     * */
    @OnClick(R.id.commentBtn)
    public void CommentClick() {
        Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("thing", detailThing);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

//    private void initCollect() {
//        isCollected();
////        changeColor();
//    }
//
//    private void isCollected() {
//        BmobQuery<CollectEntity> query = new BmobQuery<>();
//        query.addWhereEqualTo("thingEntity", detailThing);
//        query.addWhereEqualTo("userEntity", BmobUser.getCurrentUser(UserEntity.class));
//        query.include("userEntity,thingEntity");
//        query.findObjects(new FindListener<CollectEntity>() {
//            @Override
//            public void done(List<CollectEntity> list, BmobException e) {
//                if (list == null) {
//                    flag = false;
//                    return;
//                }
//                if (list.size() == 0) {
//                    flag = false;
//                    return;
//                }
//                else {
//                    flag = true;
//                    collect.setImageResource(R.mipmap.shoucang2);
//                    return;
//                }
//            }
//        });
//    }
//
//    private void changeColor() {
//        if (flag) {
//            collect.setImageResource(R.mipmap.shoucang2);
//            collectThing();
//        }
//        else {
//            collect.setImageResource(R.mipmap.shoucang);
//            deleteCollect();
//        }
//    }

    private void collectThing() {
        if (flag) {
            final CollectEntity collectEntity = new CollectEntity();
            collectEntity.setThingEntity(detailThing);
            collectEntity.setUserEntity(BmobUser.getCurrentUser(UserEntity.class));
            collectEntity.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    collectId = collectEntity.getObjectId();
//                    Toast.makeText(DetailActivity.this, "收藏成功！", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void deleteCollect() {
        CollectEntity collectEntity = new CollectEntity();
        collectEntity.delete(collectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    private void changeCollectState() {

    }

    private void contactUser() {

    }


}

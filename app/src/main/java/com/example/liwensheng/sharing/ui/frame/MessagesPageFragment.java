package com.example.liwensheng.sharing.ui.frame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.entity.CommentEntity;
import com.example.liwensheng.sharing.entity.GoodEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.activity.MesComment;
import com.example.liwensheng.sharing.ui.activity.MesGood;
import com.example.liwensheng.sharing.view.RefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;

/**
 * Created by liWensheng on 2017/2/28.
 * 用于添加消息
 */

public class MessagesPageFragment extends BaseFragment {

    @BindView(R.id.mes_dianzan)
    LinearLayout dianzan;
    @BindView(R.id.mes_pinglun)
    LinearLayout pinglun;
    @BindView(R.id.num_dianzan)
    TextView num_dianzan;
    @BindView(R.id.num_pinglun)
    TextView num_pinglun;


    private  int type_1, type_2, update_1, update_2;

    private boolean loading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("消息");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.messagepage_layout, null);
        ButterKnife.bind(this, view);

        getData();
        initMesFrame();
        return view;
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        initRefreshLayout();
//    }

    @OnClick({R.id.mes_dianzan, R.id.mes_pinglun})
    public void cheskMes(View view) {
        switch (view.getId()) {
            case R.id.mes_dianzan:
                Intent intent = new Intent(getContext(), MesGood.class);
                startActivity(intent);
                break;
            case R.id.mes_pinglun:
                Intent intent2 = new Intent(getContext(), MesComment.class);
                startActivity(intent2);
                break;
        }

    }

    private void initMesFrame() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

//        Fragment chatFrame = new LCIMConversationListFragment();
        transaction.replace(R.id.contact_frame, new LCIMConversationListFragment()).addToBackStack(null).commit();
    }

    /**
     * 获取各类消息数目
     * */
    private void getData() {
        type_1 = 0;
        type_2 = 0;

        //点赞数目
        BmobQuery<GoodEntity> goodQuery = new BmobQuery<>();
        goodQuery.addWhereEqualTo("read", false);
        goodQuery.addWhereEqualTo("userEntity", BmobUser.getCurrentUser(UserEntity.class));
        goodQuery.findObjects(new FindListener<GoodEntity>() {
            @Override
            public void done(List<GoodEntity> list, BmobException e) {
                if (list == null){
                    type_1 = 0;
                }
                else {
                    type_1 = list.size();
                }
                num_dianzan.setText(type_1 + "");
            }
        });

        //评论数目
        BmobQuery<CommentEntity> commentQuery = new BmobQuery<>();
        commentQuery.addWhereEqualTo("read", false);
        commentQuery.addWhereEqualTo("userEntity", BmobUser.getCurrentUser(UserEntity.class));
        commentQuery.findObjects(new FindListener<CommentEntity>() {
            @Override
            public void done(List<CommentEntity> list, BmobException e) {
                if (list == null) {
                    type_2 = 0;
                }
                else {
                    type_2 = list.size();
                }
                num_pinglun.setText(type_2+"");
            }
        });
    }

}

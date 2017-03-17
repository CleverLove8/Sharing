package com.example.liwensheng.sharing.ui.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.adapter.ContactAdapter;
import com.example.liwensheng.sharing.adapter.IMutlipleItem;
import com.example.liwensheng.sharing.adapter.OnRecyclerViewListener;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.entity.Friend;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.model.UserModel;
import com.example.liwensheng.sharing.ui.activity.ChatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liWensheng on 2017/3/17.
 */

public class ContactFragment extends BaseFragment {

    @BindView(R.id.rc_view)
    RecyclerView rc_view;
    @BindView(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    ContactAdapter adapter;
    LinearLayoutManager layoutManager;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_conversation, container, false);
        ButterKnife.bind(this, rootView);

        IMutlipleItem<Friend> mutlipleItem = new IMutlipleItem<Friend>() {

            @Override
            public int getItemViewType(int postion, Friend friend) {
                return ContactAdapter.TYPE_ITEM;
            }

            @Override
            public int getItemLayoutId(int viewtype) {
                return R.layout.item_contact;
            }

            @Override
            public int getItemCount(List<Friend> list) {
                return list.size()+1;
            }
        };
        adapter = new ContactAdapter(getActivity(),mutlipleItem,null);
        rc_view.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        rc_view.setLayoutManager(layoutManager);
        sw_refresh.setEnabled(true);
        setListener();


        return rootView;
    }

    private void setListener(){
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                sw_refresh.setRefreshing(true);
                query();
            }
        });
        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Friend friend = adapter.getItem(position);
                UserEntity user = friend.getFriendUser();
                BmobIMUserInfo info = new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getUser_icon().getFileUrl());
                //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
                BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
                Bundle bundle = new Bundle();
                bundle.putSerializable("c", c);
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(final int position) {

                return true;
            }
        });
    }

    /**
     查询本地会话
     */
    public void query(){
        BmobQuery<Friend> query = new BmobQuery<>();
        query.addWhereEqualTo("user", BmobUser.getCurrentUser(UserEntity.class));
        query.findObjects(new FindListener<Friend>() {
            @Override
            public void done(List<Friend> list, cn.bmob.v3.exception.BmobException e) {
                if (e == null && list != null) {
                    adapter.bindDatas(list);
                    adapter.notifyDataSetChanged();
                    sw_refresh.setRefreshing(false);
                }
            }
        });
    }
}

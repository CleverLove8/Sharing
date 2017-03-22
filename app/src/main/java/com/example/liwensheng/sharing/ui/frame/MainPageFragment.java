package com.example.liwensheng.sharing.ui.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.adapter.ThingApdapter;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.entity.ThingEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.activity.DetailActivity;
import com.example.liwensheng.sharing.ui.activity.QueryActivity;
import com.example.liwensheng.sharing.ui.activity.TypeDetail;
import com.example.liwensheng.sharing.utils.ConstantUtils;
import com.example.liwensheng.sharing.view.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liWensheng on 2017/2/28.
 */

public class MainPageFragment extends BaseFragment implements SearchView.OnQueryTextListener  {

    @BindView(R.id.eletronic)
    LinearLayout eletronic;
    @BindView(R.id.book)
    LinearLayout book;
    @BindView(R.id.dailyUse)
    LinearLayout dailyUse;
    @BindView(R.id.cloth)
    LinearLayout cloth;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("主页");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.mainpage_layout, null);
        ButterKnife.bind(this, view);

        initSearch();
        initTabLayout();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 点击分类跳转到相应的详情页面
     * */
    @OnClick({R.id.eletronic, R.id.book, R.id.dailyUse, R.id.cloth})
    public void typeGet(View view) {
        Intent intent = new Intent(getContext(), TypeDetail.class);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.eletronic:
                bundle.putInt("type", 1);
                break;
            case R.id.book:
                bundle.putInt("type", 2);
                break;
            case R.id.dailyUse:
                bundle.putInt("type", 3);
                break;
            case R.id.cloth:
                bundle.putInt("type", 4);
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 初始化搜索框
     * */
    private void initSearch() {
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
    }
    /**
     * 搜索监听器
     * @param  newText 搜索字段
     * */
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
    /**
     * 搜索结果处理
     * @param query 搜索内容
     * */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(getContext(), QueryActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
        return true;
    }

    private void initTabLayout() {
        String[] tabList = {"借出", "求借"};
        final Fragment[] fragmentList = {new JiechuFrame(),  new QiujieFrame()};

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabList.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabList[i]));
        }

        TabFragmentAdapter adapter = new TabFragmentAdapter(getActivity().getSupportFragmentManager(),
                Arrays.asList(fragmentList), Arrays.asList(tabList));


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
//          EventBus.getDefault().post(new ConversationFragmentUpdateEvent());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    public class TabFragmentAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragments;
        private List<String> mTitles;

        public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}

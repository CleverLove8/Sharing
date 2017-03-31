package com.example.liwensheng.sharing.ui.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.ui.activity.CircleDetail;
import com.example.liwensheng.sharing.utils.GlideImageLoader;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liWensheng on 2017/2/28.
 */

public class CirclePageFragment extends BaseFragment {

    @BindView(R.id.banner)
    BannerLayout banner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("圈子");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.circlepage_layout, null);
        ButterKnife.bind(this, view);

        initBanner();

        return view;
    }

    private void initBanner() {
        final List<String> urls = new ArrayList<>();
        urls.add("https://ww2.sinaimg.cn/large/006tNbRwgy1fe4vvlpxfyj309p06e74d.jpg");
        urls.add("https://ww3.sinaimg.cn/large/006tNbRwgy1fe4vx27f1ij30cp08c74k.jpg");

        banner.setImageLoader(new GlideImageLoader());
        banner.setViewUrls(urls);

        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 点击圈子进入
     * */
    @OnClick({R.id.bookBtn, R.id.basketballBtn, R.id.runBtn,
            R.id.movieBtn, R.id.foodBtn, R.id.chat, R.id.play})
    public void circleDetail(View view) {
        int id = 0;
        switch (view.getId()) {
            case R.id.bookBtn:
                id = 0;
                break;
            case R.id.basketballBtn:
                id = 1;
                break;
            case R.id.runBtn:
                id = 2;
                break;
            case R.id.movieBtn:
                id = 3;
                break;
            case R.id.foodBtn:
                id = 4;
                break;
            case R.id.chat:
                id = 5;
                break;
            case R.id.play:
                id = 6;
                break;
        }
        Intent intent = new Intent(getContext(), CircleDetail.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}

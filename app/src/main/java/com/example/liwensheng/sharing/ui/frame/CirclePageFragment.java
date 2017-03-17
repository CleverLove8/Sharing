package com.example.liwensheng.sharing.ui.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liWensheng on 2017/2/28.
 */

public class CirclePageFragment extends BaseFragment {

    @BindView(R.id.bookCircle)
    ImageView bookCircle;
    @BindView(R.id.movieCircle)
    ImageView mivieCircle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("圈子");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.circlepage_layout, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.bookCircle)
    public void intoBookCircle() {

    }
}

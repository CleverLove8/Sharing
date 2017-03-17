package com.example.liwensheng.sharing.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by liWensheng on 2017/2/28.
 */

public class BaseFragment extends Fragment {
    /**
     * 设置空间隐藏, 不占位
     * @param views
     */
    public void setViewVisibilityGone(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 设置空间显示
     * @param views
     */
    public void setViewVisibilityVisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }
}

// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainPageFragment_ViewBinding<T extends MainPageFragment> implements Unbinder {
  protected T target;

  private View view2131689741;

  private View view2131689742;

  private View view2131689743;

  private View view2131689744;

  @UiThread
  public MainPageFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.eletronic, "field 'eletronic' and method 'typeGet'");
    target.eletronic = Utils.castView(view, R.id.eletronic, "field 'eletronic'", LinearLayout.class);
    view2131689741 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.book, "field 'book' and method 'typeGet'");
    target.book = Utils.castView(view, R.id.book, "field 'book'", LinearLayout.class);
    view2131689742 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.dailyUse, "field 'dailyUse' and method 'typeGet'");
    target.dailyUse = Utils.castView(view, R.id.dailyUse, "field 'dailyUse'", LinearLayout.class);
    view2131689743 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cloth, "field 'cloth' and method 'typeGet'");
    target.cloth = Utils.castView(view, R.id.cloth, "field 'cloth'", LinearLayout.class);
    view2131689744 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    target.searchView = Utils.findRequiredViewAsType(source, R.id.searchView, "field 'searchView'", SearchView.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.eletronic = null;
    target.book = null;
    target.dailyUse = null;
    target.cloth = null;
    target.searchView = null;
    target.tabLayout = null;
    target.viewPager = null;

    view2131689741.setOnClickListener(null);
    view2131689741 = null;
    view2131689742.setOnClickListener(null);
    view2131689742 = null;
    view2131689743.setOnClickListener(null);
    view2131689743 = null;
    view2131689744.setOnClickListener(null);
    view2131689744 = null;

    this.target = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.view.RefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainPageFragment_ViewBinding<T extends MainPageFragment> implements Unbinder {
  protected T target;

  private View view2131689714;

  private View view2131689715;

  private View view2131689716;

  private View view2131689717;

  @UiThread
  public MainPageFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.eletronic, "field 'eletronic' and method 'typeGet'");
    target.eletronic = Utils.castView(view, R.id.eletronic, "field 'eletronic'", LinearLayout.class);
    view2131689714 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.book, "field 'book' and method 'typeGet'");
    target.book = Utils.castView(view, R.id.book, "field 'book'", LinearLayout.class);
    view2131689715 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.dailyUse, "field 'dailyUse' and method 'typeGet'");
    target.dailyUse = Utils.castView(view, R.id.dailyUse, "field 'dailyUse'", LinearLayout.class);
    view2131689716 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cloth, "field 'cloth' and method 'typeGet'");
    target.cloth = Utils.castView(view, R.id.cloth, "field 'cloth'", LinearLayout.class);
    view2131689717 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.typeGet(p0);
      }
    });
    target.horizontalScrollView = Utils.findRequiredViewAsType(source, R.id.horizontalScrollView, "field 'horizontalScrollView'", HorizontalScrollView.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.mainpage_group, "field 'radioGroup'", RadioGroup.class);
    target.qiujie = Utils.findRequiredViewAsType(source, R.id.mainpage_qiujie, "field 'qiujie'", RadioButton.class);
    target.jiechu = Utils.findRequiredViewAsType(source, R.id.mainpage_jiechu, "field 'jiechu'", RadioButton.class);
    target.mRefreshLayout = Utils.findRequiredViewAsType(source, R.id.Refresh, "field 'mRefreshLayout'", RefreshLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listview, "field 'listView'", ListView.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.searchView, "field 'searchView'", SearchView.class);
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
    target.horizontalScrollView = null;
    target.radioGroup = null;
    target.qiujie = null;
    target.jiechu = null;
    target.mRefreshLayout = null;
    target.listView = null;
    target.searchView = null;

    view2131689714.setOnClickListener(null);
    view2131689714 = null;
    view2131689715.setOnClickListener(null);
    view2131689715 = null;
    view2131689716.setOnClickListener(null);
    view2131689716 = null;
    view2131689717.setOnClickListener(null);
    view2131689717 = null;

    this.target = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.view.RefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MesGood_ViewBinding<T extends MesGood> implements Unbinder {
  protected T target;

  private View view2131689753;

  @UiThread
  public MesGood_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.good_back_btn, "field 'back_btn' and method 'goBack'");
    target.back_btn = Utils.castView(view, R.id.good_back_btn, "field 'back_btn'", LinearLayout.class);
    view2131689753 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goBack();
      }
    });
    target.mRefreshLayout = Utils.findRequiredViewAsType(source, R.id.mes_good_refresh, "field 'mRefreshLayout'", RefreshLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.mes_good_listview, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.back_btn = null;
    target.mRefreshLayout = null;
    target.listView = null;

    view2131689753.setOnClickListener(null);
    view2131689753 = null;

    this.target = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactFragment_ViewBinding<T extends ContactFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ContactFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rc_view = Utils.findRequiredViewAsType(source, R.id.rc_view, "field 'rc_view'", RecyclerView.class);
    target.sw_refresh = Utils.findRequiredViewAsType(source, R.id.sw_refresh, "field 'sw_refresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rc_view = null;
    target.sw_refresh = null;

    this.target = null;
  }
}

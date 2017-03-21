// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.view.RefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TypeDetail_ViewBinding<T extends TypeDetail> implements Unbinder {
  protected T target;

  @UiThread
  public TypeDetail_ViewBinding(T target, View source) {
    this.target = target;

    target.mRefreshLayout = Utils.findRequiredViewAsType(source, R.id.his_refresh, "field 'mRefreshLayout'", RefreshLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.his_listview, "field 'listView'", ListView.class);
    target.typeName = Utils.findRequiredViewAsType(source, R.id.type_name, "field 'typeName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mRefreshLayout = null;
    target.listView = null;
    target.typeName = null;

    this.target = null;
  }
}

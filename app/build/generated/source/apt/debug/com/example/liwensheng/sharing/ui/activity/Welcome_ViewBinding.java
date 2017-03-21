// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Welcome_ViewBinding<T extends Welcome> implements Unbinder {
  protected T target;

  @UiThread
  public Welcome_ViewBinding(T target, View source) {
    this.target = target;

    target.second = Utils.findRequiredViewAsType(source, R.id.second, "field 'second'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.second = null;

    this.target = null;
  }
}

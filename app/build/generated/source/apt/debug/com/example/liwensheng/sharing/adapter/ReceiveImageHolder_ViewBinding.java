// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReceiveImageHolder_ViewBinding<T extends ReceiveImageHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ReceiveImageHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_avatar = Utils.findRequiredViewAsType(source, R.id.iv_avatar, "field 'iv_avatar'", ImageView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.iv_picture = Utils.findRequiredViewAsType(source, R.id.iv_picture, "field 'iv_picture'", ImageView.class);
    target.progress_load = Utils.findRequiredViewAsType(source, R.id.progress_load, "field 'progress_load'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_avatar = null;
    target.tv_time = null;
    target.iv_picture = null;
    target.progress_load = null;

    this.target = null;
  }
}

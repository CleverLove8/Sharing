// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReceiveTextHolder_ViewBinding<T extends ReceiveTextHolder> implements Unbinder {
  protected T target;

  private View view2131689694;

  @UiThread
  public ReceiveTextHolder_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_avatar, "field 'iv_avatar' and method 'onAvatarClick'");
    target.iv_avatar = Utils.castView(view, R.id.iv_avatar, "field 'iv_avatar'", ImageView.class);
    view2131689694 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAvatarClick(p0);
      }
    });
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_message = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tv_message'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_avatar = null;
    target.tv_time = null;
    target.tv_message = null;

    view2131689694.setOnClickListener(null);
    view2131689694 = null;

    this.target = null;
  }
}

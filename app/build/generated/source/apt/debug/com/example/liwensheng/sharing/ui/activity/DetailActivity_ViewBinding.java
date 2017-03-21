// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailActivity_ViewBinding<T extends DetailActivity> implements Unbinder {
  protected T target;

  private View view2131689672;

  private View view2131689669;

  private View view2131689668;

  @UiThread
  public DetailActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.good_icon = Utils.findRequiredViewAsType(source, R.id.good_icon, "field 'good_icon'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.contact_button, "method 'ContactClick'");
    view2131689672 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ContactClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.googBtn, "method 'GoodClick'");
    view2131689669 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.GoodClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.commentBtn, "method 'CommentClick'");
    view2131689668 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.CommentClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.good_icon = null;

    view2131689672.setOnClickListener(null);
    view2131689672 = null;
    view2131689669.setOnClickListener(null);
    view2131689669 = null;
    view2131689668.setOnClickListener(null);
    view2131689668 = null;

    this.target = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentActivity_ViewBinding<T extends CommentActivity> implements Unbinder {
  protected T target;

  private View view2131689748;

  @UiThread
  public CommentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mContent = Utils.findRequiredViewAsType(source, R.id.comment_content, "field 'mContent'", TextView.class);
    target.mTip = Utils.findRequiredViewAsType(source, R.id.comment_tip, "field 'mTip'", TextView.class);
    view = Utils.findRequiredView(source, R.id.comment_button, "field 'comment_btn' and method 'pushComment'");
    target.comment_btn = Utils.castView(view, R.id.comment_button, "field 'comment_btn'", TextView.class);
    view2131689748 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.pushComment();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mContent = null;
    target.mTip = null;
    target.comment_btn = null;

    view2131689748.setOnClickListener(null);
    view2131689748 = null;

    this.target = null;
  }
}

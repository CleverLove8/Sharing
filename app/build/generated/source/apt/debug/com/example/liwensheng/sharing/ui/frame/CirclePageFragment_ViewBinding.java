// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

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

public class CirclePageFragment_ViewBinding<T extends CirclePageFragment> implements Unbinder {
  protected T target;

  private View view2131689634;

  @UiThread
  public CirclePageFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bookCircle, "field 'bookCircle' and method 'intoBookCircle'");
    target.bookCircle = Utils.castView(view, R.id.bookCircle, "field 'bookCircle'", ImageView.class);
    view2131689634 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.intoBookCircle();
      }
    });
    target.mivieCircle = Utils.findRequiredViewAsType(source, R.id.movieCircle, "field 'mivieCircle'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bookCircle = null;
    target.mivieCircle = null;

    view2131689634.setOnClickListener(null);
    view2131689634 = null;

    this.target = null;
  }
}

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

public class ShowImg_ViewBinding<T extends ShowImg> implements Unbinder {
  protected T target;

  private View view2131689792;

  @UiThread
  public ShowImg_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.showBackground, "field 'imageView' and method 'closeImg'");
    target.imageView = Utils.castView(view, R.id.showBackground, "field 'imageView'", ImageView.class);
    view2131689792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeImg(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imageView = null;

    view2131689792.setOnClickListener(null);
    view2131689792 = null;

    this.target = null;
  }
}

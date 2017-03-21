// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessagesPageFragment_ViewBinding<T extends MessagesPageFragment> implements Unbinder {
  protected T target;

  private View view2131689769;

  private View view2131689771;

  @UiThread
  public MessagesPageFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.mes_dianzan, "field 'dianzan' and method 'cheskMes'");
    target.dianzan = Utils.castView(view, R.id.mes_dianzan, "field 'dianzan'", LinearLayout.class);
    view2131689769 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cheskMes(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.mes_pinglun, "field 'pinglun' and method 'cheskMes'");
    target.pinglun = Utils.castView(view, R.id.mes_pinglun, "field 'pinglun'", LinearLayout.class);
    view2131689771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cheskMes(p0);
      }
    });
    target.num_dianzan = Utils.findRequiredViewAsType(source, R.id.num_dianzan, "field 'num_dianzan'", TextView.class);
    target.num_pinglun = Utils.findRequiredViewAsType(source, R.id.num_pinglun, "field 'num_pinglun'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.dianzan = null;
    target.pinglun = null;
    target.num_dianzan = null;
    target.num_pinglun = null;

    view2131689769.setOnClickListener(null);
    view2131689769 = null;
    view2131689771.setOnClickListener(null);
    view2131689771 = null;

    this.target = null;
  }
}

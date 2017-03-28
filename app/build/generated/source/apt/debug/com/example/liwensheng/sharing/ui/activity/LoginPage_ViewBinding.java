// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginPage_ViewBinding<T extends LoginPage> implements Unbinder {
  protected T target;

  private View view2131689605;

  private View view2131689607;

  @UiThread
  public LoginPage_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'etUsername'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_go, "field 'btGo' and method 'onClick'");
    target.btGo = Utils.castView(view, R.id.bt_go, "field 'btGo'", Button.class);
    view2131689605 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fab, "field 'fab' and method 'onClick'");
    target.fab = Utils.castView(view, R.id.fab, "field 'fab'", TextView.class);
    view2131689607 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.forget = Utils.findRequiredViewAsType(source, R.id.forget, "field 'forget'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etUsername = null;
    target.etPassword = null;
    target.btGo = null;
    target.fab = null;
    target.forget = null;

    view2131689605.setOnClickListener(null);
    view2131689605 = null;
    view2131689607.setOnClickListener(null);
    view2131689607 = null;

    this.target = null;
  }
}

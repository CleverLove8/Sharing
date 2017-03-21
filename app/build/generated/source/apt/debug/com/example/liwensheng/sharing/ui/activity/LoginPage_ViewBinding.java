// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginPage_ViewBinding<T extends LoginPage> implements Unbinder {
  protected T target;

  private View view2131689607;

  private View view2131689608;

  @UiThread
  public LoginPage_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'etUsername'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_go, "field 'btGo' and method 'onClick'");
    target.btGo = Utils.castView(view, R.id.bt_go, "field 'btGo'", Button.class);
    view2131689607 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.keepPassword = Utils.findRequiredViewAsType(source, R.id.keep_password, "field 'keepPassword'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.fab, "field 'fab' and method 'onClick'");
    target.fab = Utils.castView(view, R.id.fab, "field 'fab'", Button.class);
    view2131689608 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etUsername = null;
    target.etPassword = null;
    target.btGo = null;
    target.keepPassword = null;
    target.fab = null;

    view2131689607.setOnClickListener(null);
    view2131689607 = null;
    view2131689608.setOnClickListener(null);
    view2131689608 = null;

    this.target = null;
  }
}

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
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutMe_ViewBinding<T extends AboutMe> implements Unbinder {
  protected T target;

  private View view2131689592;

  @UiThread
  public AboutMe_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.user_icon, "field 'User_icon' and method 'onProfileImage'");
    target.User_icon = Utils.castView(view, R.id.user_icon, "field 'User_icon'", CircleImageView.class);
    view2131689592 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onProfileImage(p0);
      }
    });
    target.User_name = Utils.findRequiredViewAsType(source, R.id.user_name, "field 'User_name'", TextView.class);
    target.User_phoneNum = Utils.findRequiredViewAsType(source, R.id.user_phoneNum, "field 'User_phoneNum'", TextView.class);
    target.User_qq = Utils.findRequiredViewAsType(source, R.id.user_qq, "field 'User_qq'", TextView.class);
    target.User_credit = Utils.findRequiredViewAsType(source, R.id.user_credit, "field 'User_credit'", TextView.class);
    target.User_history = Utils.findRequiredViewAsType(source, R.id.user_history, "field 'User_history'", LinearLayout.class);
    target.User_collect = Utils.findRequiredViewAsType(source, R.id.user_collect, "field 'User_collect'", LinearLayout.class);
    target.User_location = Utils.findRequiredViewAsType(source, R.id.user_location, "field 'User_location'", LinearLayout.class);
    target.User_circle = Utils.findRequiredViewAsType(source, R.id.user_circle, "field 'User_circle'", LinearLayout.class);
    target.User_setting = Utils.findRequiredViewAsType(source, R.id.user_setting, "field 'User_setting'", LinearLayout.class);
    target.User_abouUs = Utils.findRequiredViewAsType(source, R.id.user_aboutUs, "field 'User_abouUs'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.User_icon = null;
    target.User_name = null;
    target.User_phoneNum = null;
    target.User_qq = null;
    target.User_credit = null;
    target.User_history = null;
    target.User_collect = null;
    target.User_location = null;
    target.User_circle = null;
    target.User_setting = null;
    target.User_abouUs = null;

    view2131689592.setOnClickListener(null);
    view2131689592 = null;

    this.target = null;
  }
}

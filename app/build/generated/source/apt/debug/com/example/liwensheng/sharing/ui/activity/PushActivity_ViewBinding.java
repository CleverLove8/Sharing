// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PushActivity_ViewBinding<T extends PushActivity> implements Unbinder {
  protected T target;

  private View view2131689729;

  private View view2131689781;

  @UiThread
  public PushActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'mName'", EditText.class);
    target.mContent = Utils.findRequiredViewAsType(source, R.id.et_content, "field 'mContent'", EditText.class);
    target.mTip = Utils.findRequiredViewAsType(source, R.id.tv_tip, "field 'mTip'", TextView.class);
    target.mLocation = Utils.findRequiredViewAsType(source, R.id.et_location, "field 'mLocation'", EditText.class);
    target.mLayoutImg = Utils.findRequiredViewAsType(source, R.id.layout_img, "field 'mLayoutImg'", LinearLayout.class);
    target.mTime = Utils.findRequiredViewAsType(source, R.id.et_time, "field 'mTime'", EditText.class);
    target.mRadioGroup = Utils.findRequiredViewAsType(source, R.id.et_type, "field 'mRadioGroup'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.push_button, "field 'push_btn' and method 'push'");
    target.push_btn = Utils.castView(view, R.id.push_button, "field 'push_btn'", TextView.class);
    view2131689729 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.push();
      }
    });
    view = Utils.findRequiredView(source, R.id.img_plus, "method 'onForget'");
    view2131689781 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onForget(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mName = null;
    target.mContent = null;
    target.mTip = null;
    target.mLocation = null;
    target.mLayoutImg = null;
    target.mTime = null;
    target.mRadioGroup = null;
    target.push_btn = null;

    view2131689729.setOnClickListener(null);
    view2131689729 = null;
    view2131689781.setOnClickListener(null);
    view2131689781 = null;

    this.target = null;
  }
}

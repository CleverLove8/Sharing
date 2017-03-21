// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatActivity_ViewBinding<T extends ChatActivity> implements Unbinder {
  protected T target;

  private View view2131689684;

  private View view2131689682;

  private View view2131689683;

  private View view2131689686;

  private View view2131689687;

  private View view2131689688;

  private View view2131689679;

  private View view2131689680;

  private View view2131689681;

  @UiThread
  public ChatActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.ll_chat = Utils.findRequiredViewAsType(source, R.id.ll_chat, "field 'll_chat'", LinearLayout.class);
    target.sw_refresh = Utils.findRequiredViewAsType(source, R.id.sw_refresh, "field 'sw_refresh'", SwipeRefreshLayout.class);
    target.rc_view = Utils.findRequiredViewAsType(source, R.id.rc_view, "field 'rc_view'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.edit_msg, "field 'edit_msg' and method 'onEditClick'");
    target.edit_msg = Utils.castView(view, R.id.edit_msg, "field 'edit_msg'", EditText.class);
    view2131689684 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_chat_add, "field 'btn_chat_add' and method 'onAddClick'");
    target.btn_chat_add = Utils.castView(view, R.id.btn_chat_add, "field 'btn_chat_add'", Button.class);
    view2131689682 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_chat_emo, "field 'btn_chat_emo' and method 'onEmoClick'");
    target.btn_chat_emo = Utils.castView(view, R.id.btn_chat_emo, "field 'btn_chat_emo'", Button.class);
    view2131689683 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEmoClick(p0);
      }
    });
    target.btn_speak = Utils.findRequiredViewAsType(source, R.id.btn_speak, "field 'btn_speak'", Button.class);
    view = Utils.findRequiredView(source, R.id.btn_chat_voice, "field 'btn_chat_voice' and method 'onVoiceClick'");
    target.btn_chat_voice = Utils.castView(view, R.id.btn_chat_voice, "field 'btn_chat_voice'", Button.class);
    view2131689686 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onVoiceClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_chat_keyboard, "field 'btn_chat_keyboard' and method 'onKeyClick'");
    target.btn_chat_keyboard = Utils.castView(view, R.id.btn_chat_keyboard, "field 'btn_chat_keyboard'", Button.class);
    view2131689687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onKeyClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_chat_send, "field 'btn_chat_send' and method 'onSendClick'");
    target.btn_chat_send = Utils.castView(view, R.id.btn_chat_send, "field 'btn_chat_send'", Button.class);
    view2131689688 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendClick(p0);
      }
    });
    target.layout_more = Utils.findRequiredViewAsType(source, R.id.layout_more, "field 'layout_more'", LinearLayout.class);
    target.layout_add = Utils.findRequiredViewAsType(source, R.id.layout_add, "field 'layout_add'", LinearLayout.class);
    target.layout_emo = Utils.findRequiredViewAsType(source, R.id.layout_emo, "field 'layout_emo'", LinearLayout.class);
    target.layout_record = Utils.findRequiredViewAsType(source, R.id.layout_record, "field 'layout_record'", RelativeLayout.class);
    target.tv_voice_tips = Utils.findRequiredViewAsType(source, R.id.tv_voice_tips, "field 'tv_voice_tips'", TextView.class);
    target.iv_record = Utils.findRequiredViewAsType(source, R.id.iv_record, "field 'iv_record'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_picture, "method 'onPictureClick'");
    view2131689679 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onPictureClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_camera, "method 'onCameraClick'");
    view2131689680 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCameraClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_location, "method 'onLocationClick'");
    view2131689681 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLocationClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_chat = null;
    target.sw_refresh = null;
    target.rc_view = null;
    target.edit_msg = null;
    target.btn_chat_add = null;
    target.btn_chat_emo = null;
    target.btn_speak = null;
    target.btn_chat_voice = null;
    target.btn_chat_keyboard = null;
    target.btn_chat_send = null;
    target.layout_more = null;
    target.layout_add = null;
    target.layout_emo = null;
    target.layout_record = null;
    target.tv_voice_tips = null;
    target.iv_record = null;

    view2131689684.setOnClickListener(null);
    view2131689684 = null;
    view2131689682.setOnClickListener(null);
    view2131689682 = null;
    view2131689683.setOnClickListener(null);
    view2131689683 = null;
    view2131689686.setOnClickListener(null);
    view2131689686 = null;
    view2131689687.setOnClickListener(null);
    view2131689687 = null;
    view2131689688.setOnClickListener(null);
    view2131689688 = null;
    view2131689679.setOnClickListener(null);
    view2131689679 = null;
    view2131689680.setOnClickListener(null);
    view2131689680 = null;
    view2131689681.setOnClickListener(null);
    view2131689681 = null;

    this.target = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatActivity$$ViewBinder<T extends com.example.liwensheng.sharing.ui.activity.ChatActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493027, "method 'onEditClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onEditClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493026, "method 'onEmoClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onEmoClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493025, "method 'onAddClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onAddClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493029, "method 'onVoiceClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onVoiceClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493030, "method 'onKeyClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onKeyClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493031, "method 'onSendClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onSendClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493022, "method 'onPictureClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onPictureClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493023, "method 'onCameraClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onCameraClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493024, "method 'onLocationClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onLocationClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}

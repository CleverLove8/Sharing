// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ReceiveTextHolder$$ViewBinder<T extends com.example.liwensheng.sharing.adapter.ReceiveTextHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493037, "method 'onAvatarClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onAvatarClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}

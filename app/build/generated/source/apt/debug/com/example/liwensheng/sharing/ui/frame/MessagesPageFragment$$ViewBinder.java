// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MessagesPageFragment$$ViewBinder<T extends com.example.liwensheng.sharing.ui.frame.MessagesPageFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493087, "method 'cheskMes'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.cheskMes(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493089, "method 'cheskMes'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.cheskMes(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}

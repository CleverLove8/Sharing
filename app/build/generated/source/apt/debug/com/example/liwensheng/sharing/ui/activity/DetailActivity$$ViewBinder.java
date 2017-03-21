// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DetailActivity$$ViewBinder<T extends com.example.liwensheng.sharing.ui.activity.DetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493015, "method 'ContactClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ContactClick();
        }
      });
    view = finder.findRequiredView(source, 2131493012, "method 'GoodClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.GoodClick();
        }
      });
    view = finder.findRequiredView(source, 2131493011, "method 'CommentClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.CommentClick();
        }
      });
  }

  @Override public void unbind(T target) {
  }
}

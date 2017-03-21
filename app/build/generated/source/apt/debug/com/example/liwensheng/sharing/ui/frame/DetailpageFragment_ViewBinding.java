// Generated code from Butter Knife. Do not modify!
package com.example.liwensheng.sharing.ui.frame;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.view.RefreshLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailpageFragment_ViewBinding<T extends DetailpageFragment> implements Unbinder {
  protected T target;

  @UiThread
  public DetailpageFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.icon = Utils.findRequiredViewAsType(source, R.id.detail_icon, "field 'icon'", CircleImageView.class);
    target.userName = Utils.findRequiredViewAsType(source, R.id.detail_user, "field 'userName'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.detail_time, "field 'time'", TextView.class);
    target.location = Utils.findRequiredViewAsType(source, R.id.detail_location, "field 'location'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.detail_name, "field 'name'", TextView.class);
    target.howlong = Utils.findRequiredViewAsType(source, R.id.detail_howlong, "field 'howlong'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.detail_content, "field 'content'", TextView.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.detail_img, "field 'img'", ImageView.class);
    target.Ifcomment = Utils.findRequiredViewAsType(source, R.id.comment_number, "field 'Ifcomment'", TextView.class);
    target.mRefreshLayout = Utils.findRequiredViewAsType(source, R.id.detail_refresh, "field 'mRefreshLayout'", RefreshLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.detail_listview, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.icon = null;
    target.userName = null;
    target.time = null;
    target.location = null;
    target.name = null;
    target.howlong = null;
    target.content = null;
    target.img = null;
    target.Ifcomment = null;
    target.mRefreshLayout = null;
    target.listView = null;

    this.target = null;
  }
}

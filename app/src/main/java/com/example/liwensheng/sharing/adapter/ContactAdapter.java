package com.example.liwensheng.sharing.adapter;

import android.content.Context;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.Friend;
import com.example.liwensheng.sharing.entity.UserEntity;

import java.util.Collection;


/**联系人
 * 一种简洁的Adapter实现方式，可用于多种Item布局的recycleView实现，不用再写ViewHolder啦
 * @author :smile
 * @project:ContactNewAdapter
 * @date :2016-04-27-14:18
 */
public class ContactAdapter extends BaseRecyclerAdapter<Friend> {

    public static final int TYPE_NEW_FRIEND = 0;
    public static final int TYPE_ITEM = 1;

    public ContactAdapter(Context context, IMutlipleItem<Friend> items, Collection<Friend> datas) {
        super(context,items,datas);
    }

    @Override
    public void bindView(BaseRecyclerHolder holder, Friend friend, int position) {
        UserEntity user = friend.getFriendUser();
        if(holder.layoutId== R.layout.item_contact){
            //好友头像
            holder.setImageView(friend == null ? null : user.getUser_icon().getFileUrl(), R.mipmap.default_avatar, R.id.iv_recent_avatar);
            //好友名称
            holder.setText(R.id.tv_recent_name,user.getUser_name()==null?"未知":user.getUsername());
        }
        else {
            return;
        }
    }

}

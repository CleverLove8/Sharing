package com.example.liwensheng.sharing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.CommentEntity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liWensheng on 2017/3/10.
 */

public class CommentAdapter extends BaseAdapter {

    private Context context;
    private List<CommentEntity> list;
    private CommentAdapter.ViewHodler holder;
    private LayoutInflater inflater;

    public  CommentAdapter(Context context, List<CommentEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.comment_item, null);
            holder = new CommentAdapter.ViewHodler();
            holder.icon = (CircleImageView) convertView.findViewById(R.id.comment_icon);
            holder.author = (TextView) convertView.findViewById(R.id.comment_user);
            holder.content = (TextView) convertView.findViewById(R.id.comment_content);
            holder.createdTime = (TextView) convertView.findViewById(R.id.comment_time);
            convertView.setTag(holder);
        } else {
            holder = (CommentAdapter.ViewHodler) convertView.getTag();
        }
        CommentEntity thingEntity = list.get(position);

        if (thingEntity.getUserEntity().getUser_icon() != null) {
            Glide.with(context)
                    .load(thingEntity.getUserEntity().getUser_icon().getFileUrl())
                    .into(holder.icon);
        } else {
            Glide.with(context)
                    .load(R.drawable.default_avatar)
                    .into(holder.icon);
        }
        holder.author.setText(thingEntity.getUserEntity().getUser_name());
        holder.content.setText(thingEntity.getComments());
        holder.createdTime.setText(thingEntity.getCreatedAt());
        return convertView;
    }

    private class ViewHodler {
        CircleImageView icon;
        TextView author;
        TextView content;
        TextView createdTime;
    }
}

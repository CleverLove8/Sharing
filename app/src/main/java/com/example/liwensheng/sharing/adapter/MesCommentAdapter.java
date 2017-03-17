package com.example.liwensheng.sharing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.CommentEntity;
import com.example.liwensheng.sharing.entity.GoodEntity;
import com.example.liwensheng.sharing.entity.ThingEntity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liWensheng on 2017/3/14.
 */

public class MesCommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentEntity> list;
    private ViewHodler holder;
    private LayoutInflater inflater;

    public  MesCommentAdapter(Context context, List<CommentEntity> list) {
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
            convertView = inflater.inflate(R.layout.mes_comment_item, null);
            holder = new ViewHodler();
            holder.icon = (CircleImageView) convertView.findViewById(R.id.good_icon);
            holder.good_user = (TextView) convertView.findViewById(R.id.good_user);
            holder.good_time = (TextView) convertView.findViewById(R.id.good_time);
            holder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);
            holder.mes_img = (ImageView) convertView.findViewById(R.id.mes_img);
            holder.mes_time = (TextView) convertView.findViewById(R.id.mes_time);
            holder.mes_location = (TextView) convertView.findViewById(R.id.mes_location);
            holder.mes_type = (TextView) convertView.findViewById(R.id.mes_type);
            holder.mes_name = (TextView) convertView.findViewById(R.id.mes_name);
            holder.mes_howlong = (TextView) convertView.findViewById(R.id.mes_howlong);
            holder.mes_content = (TextView) convertView.findViewById(R.id.mes_content);
            convertView.setTag(holder);
        } else {
            holder = (MesCommentAdapter.ViewHodler) convertView.getTag();
        }
        CommentEntity goodEntity = list.get(position);
        ThingEntity thingEntity = goodEntity.getThingEntity();
//        BmobQuery<UserEntity> query = new BmobQuery<>();
//        query.addWhereEqualTo("objectId", goodEntity.getUserEntity().getObjectId());
//        query.findObjects(new FindListener<UserEntity>() {
//            @Override
//            public void done(List<UserEntity> list, BmobException e) {
//
//            }
//        });

        if (goodEntity.getUserEntity().getUser_icon() != null) {
            Glide.with(context)
                    .load(goodEntity.getUserEntity().getUser_icon().getFileUrl())
                    .into(holder.icon);
        }
        else {
            Glide.with(context)
                    .load(R.drawable.default_avatar)
                    .into(holder.icon);
        }
        holder.good_user.setText(goodEntity.getUserEntity().getUser_name());
        holder.good_time.setText(goodEntity.getCreatedAt());
        holder.comment_content.setText(goodEntity.getComments());

        if (thingEntity.getImg() != null) {
            holder.mes_img.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(thingEntity.getImg().getFileUrl())
                    .into(holder.mes_img);
        } else {
            holder.mes_img.setVisibility(View.GONE);
        }
        holder.mes_time.setText(thingEntity.getCreatedAt());
        holder.mes_location.setText(thingEntity.getLocation());
        if (thingEntity.getType() == 1) {
            holder.mes_type.setText("求借");
        }
        else {
            holder.mes_type.setText("借出");
        }
        holder.mes_name.setText(thingEntity.getName());
        holder.mes_howlong.setText(thingEntity.getTime());
        holder.mes_content.setText(thingEntity.getContent());

        return convertView;
    }

    private class ViewHodler {
        CircleImageView icon;
        TextView good_user;
        TextView good_time;
        TextView comment_content;
        ImageView mes_img;
        TextView mes_user;
        TextView mes_time;
        TextView mes_location;
        TextView mes_type;
        TextView mes_name;
        TextView mes_howlong;
        TextView mes_content;
    }
}

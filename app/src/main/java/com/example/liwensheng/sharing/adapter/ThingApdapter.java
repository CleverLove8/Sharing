package com.example.liwensheng.sharing.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.ThingEntity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liWensheng on 2017/3/6.
 */

public class ThingApdapter extends BaseAdapter {

    private Context context;
    private List<ThingEntity> list;
    private ViewHodler holder;
    private LayoutInflater inflater;

    public  ThingApdapter(Context context, List<ThingEntity> list) {
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
            convertView = inflater.inflate(R.layout.main_item, null);
            holder = new ViewHodler();
            holder.icon = (CircleImageView) convertView.findViewById(R.id.push_icon);
            holder.author = (TextView) convertView.findViewById(R.id.push_user);
            holder.img = (ImageView) convertView.findViewById(R.id.push_img);
            holder.name = (TextView) convertView.findViewById(R.id.push_name);
            holder.createdTime = (TextView) convertView.findViewById(R.id.push_time);
            holder.location = (TextView) convertView.findViewById(R.id.push_location);
            holder.type = (TextView) convertView.findViewById(R.id.push_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHodler) convertView.getTag();
        }
        ThingEntity thingEntity = list.get(position);

        if (thingEntity.getAuthor().getUser_icon() != null) {
            Glide.with(context)
                    .load(thingEntity.getAuthor().getUser_icon().getFileUrl())
                    .into(holder.icon);
        } else {
            Glide.with(context)
                    .load(R.drawable.default_avatar)
                    .into(holder.icon);
        }
        holder.author.setText(thingEntity.getAuthor().getUser_name());
        if (thingEntity.getImg() != null) {
            holder.img.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(thingEntity.getImg().getFileUrl())
                    .into(holder.img);
        } else {
            holder.img.setVisibility(View.GONE);
        }
        holder.name.setText(thingEntity.getName());
        holder.location.setText(thingEntity.getLocation());
        holder.createdTime.setText(thingEntity.getCreatedAt());
        if (thingEntity.getSend()) {
            holder.type.setText("求借");
        }
        else {
            holder.type.setText("借出");
        }
        return convertView;
    }

    private class ViewHodler {
        CircleImageView icon;
        TextView author;
        ImageView img;
        TextView name;
        TextView location;
        TextView createdTime;
        TextView type;
    }
}

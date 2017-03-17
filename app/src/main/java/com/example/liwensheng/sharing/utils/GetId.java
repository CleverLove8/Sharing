package com.example.liwensheng.sharing.utils;

import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.entity.ThingEntity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liWensheng on 2017/3/12.
 */

public class GetId {
    private String authorId;

    public GetId() {
        authorId = "";
    }

    public String getIdByThingId(String thingId) {
        BmobQuery<ThingEntity> query = new BmobQuery<>();
        query.order("-createdAt"); // 按时间降序查询
        query.include("author"); // 希望在查询帖子信息的同时也把发布人的信息查询出来
        query.addWhereEqualTo("objectId", thingId);
        query.setLimit(5);//设置每页数据个数
        //查找数据
        query.findObjects(new FindListener<ThingEntity>() {
            @Override
            public void done(List<ThingEntity> list, cn.bmob.v3.exception.BmobException e) {
                if (e==null) {
                    final ThingEntity object = list.get(0);
                    authorId = object.getAuthor().getObjectId();
                }
            }
        });
        return authorId;
    }
}

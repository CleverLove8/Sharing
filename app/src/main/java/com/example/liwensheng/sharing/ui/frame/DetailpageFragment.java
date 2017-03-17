package com.example.liwensheng.sharing.ui.frame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.adapter.CommentAdapter;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.entity.CommentEntity;
import com.example.liwensheng.sharing.entity.GoodEntity;
import com.example.liwensheng.sharing.entity.ThingEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.activity.CommentActivity;
import com.example.liwensheng.sharing.ui.activity.ShowImg;
import com.example.liwensheng.sharing.view.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteBatchListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liWensheng on 2017/3/9.
 */

public class DetailpageFragment extends BaseFragment {



    @BindView(R.id.detail_icon)
    CircleImageView icon;
    @BindView(R.id.detail_user)
    TextView userName;
    @BindView(R.id.detail_time)
    TextView time;
    @BindView(R.id.detail_location)
    TextView location;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_howlong)
    TextView howlong;
    @BindView(R.id.detail_content)
    TextView content;
    @BindView(R.id.detail_img)
    ImageView img;

    @BindView(R.id.comment_number)
    TextView Ifcomment;
    @BindView(R.id.detail_refresh)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.detail_listview)
    ListView listView;


    private final int SUCCESS = 0;
    private final int FAIL = 1;

    private static final int STATE_REFRESH = 0; // 下拉刷新
    private static final int STATE_MORE = 1; // 加载更多
    private int limit = 5; // 每页的数据是10条
    private String lastTime;

    //评论
    private CommentAdapter mAdapter;
    private boolean loading = false;
    private int currentPage = 0; //当前页面
    private List<CommentEntity> commentEntities = new ArrayList<>();

    //点赞
    private int goodNum; //已经点赞的数目
    private boolean isGood; //当前用户是否点赞
    private String goodId; //如用户点赞，则此为点赞记录的id

    private ThingEntity detailThing;
    private String authorId;


    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.detail_frame, null);
        ButterKnife.bind(this, view);

        detailThing = new ThingEntity();
        Bundle bundle = getArguments();
        detailThing = (ThingEntity) bundle.getSerializable("thing");
        showPublishDialog();
        getData(); //获取消息详情
//        setGoodNum();//设置点赞数目
        initRefreshLayout(); //加载评论



        authorId = detailThing.getAuthor().getObjectId();

        return view;
    }



    /**
     * 根据id获取详细消息
     * */
    private void getData() {
        if (detailThing.getAuthor().getUser_icon() != null) {
            Glide.with(getContext()).load(detailThing.getAuthor().getUser_icon().getFileUrl()).into(icon);
        }
        else{
            Glide.with(getContext()).load(R.drawable.default_avatar).into(icon);
        }
        userName.setText(detailThing.getAuthor().getUser_name());
        time.setText(detailThing.getCreatedAt());
        location.setText(detailThing.getLocation());
        name.setText(detailThing.getName());
        howlong.setText(detailThing.getTime());
        content.setText(detailThing.getContent());
        if (detailThing.getImg() != null) {
            img.setVisibility(View.VISIBLE);
            Glide.with(getContext())
                    .load(detailThing.getImg().getFileUrl())
                    .into(img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickImg(detailThing);
                }
            });
        } else {
            img.setVisibility(View.GONE);
        }

    }

    /**
     * 点击图片全屏显示
     * */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    String url =(String) msg.obj;
                    Intent intent = new Intent(getContext(), ShowImg.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                    break;
                case FAIL:
                    Toast.makeText(getContext(), "图片加载失败", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    private void clickImg(final ThingEntity thingEntity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = thingEntity.getImg().getFileUrl();
                    if (url.length() != 0) {
                        Message msg = new Message();
                        msg.what = SUCCESS;
                        msg.obj = url;
                        handler.sendMessage(msg);
                    }
                    else {
                        Message msg = new Message();
                        msg.what = FAIL;
                        handler.sendMessage(msg);
                    }
                }catch (Exception e) {
//                    Toast.makeText(DetailActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    /**
//     * 点赞
//     * */
//
//    @OnClick(R.id.googBtn)
//    public void GoodClick() {
//        //查询当前用户是否点赞该消息
//        if (isGood) {
//            deleteGood();
//
//        }
//        else {
//            addGood();
//        }
//    }
//
//    private void setGoodNum() {
//        isGood = false;
//        BmobQuery<GoodEntity> query = new BmobQuery<>();
//        query.addWhereEqualTo("thingEntity", detailThing);
//        query.addWhereEqualTo("userEntity", BmobUser.getCurrentUser(UserEntity.class));
//        query.include("userEntity");
//        query.findObjects(new FindListener<GoodEntity>() {
//            @Override
//            public void done(List<GoodEntity> list, BmobException e) {
////                goodNum = list.size();
//                //判断当前用户是否点赞
//                if (list.size() != 0) {
//                    goodNum++;
//                    isGood = true;
//                    goodId = list.get(0).getObjectId();
//                }
//                if (isGood) {
//                    good_icon.setBackgroundResource(R.mipmap.good_2);
//                }
////                Toast.makeText(getContext(), goodId, Toast.LENGTH_LONG).show();
//                good_number.setText(goodNum + "");
//                progressDialog.dismiss();
//            }
//        });
//
//
//    }
//
//
//    private void addGood() {
//        final GoodEntity goodEntity = new GoodEntity();
//        goodEntity.setUserEntity(BmobUser.getCurrentUser(UserEntity.class));
//        goodEntity.setThingEntity(detailThing);
//        goodEntity.setRead(false);
//        goodEntity.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                goodNum+=1;
//                good_icon.setBackgroundResource(R.mipmap.good_2);
//                good_number.setText(goodNum+"");
//                goodId = goodEntity.getObjectId();
//                isGood = true;
//            }
//        });
//    }
//
//    private void deleteGood() {
//        final GoodEntity goodEntity = new GoodEntity();
//        goodEntity.delete(goodId, new UpdateListener() {
//            @Override
//            public void done(BmobException e) {
//                goodNum-=1;
//                good_icon.setBackgroundResource(R.mipmap.good);
//                good_number.setText(goodNum+"");
//                isGood = false;
//            }
//        });
//    }
//
//    /**
//     * 点击评论
//     * */
//    @OnClick(R.id.commentBtn)
//    public void CommentClick() {
//        Intent intent = new Intent(getContext(), CommentActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("thing", detailThing);
//        intent.putExtras(bundle);
//        startActivity(intent);
//        getActivity().finish();
//    }


    /**
     * 初始化RefreshLayout
     * 刷新页面
     * 加载相关评论
     * */
    private void initRefreshLayout() {
        // 设置进度动画的颜色
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark);
        // 设置进度圈的大小,只有两个值:DEFAULT、LARGE
        mRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        // true:下拉过程会自动缩放,230:下拉刷新的高度
        mRefreshLayout.setProgressViewEndTarget(true, 230);

        // 进入页面就执行下拉动画
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData(0, STATE_REFRESH);
            }
        });
        // 下拉刷新操作
        mRefreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(0, STATE_REFRESH);
            }
        });
        // 上拉加载更多操作
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                if (!loading) {
                    loading = true;
                    getData(currentPage, STATE_MORE);
                }
            }
        });

        mAdapter = new CommentAdapter(getContext(), commentEntities);


        if (mAdapter != null) {
            listView.setAdapter(mAdapter);
        }

        progressDialog.dismiss();
    }

    /**
     * 分页获取数据
     * @param page 页码
     * @param actionType istView的操作类型（下拉刷新、上拉加载更多）
     */
    public void getData(final int page, final int actionType) {
        BmobQuery<CommentEntity> query = new BmobQuery<>();
        query.order("-createdAt"); // 按时间降序查询
        query.include("userEntity"); // 希望在查询帖子信息的同时也把发布人的信息查询出来
        query.addWhereEqualTo("thingEntity", detailThing);
        if(actionType == STATE_MORE) { //加载更多
            // 处理时间查询
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            // 只查询小于等于最后一个item发表时间的数据
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            // query.setSkip(page*limit+1); // 跳过之前页数并去掉重复数据
        } else {
            query.setSkip(0);
        }
        query.setLimit(limit);//设置每页数据个数
        //查找数据
        query.findObjects(new FindListener<CommentEntity>() {
            @Override
            public void done(List<CommentEntity> list, cn.bmob.v3.exception.BmobException e) {
                if (e==null) {
                    if (list.size()>0) {
                        if(actionType == STATE_REFRESH) {
                            currentPage = 0;
                            commentEntities.clear();
                        }
                        for (CommentEntity td : list) {
                            commentEntities.add(td);
//                            Toast.makeText(getContext(), td.getUserEntity().getUser_name(), Toast.LENGTH_LONG).show();
                        }
                        if(commentEntities.size()>0) {
                            Ifcomment.setVisibility(View.GONE);
                        }
                        currentPage++;
                        mAdapter.notifyDataSetChanged();
                        lastTime = commentEntities.get(commentEntities.size()-1).getCreatedAt(); // 获取最后时间
                        if (actionType == STATE_MORE) {
                            mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                        }
                    }
                    else if (actionType == STATE_MORE) {
//                        Toast.makeText(getContext(),"没有更多了", Toast.LENGTH_LONG).show();
                        mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                    }
                    else if (actionType == STATE_REFRESH) {
//                        Toast.makeText(getContext(),"没有数据", Toast.LENGTH_LONG).show();
                    }
                    loading = false;
                    mRefreshLayout.setRefreshing(false); // 请求完成结束刷新状态
                }

                else {
                    if (actionType == STATE_MORE) {
                        mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                    }
                    e.printStackTrace();
//                    Toast.makeText(getContext(),"请求异常，请稍后重试：" + e.toString(), Toast.LENGTH_LONG).show();
                    loading = false;
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 显示加载时的Dialog
     */
    private void showPublishDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("加载中..");
        progressDialog.setCancelable(false); // 设置点击Dialog以外的界面不消失，按返回键也不起作用
        progressDialog.show();
    }
}

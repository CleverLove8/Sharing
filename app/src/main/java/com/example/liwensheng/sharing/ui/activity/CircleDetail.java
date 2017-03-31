package com.example.liwensheng.sharing.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.adapter.CircleAdapter;
import com.example.liwensheng.sharing.adapter.CommentAdapter;
import com.example.liwensheng.sharing.entity.CircleEntity;
import com.example.liwensheng.sharing.entity.CommentEntity;
import com.example.liwensheng.sharing.view.RefreshLayout;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liWensheng on 2017/3/30.
 */

public class CircleDetail extends AppCompatActivity {

    @BindView(R.id.circleBanner)
    RelativeLayout banner;
    @BindView(R.id.circleImg)
    ImageView circleImg;
    @BindView(R.id.circleTitle)
    TextView circleTitle;
    @BindView(R.id.pushNum)
    TextView pushNum;
    @BindView(R.id.joinNum)
    TextView joinNum;
    @BindView(R.id.circleNotice)
    TextView circleNotice;
    @BindView(R.id.circleHuaTi)
    TextView circleHuaTi;

    @BindView(R.id.Refresh)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.listview)
    ListView listView;

    private int id;

    private final int SUCCESS = 0;
    private final int FAIL = 1;

    private static final int STATE_REFRESH = 0; // 下拉刷新
    private static final int STATE_MORE = 1; // 加载更多
    private int limit = 5; // 每页的数据是10条
    private String lastTime;

    //评论
    private CircleAdapter mAdapter;
    private boolean loading = false;
    private int currentPage = 0; //当前页面
    private List<CircleEntity> circleEntities = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        ButterKnife.bind(this);

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getInt("id");

        loadImg();
        showPublishDialog();
        initRefreshLayout();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void loadImg() {
            switch (id) {
                case 0:
                    banner.setBackgroundResource(R.mipmap.bookbg);
                    circleImg.setImageResource(R.mipmap.bookcircle);
                    circleTitle.setText("读书圈");
                    circleNotice.setText("想和你一起翻开这本书");
                    circleHuaTi.setText("说说你印象最深的一本书吧");
                    break;
                case 1:
                    banner.setBackgroundResource(R.mipmap.basketbg);
                    circleImg.setImageResource(R.mipmap.basketball);
                    circleTitle.setText("约球圈");
                    circleNotice.setText("basketball is amazing!");
                    circleHuaTi.setText("库里是否是真正的超级明星");
                    break;
                case 2:
                    banner.setBackgroundResource(R.mipmap.runbg);
                    circleImg.setImageResource(R.mipmap.run);
                    circleTitle.setText("约跑圈");
                    circleNotice.setText("生命在于跑跑跑");
                    circleHuaTi.setText("今天又快了多少秒");
                    break;
                case 3:
                    banner.setBackgroundResource(R.mipmap.moviebg);
                    circleImg.setImageResource(R.mipmap.movie);
                    circleTitle.setText("约影圈");
                    circleNotice.setText("想和我一起吃爆米花么");
                    circleHuaTi.setText("电影院的最佳位置在哪里");
                    break;
                case 4:
                    banner.setBackgroundResource(R.mipmap.foodbg);
                    circleImg.setImageResource(R.mipmap.food);
                    circleTitle.setText("约吃圈");
                    circleNotice.setText("唯有美食不可辜负");
                    circleHuaTi.setText("各地有什么代表性的美食呢");
                    break;
                case 5:
                    banner.setBackgroundResource(R.mipmap.chatbg);
                    circleImg.setImageResource(R.mipmap.qingsu);
                    circleTitle.setText("约聊圈");
                    circleNotice.setText("陌生人，听我唱一首歌？");
                    circleHuaTi.setText("进天你会讲怎样的故事");
                    break;
                case 6:
                    banner.setBackgroundResource(R.mipmap.chuyoubg);
                    circleImg.setImageResource(R.mipmap.chuyou);
                    circleTitle.setText("约玩圈");
                    circleNotice.setText("刚相逢也可玩到嗨起");
                    circleHuaTi.setText("你会和陌生人一起去坐过山车么");
                    break;
            }
    }


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

        mAdapter = new CircleAdapter(this, circleEntities);


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
        BmobQuery<CircleEntity> query = new BmobQuery<>();
        query.order("-createdAt"); // 按时间降序查询
        query.include("author,type"); // 希望在查询帖子信息的同时也把发布人的信息查询出来
        query.addWhereEqualTo("type", id);
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
        query.findObjects(new FindListener<CircleEntity>() {
            @Override
            public void done(List<CircleEntity> list, cn.bmob.v3.exception.BmobException e) {
                if (e==null) {
                    if (list.size()>0) {
                        if(actionType == STATE_REFRESH) {
                            currentPage = 0;
                            circleEntities.clear();
                        }
                        for (CircleEntity td : list) {
                            if (td.getType() == id)
                                circleEntities.add(td);
//                            Toast.makeText(getContext(), td.getUserEntity().getUser_name(), Toast.LENGTH_LONG).show();
                        }
                        currentPage++;
                        mAdapter.notifyDataSetChanged();
                        lastTime = circleEntities.get(circleEntities.size()-1).getCreatedAt(); // 获取最后时间
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

    @OnClick(R.id.button)
    public void Push() {
        Intent intent = new Intent(CircleDetail.this, PushCircle.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    /**
     * 显示加载时的Dialog
     */
    private void showPublishDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中..");
        progressDialog.setCancelable(false); // 设置点击Dialog以外的界面不消失，按返回键也不起作用
        progressDialog.show();
    }
}

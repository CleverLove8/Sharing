package com.example.liwensheng.sharing.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.adapter.MesCommentAdapter;
import com.example.liwensheng.sharing.adapter.MesGoodAdapter;
import com.example.liwensheng.sharing.entity.CommentEntity;
import com.example.liwensheng.sharing.entity.GoodEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.view.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liWensheng on 2017/3/12.
 */

public class MesComment extends AppCompatActivity {

    @BindView(R.id.comment_back_btn)
    LinearLayout back_btn;

    @BindView(R.id.mes_comment_refresh)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.mes_comment_listview)
    ListView listView;

    private MesCommentAdapter mAdapter;
    private boolean loading = false;
    private int currentPage = 0; //当前页面
    private List<CommentEntity> commentEntities = new ArrayList<>();

    private static final int STATE_REFRESH = 0; // 下拉刷新
    private static final int STATE_MORE = 1; // 加载更多
    private int limit = 5; // 每页的数据是10条
    private String lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_comment_layout);
        ButterKnife.bind(this);

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

    /**
     * 点击标题栏返回键
     * */
    @OnClick(R.id.comment_back_btn)
    public void goBack() {
        finish();
    }
    /**
     * 初始化RefreshLayout
     * 刷新页面
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

        mAdapter = new MesCommentAdapter(MesComment.this, commentEntities);


        if (mAdapter != null) {
            listView.setAdapter(mAdapter);
        }

    }

    /**
     * 分页获取数据
     * @param page 页码
     * @param actionType istView的操作类型（下拉刷新、上拉加载更多）
     */
    public void getData(final int page, final int actionType) {
        BmobQuery<CommentEntity> query = new BmobQuery<>();
        query.order("-createdAt"); // 按时间降序查询
        query.include("thingEntity,userEntity");
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
            public void done(List<CommentEntity> list, BmobException e) {
                if (e==null) {
                    if (list.size()>0) {
                        if(actionType == STATE_REFRESH) {
                            currentPage = 0;
                            commentEntities.clear();
                        }
                        for (CommentEntity td : list) {
                            if (td.getThingEntity().getAuthor().getObjectId()
                                    .equals(BmobUser.getCurrentUser(UserEntity.class).getObjectId())) {
                                commentEntities.add(td);
//                                Toast.makeText(MesComment.this, td.getUserEntity().getObjectId(), Toast.LENGTH_LONG).show();
                            }
                        }
                        currentPage++;
                        mAdapter.notifyDataSetChanged();
                        lastTime = commentEntities.get(commentEntities.size()-1).getCreatedAt(); // 获取最后时间
                        if (actionType == STATE_MORE) {
                            mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                        }
                    }
                    else if (actionType == STATE_MORE) {
//                        Toast.makeText(MesComment.this,"没有更多了", Toast.LENGTH_LONG).show();
                        mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                    }
                    else if (actionType == STATE_REFRESH) {
//                        Toast.makeText(MesComment.this,"没有数据", Toast.LENGTH_LONG).show();
                    }
                    loading = false;
                    mRefreshLayout.setRefreshing(false); // 请求完成结束刷新状态
                }

                else {
                    if (actionType == STATE_MORE) {
                        mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                    }
//                    Toast.makeText(MesComment.this,"请求异常，请稍后重试：" + e.toString(), Toast.LENGTH_LONG).show();
                    loading = false;
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

}

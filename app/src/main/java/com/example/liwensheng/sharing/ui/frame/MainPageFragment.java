package com.example.liwensheng.sharing.ui.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.liwensheng.sharing.R;
import com.example.liwensheng.sharing.adapter.ThingApdapter;
import com.example.liwensheng.sharing.base.BaseFragment;
import com.example.liwensheng.sharing.entity.ThingEntity;
import com.example.liwensheng.sharing.entity.UserEntity;
import com.example.liwensheng.sharing.ui.activity.DetailActivity;
import com.example.liwensheng.sharing.ui.activity.QueryActivity;
import com.example.liwensheng.sharing.ui.activity.TypeDetail;
import com.example.liwensheng.sharing.utils.ConstantUtils;
import com.example.liwensheng.sharing.view.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liWensheng on 2017/2/28.
 */

public class MainPageFragment extends BaseFragment implements SearchView.OnQueryTextListener , RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.eletronic)
    LinearLayout eletronic;
    @BindView(R.id.book)
    LinearLayout book;
    @BindView(R.id.dailyUse)
    LinearLayout dailyUse;
    @BindView(R.id.cloth)
    LinearLayout cloth;

    @BindView(R.id.horizontalScrollView)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.mainpage_group)
    RadioGroup radioGroup;
    @BindView(R.id.mainpage_qiujie)
    RadioButton qiujie;
    @BindView(R.id.mainpage_jiechu)
    RadioButton jiechu;

    @BindView(R.id.Refresh)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.listview)
    ListView listView;

    @BindView(R.id.searchView)
    SearchView searchView;

    private ArrayList<RefreshLayout> refreshList;
    private float mCurrentCheckedRadioLeft;//当前被选中的RadioButton距离左侧的距离

    private ThingApdapter mAdapter;
    private boolean loading = false;
    private int currentPage = 0; //当前页面
    private List<ThingEntity> thingEntities = new ArrayList<>();

    private static final int STATE_REFRESH = 0; // 下拉刷新
    private static final int STATE_MORE = 1; // 加载更多
    private int limit = 5; // 每页的数据是10条
    private String lastTime;

    private boolean selectFlag = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("主页");
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.mainpage_layout, null);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        qiujie.setChecked(true);

        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
        radioGroup.setOnCheckedChangeListener(this);
        // ListView条目点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThingEntity clickThing =  (ThingEntity)mAdapter.getItem(position);
                String thingId = clickThing.getObjectId();
                Intent intent = new Intent(getContext(), DetailActivity.class);
//                intent.putExtra("id", thingId);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thing", clickThing);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        initSearch();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initRefreshLayout();
    }

    /**
     * 点击分类跳转到相应的详情页面
     * */
    @OnClick({R.id.eletronic, R.id.book, R.id.dailyUse, R.id.cloth})
    public void typeGet(View view) {
        Intent intent = new Intent(getContext(), TypeDetail.class);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.eletronic:
                bundle.putInt("type", 1);
                break;
            case R.id.book:
                bundle.putInt("type", 2);
                break;
            case R.id.dailyUse:
                bundle.putInt("type", 3);
                break;
            case R.id.cloth:
                bundle.putInt("type", 4);
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        AnimationSet _AnimationSet = new AnimationSet(true);
        TranslateAnimation _TranslateAnimation;

        if (checkedId == R.id.mainpage_qiujie) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo1), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            selectFlag = true;
            initRefreshLayout();
        }
        else {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo1), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);
            selectFlag = false;
            initRefreshLayout();
        }

        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();//更新当前蓝色横条距离左边的距离
        horizontalScrollView.smoothScrollTo((int)mCurrentCheckedRadioLeft-(int)getResources().getDimension(R.dimen.rdo2), 0);
    }

    /**
     * 获得当前被选中的RadioButton距离左侧的距离
     */
    private float getCurrentCheckedRadioLeft() {
        // TODO Auto-generated method stub
        if (qiujie.isChecked()) {
            return getResources().getDimension(R.dimen.rdo1);
        }else if (jiechu.isChecked()) {
            return getResources().getDimension(R.dimen.rdo2);
        }
        return 0f;
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

        mAdapter = new ThingApdapter(getContext(), thingEntities);


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
        BmobQuery<ThingEntity> query = new BmobQuery<>();
        query.order("-createdAt"); // 按时间降序查询
        query.include("author"); // 希望在查询帖子信息的同时也把发布人的信息查询出来
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
        query.findObjects(new FindListener<ThingEntity>() {
            @Override
            public void done(List<ThingEntity> list, BmobException e) {
                if (e==null) {
                    if (list.size()>0) {
                        if(actionType == STATE_REFRESH) {
                            currentPage = 0;
                            thingEntities.clear();
                        }
                        for (ThingEntity td : list) {
                            if (td.getSend() == selectFlag) {
                                thingEntities.add(td);
                            }
                        }
                        currentPage++;
                        mAdapter.notifyDataSetChanged();
                        lastTime = thingEntities.get(thingEntities.size()-1).getCreatedAt(); // 获取最后时间
                        if (actionType == STATE_MORE) {
                            mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                        }
                    }
                    else if (actionType == STATE_MORE) {
                        Toast.makeText(getContext(),"没有更多了", Toast.LENGTH_LONG).show();
                        mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                    }
                    else if (actionType == STATE_REFRESH) {
                        Toast.makeText(getContext(),"没有数据", Toast.LENGTH_LONG).show();
                    }
                    loading = false;
                    mRefreshLayout.setRefreshing(false); // 请求完成结束刷新状态
                }

                else {
                    if (actionType == STATE_MORE) {
                        mRefreshLayout.setLoading(false); // 结束旋转ProgressBar
                    }
                    Toast.makeText(getContext(),"请求异常，请稍后重试：" + e.toString(), Toast.LENGTH_LONG).show();
                    loading = false;
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    public void clickListView() {

    }

    /**
     * 初始化搜索框
     * */
    private void initSearch() {
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
    }
    /**
     * 搜索监听器
     * @param  newText 搜索字段
     * */
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
    /**
     * 搜索结果处理
     * @param query 搜索内容
     * */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(getContext(), QueryActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 登录
            case ConstantUtils.RESULT_UPDATE_INFO:
                if (UserEntity.getCurrentUser()!=null) {
                    getData(0, STATE_REFRESH);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

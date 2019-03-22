package com.njz.letsgoappguides.ui.activites.settlement;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.mine.IncomeAdapter;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.emptyView.IncomeItemView;
import com.njz.letsgoappguides.model.settlement.IncomeInfo;
import com.njz.letsgoappguides.model.settlement.IncomeListInfo;
import com.njz.letsgoappguides.presenter.mine.GetIncomeContract;
import com.njz.letsgoappguides.presenter.mine.GetIncomePresenter;
import com.njz.letsgoappguides.ui.activites.mine.BindIdcarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;


/**
 * 已结算订单列表
 */
public class MyIncomeActivity extends BaseActivity implements GetIncomeContract.View {


    GetIncomePresenter mGetIncomePresenter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    IncomeItemView view_empty4;
    IncomeAdapter mAdapter;
    LoadMoreWrapper loadMoreWrapper;
    TextView tvIncome;
    TextView tvAllincome;
    private boolean isLoad;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    int page;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_income;
    }

    @Override
    protected void initView() {

//        ButterKnife.bind(this);

        tvIncome=$(R.id.tv_income);
        recyclerView=$(R.id.recycler_view);
        tvAllincome=$(R.id.tv_allincome);
        swipeRefreshLayout=$(R.id.swipe_refresh_layout);
        view_empty4=$(R.id.view_empty4);
        initRecycler();
        initSwipeLayout();

        mGetIncomePresenter = new GetIncomePresenter(this, context);

        getRefreshData();
    }

    public void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mAdapter = new IncomeAdapter(context, new ArrayList<IncomeListInfo>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//itemChanged 闪烁问题

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mAdapter.setOnItemClickListener(new IncomeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int orderId,int refId,float priceA,float priceB) {
                if(refId==0){
                    //已完成
                    Intent intent = new Intent(context, OrderDetailIncomeActivity.class);
                    intent.putExtra("ORDER_ID",orderId);
                    intent.putExtra("PRICE_A",priceA);
                    intent.putExtra("PRICE_B",priceB);
                    intent.putExtra("ID",10);
                    startActivity(intent);
                }else{
                    //已退款
                    Intent intent = new Intent(context, OrderDetailRefundsActivity.class);
                    intent.putExtra("ORDER_ID",refId);
                    intent.putExtra("ID",10);
                    startActivity(intent);
                }
            }
        });

    }

    //初始化SwipeLayout
    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResColor(R.color.color_theme));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoad) return;
                getRefreshData();
            }
        });
    }

    private void getRefreshData() {
        swipeRefreshLayout.setRefreshing(true);
        isLoad = true;
        page = Constant.DEFAULT_PAGE;
        isLoadType = 1;
        initIncomeInfo();
    }

    private void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        initIncomeInfo();
    }

    void initIncomeInfo() {
        mGetIncomePresenter.getIncomeInfo(page, Constant.DEFAULT_LIMIT);
    }


    @OnClick(R.id.left_iv)
    public void leftBack() {
        finish();
    }


    @Override
    public void getIncomeSuccess(IncomeInfo datainfo) {
        if(datainfo==null) return;

            tvIncome.setText("￥" + datainfo.getTotalYield());
            tvAllincome.setText("￥" + datainfo.getAwaitTotalMoney());
            if (datainfo.getPageUtils() != null&&datainfo.getPageUtils().getList()!=null) {
                List<IncomeListInfo> datas = datainfo.getPageUtils().getList();
                if (isLoadType == 1) {
                    mAdapter.setData(datas);
                } else {
                    mAdapter.addData(datas);
                }
                isLoad = false;
                if (datas.size() >= Constant.DEFAULT_LIMIT) {
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
                } else {
                    // 显示加载到底的提示
                    loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
                }
                if (datas.size() == 0) {
                    view_empty4.setVisible(true);
                }
            }

        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void getIncomeFailed(String msg) {
        Log.e("test", msg);
        if("沒有查到結果".equals(msg)){//新账户没有数据进入这
            view_empty4.setVisible(true);
        }
        isLoad = false;
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);


//        if(msg.startsWith("-")){
//            view_empty.setVisible(true);
//            view_empty.setEmptyData(R.mipmap.empty_network, "网络竟然崩溃了", "别紧张，试试看刷新页面~", "点击刷新");
//            view_empty.setBtnClickLisener(new EmptyClickLisener() {
//                @Override
//                public void onClick() {
//                    getRefreshData();
//                }
//            });
//        }
    }


    @OnClick({R.id.tv_right_bind,R.id.tv_details})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_right_bind:
                if (MySelfInfo.getInstance().isLogin()) {
                    if (""!=MySelfInfo.getInstance().getCardViable() ){
                        int cardViable=Integer.parseInt(MySelfInfo.getInstance().getCardViable());
                        if(cardViable!=2){
                            showShortToast("必须实名认证成功之后才可以绑定账户!");
                        }else{
                            startActivity(new Intent(context, BindIdcarActivity.class));
                        }
                    }
                }
                break;
            case R.id.tv_details:
                startActivity(new Intent(context, SettlementOrderActivity.class));
                break;
        }

    }

}

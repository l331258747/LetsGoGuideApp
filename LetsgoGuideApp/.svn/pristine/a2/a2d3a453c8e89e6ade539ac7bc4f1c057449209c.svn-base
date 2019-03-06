package com.njz.letsgoappguides.ui.activites.mine;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.mine.OrderSettleListAdapter;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyClickLisener;
import com.njz.letsgoappguides.model.mine.OrderSettleModel;
import com.njz.letsgoappguides.presenter.mine.OrderSettleListContract;
import com.njz.letsgoappguides.presenter.mine.OrderSettleListPresenter;
import com.njz.letsgoappguides.ui.activites.home.*;
import com.njz.letsgoappguides.ui.activites.home.OrderListActivity;

import java.util.ArrayList;
import java.util.List;

public class SettlementOrderActivity extends OrderListActivity implements OrderSettleListContract.View {

    OrderSettleListPresenter settlePresenter;
    OrderSettleListAdapter mAdapter;

    @Override
    public void initData() {
        titleView.getTitleTv().setText("待结算订单");

        settlePresenter = new OrderSettleListPresenter(context, this);
        getRefreshData();
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new OrderSettleListAdapter(activity, new ArrayList<OrderSettleModel>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        page = Constant.DEFAULT_PAGE;

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mAdapter.setOnItemClickListener(new OrderSettleListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int orderId,int status) {
                Log.e("test","____________"+orderId);
                switch (status){//1已退款 2、已完成
                    case 1:
                        //已退款
                        Intent intent = new Intent(context, OrderDetailRefundsActivity.class);
                        intent.putExtra("ORDER_ID",orderId);
                        intent.putExtra("ID",11);
                        startActivity(intent);
                        break;
                    case 2:
                        //已完成
                        Intent intent2 = new Intent(context, OrderDetailIncomeActivity.class);
                        intent2.putExtra("ORDER_ID",orderId);
                        intent2.putExtra("ID",11);
                        startActivity(intent2);
                        break;
                }
            }
        });


    }

    public void getList() {
        settlePresenter.orderSettleList(page, Constant.DEFAULT_LIMIT,  "");
    }

    @Override
    public void orderSettleListSuccess(List<OrderSettleModel> data) {
        List<OrderSettleModel> datas = data;
        Log.e("test","orderSettleListSuccess"+datas.toString());
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
        swipeRefreshLayout.setRefreshing(false);

        if(mAdapter.getData().size() == 0){
            emptyView.setVisible(true);
            emptyView.setEmptyData(R.mipmap.empty_order,"这里还是空空哒~");
        }else{
            emptyView.setVisible(false);
        }

    }

    @Override
    public void orderSettleListFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);

        if(msg.startsWith("-")){
            emptyView.setVisible(true);
            emptyView.setEmptyData(R.mipmap.empty_network, "网络竟然崩溃了", "别紧张，试试看刷新页面~", "点击刷新");
            emptyView.setBtnClickLisener(new EmptyClickLisener() {
                @Override
                public void onClick() {
                    getRefreshData();
                }
            });
        }
    }

}

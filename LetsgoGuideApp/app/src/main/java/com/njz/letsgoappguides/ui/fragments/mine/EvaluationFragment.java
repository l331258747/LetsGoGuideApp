package com.njz.letsgoappguides.ui.fragments.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.mine.DynamicAdapter;
import com.njz.letsgoappguides.base.BaseFragment;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyClickLisener;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView;
import com.njz.letsgoappguides.customview.widget.myTextView.ShowAllTextView;
import com.njz.letsgoappguides.model.evaluation.Datainfo;
import com.njz.letsgoappguides.model.evaluation.ReviewList;
import com.njz.letsgoappguides.presenter.mine.MyEvaluationContract;
import com.njz.letsgoappguides.presenter.mine.MyEvaluationPresenter;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalContract;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalPresenter;
import com.njz.letsgoappguides.ui.activites.home.OrderDetailActivity;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshEvaluateListEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class EvaluationFragment extends BaseFragment  implements MyEvaluationContract.View,ReviewEvalContract.View{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
//    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
//    @BindView(R.id.view_empty)
    EmptyView view_empty;

    public static final int DYNAMIC_TYPE = 0;//全部
    public static final int DYNAMIC_TYPE1 = 1;//好评
    public static final int DYNAMIC_TYPE2 = 2;//中评
    public static final int DYNAMIC_TYPE3 = 3;//差评
    public static final int DYNAMIC_TYPE4 = 4;
    private int type;

    DynamicAdapter mAdapter;
    LoadMoreWrapper loadMoreWrapper;

    private boolean isLoad;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    int page;

    ReviewEvalPresenter mReviewEvalPresenter;
    MyEvaluationPresenter mMyEvaluationPresenter;
    int pos;

    String editTextContext;

    Disposable refreshDisposable;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(refreshDisposable !=null && !refreshDisposable.isDisposed()){
            refreshDisposable.dispose();
        }
    }

    private void initRefreshDisposable() {
        refreshDisposable = RxBus2.getInstance().toObservable(RefreshEvaluateListEvent.class, new Consumer<RefreshEvaluateListEvent>() {
            @Override
            public void accept(RefreshEvaluateListEvent refreshEvaluateListEvent) throws Exception {
                getRefreshData();
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_evaluation;
    }

    /**
     * 传入需要的参数，设置给arguments
     *
     * @param type
     * @return
     */
    public static EvaluationFragment newInstance(int type) {
        EvaluationFragment mEvaluationFragment=new EvaluationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        mEvaluationFragment.setArguments(bundle);
        return mEvaluationFragment;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null)
            type = bundle.getInt("type");

        swipeRefreshLayout=$(R.id.swipe_refresh_layout);
        view_empty=$(R.id.view_empty);
        initRecycler();
        initSwipeLayout();

        initData();
    }

    public void initData(){
        mMyEvaluationPresenter=new MyEvaluationPresenter(this,context);
        mReviewEvalPresenter=new ReviewEvalPresenter(this,context);

        getRefreshData();

        initRefreshDisposable();
    }

    public void initRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new DynamicAdapter(mActivity, new ArrayList<ReviewList>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//itemChanged 闪烁问题

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mAdapter.setOnItemClickListener(new DynamicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position, final String userId, final int id, final int orderId,final TextView contentBack) {
                    final EditText editText = new EditText(context);
                    new AlertDialog.Builder(context).setTitle("请输入回复内容").setView(editText).setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    pos=position;
                                    if(editText.getText().toString().trim().equals("")){
                                        ToastUtil.showShortToast(context,"内容回复不能为空");
                                    }else if(null==userId){ }else if(id==0){}else if(orderId==0){}else{
                                        editTextContext=editText.getText().toString().trim();
                                        mReviewEvalPresenter.getReviewEval(editTextContext,userId,id,orderId);
                                        contentBack.setText(editTextContext);
                                    }
                                }
                            }).setNegativeButton("取消", null).show();
            }

        });

        mAdapter.setOnDetailClickListener(new DynamicAdapter.OnDetailClickListener() {
            @Override
            public void onItemClick(int orderId) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("ORDER_ID",orderId);
                startActivity(intent);
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
        initEvalInfo();
    }

    private void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        initEvalInfo();
    }

    @Override
    public void loadData() {

    }

    @Override
    public void getEvaluationFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);

        if(msg.startsWith("-")){
            view_empty.setVisible(true);
            view_empty.setEmptyData(R.mipmap.empty_network, "网络竟然崩溃了", "别紧张，试试看刷新页面~", "点击刷新");
            view_empty.setBtnClickLisener(new EmptyClickLisener() {
                @Override
                public void onClick() {
                    getRefreshData();
                }
            });
        }
    }

    @Override
    public void getEvaluationSuccess(Datainfo datainfo) {
        List<ReviewList> datas = datainfo.getReviewList();
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

        if(mAdapter.getDatas().size() == 0){
            view_empty.setVisible(true);
            view_empty.setEmptyData(R.mipmap.empty_evaluate,"还没有人给您评价哦~");
        }else{
            view_empty.setVisible(false);
        }

    }


    private void initEvalInfo(){
        switch (type){
            case DYNAMIC_TYPE://page, limit, type
                mMyEvaluationPresenter.getEvaluationInfo(page,Constant.DEFAULT_LIMIT,DYNAMIC_TYPE);
                break;
            case DYNAMIC_TYPE1:
                mMyEvaluationPresenter.getEvaluationInfo(page,Constant.DEFAULT_LIMIT,DYNAMIC_TYPE1);
                break;
            case DYNAMIC_TYPE2:
                mMyEvaluationPresenter.getEvaluationInfo(page,Constant.DEFAULT_LIMIT,DYNAMIC_TYPE2);
                break;
            case DYNAMIC_TYPE3:
                mMyEvaluationPresenter.getEvaluationInfo(page,Constant.DEFAULT_LIMIT,DYNAMIC_TYPE3);
                break;
            case DYNAMIC_TYPE4:
                mMyEvaluationPresenter.getEvaluationInfo(page,Constant.DEFAULT_LIMIT,DYNAMIC_TYPE4);
                break;
        }
    }

    @Override
    public void getReviewEvalSuccess(String str) {
        ToastUtil.showShortToast(context,"评论成功");
        Log.e("test",pos+"__________");
        mAdapter.getItem(pos).setGuideContent(editTextContext);
        mAdapter.getItem(pos).setGuideDate();
        loadMoreWrapper.notifyItemChanged(pos);
    }

    @Override
    public void getReviewEvalFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }

}

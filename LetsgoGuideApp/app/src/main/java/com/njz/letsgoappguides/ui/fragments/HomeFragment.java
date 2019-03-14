package com.njz.letsgoappguides.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.mine.DynamicAdapter;
import com.njz.letsgoappguides.base.BaseFragment;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView2;
import com.njz.letsgoappguides.model.evaluation.ReviewList;
import com.njz.letsgoappguides.model.home.HomeCountModel;
import com.njz.letsgoappguides.model.home.HomeModel;
import com.njz.letsgoappguides.model.other.BannerModel;
import com.njz.letsgoappguides.presenter.home.HomeContract;
import com.njz.letsgoappguides.presenter.home.HomePresenter;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalContract;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalPresenter;
import com.njz.letsgoappguides.ui.activites.home.OrderDetailActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderListActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderListRefundActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderListTabActivity;
import com.njz.letsgoappguides.ui.activites.mine.MyEvaluationActivity;
import com.njz.letsgoappguides.ui.activites.mine.SettlementOrderActivity;
import com.njz.letsgoappguides.ui.activites.mysetting.AuthenticationActivity;
import com.njz.letsgoappguides.ui.activites.other.WebViewActivity;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.banner.LocalImageHolderView;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshEvaluateListEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomeFragment extends BaseFragment implements HomeContract.View, ReviewEvalContract.View {


    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.tv_guide_title)
    TextView tvGuideTitle;
    @BindView(R.id.rl_evaluate)
    RelativeLayout rlEvaluate;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_today)
    TextView tvToday;
    @BindView(R.id.tv_balance_no)
    TextView tvBalanceNo;
    @BindView(R.id.tv_pay_no)
    TextView tvPayNo;
    @BindView(R.id.tv_submit_no)
    TextView tvSubmitNo;
    @BindView(R.id.tv_submit_yes)
    TextView tvSubmitYes;
    @BindView(R.id.tv_refund)
    TextView tvRefund;

    boolean isLoad = false;
    @BindView(R.id.ll_pay_no)
    LinearLayout llPayNo;
    @BindView(R.id.ll_submit_no)
    LinearLayout llSubmitNo;
    @BindView(R.id.ll_submit_yes)
    LinearLayout llSubmitYes;
    @BindView(R.id.ll_refund)
    LinearLayout llRefund;
    @BindView(R.id.view_empty)
    EmptyView2 emptyView;
    @BindView(R.id.ll_home_allOrder)
    LinearLayout llHomeAllOrder;
    @BindView(R.id.ll_home_dayOrder)
    LinearLayout llHomeDayOrder;
    @BindView(R.id.ll_home_nofinshOrder)
    LinearLayout llHomeNofinshOrder;

    DynamicAdapter evaluateAdapter;
    int pos;
    String editTextContext;
    HomePresenter mPresenter;
    ReviewEvalPresenter evalPresenter;

    Disposable refreshDisposable;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        initRecycler();
        initSwipeLayout();

        mPresenter = new HomePresenter(context, this);
        evalPresenter = new ReviewEvalPresenter(this, context);

        initAuthentication();

        initRefreshDisposable();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (refreshDisposable != null && !refreshDisposable.isDisposed()) {
            refreshDisposable.dispose();
        }
    }

    private void initRefreshDisposable() {
        refreshDisposable = RxBus2.getInstance().toObservable(RefreshEvaluateListEvent.class, new Consumer<RefreshEvaluateListEvent>() {
            @Override
            public void accept(RefreshEvaluateListEvent refreshEvaluateListEvent) throws Exception {
                getData();
            }
        });
    }

    private void initAuthentication() {
        if (TextUtils.equals(MySelfInfo.getInstance().getCardViable(), "0") || TextUtils.equals(MySelfInfo.getInstance().getCardViable(), "-1")) {
            DialogUtil.getInstance().getDefaultDialog(context, "实名认证提醒", "完成实名认证后，才能发布相关服务内容，赶快去认证吧！", "去认证", new DialogUtil.DialogCallBack() {
                @Override
                public void exectEvent(DialogInterface alterDialog) {
                    startActivity(new Intent(context, AuthenticationActivity.class));
                }
            }).show();
        }
    }

    //初始化recyclerview
    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        evaluateAdapter = new DynamicAdapter(context, new ArrayList<ReviewList>());
        recyclerView.setAdapter(evaluateAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//itemChanged 闪烁问题

        evaluateAdapter.setOnDetailClickListener(new DynamicAdapter.OnDetailClickListener() {
            @Override
            public void onItemClick(int orderId) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("ORDER_ID", orderId);
                startActivity(intent);
            }
        });

        evaluateAdapter.setOnItemClickListener(new DynamicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position, final String userId, final int id, final int orderId, final TextView contentBack) {
                final EditText editText = new EditText(context);
                new AlertDialog.Builder(context).setTitle("请输入回复内容").setView(editText).setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pos = position;
                                if (editText.getText().toString().trim().equals("")) {
                                    ToastUtil.showShortToast(context, "内容回复不能为空");
                                } else if (null == userId) {
                                } else if (id == 0) {
                                } else if (orderId == 0) {
                                } else {
                                    editTextContext = editText.getText().toString().trim();
                                    evalPresenter.getReviewEval(editTextContext, userId, id, orderId);
                                    contentBack.setText(editTextContext);
                                }
                            }
                        }).setNegativeButton("取消", null).show();
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
                isLoad = true;
                getData();
            }
        });
    }

    public void getData() {
        swipeRefreshLayout.setRefreshing(true);
        mPresenter.orderIndex();
    }

    @Override
    public void loadData() {

    }


    @Override
    public void orderIndexSuccess(HomeModel datas) {
        swipeRefreshLayout.setRefreshing(false);
        isLoad = false;
        initBanner(datas.getNjzBannerList());
        initCount(datas.getNjzOrderCounts());
        initEvaluation(datas.getTravelOrderReview());
    }

    private void initEvaluation(List<ReviewList> datas) {
        evaluateAdapter.setData(datas);

        if (datas.size() == 0) {
            emptyView.setVisible(true);
            emptyView.setEmptyData(R.mipmap.empty_follow, "这里还是空空哒~");
            emptyView.setEmptyBackground(R.color.white);
        } else {
            emptyView.setVisible(false);
        }

    }

    @Override
    public void orderIndexFailed(String msg) {
        showShortToast(msg);
        swipeRefreshLayout.setRefreshing(false);
        isLoad = false;
    }

    public void initCount(HomeCountModel counts) {
        tvTotal.setText(counts.getTotalCount());
        tvToday.setText(counts.getTodayCount());
        tvBalanceNo.setText(counts.getBeBalancedCount());
        tvPayNo.setVisibility(TextUtils.isEmpty(counts.getBeforePayCount()) ? View.GONE : View.VISIBLE);
        tvSubmitNo.setVisibility(TextUtils.isEmpty(counts.getBeforeSureCount()) ? View.GONE : View.VISIBLE);
        tvSubmitYes.setVisibility(View.GONE);
        tvRefund.setVisibility(TextUtils.isEmpty(counts.getRefundCount()) ? View.GONE : View.VISIBLE);
    }

    //----------banner start
    public void initBanner(final List<BannerModel> homeBanners) {
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView(new LocalImageHolderView.BannerListener<BannerModel>() {

                    @Override
                    public void bannerListener(Context context, int position, BannerModel data, ImageView view) {
                        GlideUtil.LoadImage(context, data.getImgUrl(), view);
                    }
                });
            }
        }, homeBanners)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (TextUtils.isEmpty(homeBanners.get(position).getToUrl())) return;
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra(Constant.EXTRA_URL, homeBanners.get(position).getToUrl());
                        intent.putExtra(Constant.EXTRA_TITLE, "那就走");
                        startActivity(intent);
                    }
                })
                .setPointViewVisible(true) //设置指示器是否可见
                .setPageIndicator(new int[]{R.drawable.oval_white_hollow, R.drawable.oval_theme_solid})//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方向（左、中、右）
                .setManualPageable(true);//设置手动影响（设置了该项无法手动切换）设置为false后手点击后 停止轮播了
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(Constant.BANNER_RUNNING_TIME);

        if (getUserVisibleHint() && isPrepared) {
            getData();
        }
    }

    //----------banner end

    @OnClick({R.id.ll_pay_no, R.id.ll_submit_no, R.id.ll_submit_yes, R.id.ll_refund, R.id.rl_evaluate,R.id.ll_home_allOrder, R.id.ll_home_dayOrder, R.id.ll_home_nofinshOrder})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_pay_no:
                intent = new Intent(context, OrderListActivity.class);
                intent.putExtra("PAY_STATUS", Constant.ORDER_PAY_WAIT);
                intent.putExtra("ORDER_STATUS", 0);
                startActivity(intent);
                break;
            case R.id.ll_submit_no:
                intent = new Intent(context, OrderListActivity.class);
                intent.putExtra("PAY_STATUS", Constant.ORDER_PAY_ALREADY);
                intent.putExtra("ORDER_STATUS", Constant.ORDER_TRAVEL_WAIT);
                startActivity(intent);
                break;
            case R.id.ll_submit_yes:
                intent = new Intent(context, OrderListTabActivity.class);
                intent.putExtra("PAY_STATUS", Constant.ORDER_PAY_ALREADY);
                intent.putExtra("ORDER_STATUS", Constant.ORDER_TRAVEL_NO_GO);
                startActivity(intent);
                break;
            case R.id.ll_refund:
                intent = new Intent(context, OrderListRefundActivity.class);
                intent.putExtra("PAY_STATUS", Constant.ORDER_PAY_REFUND);
                intent.putExtra("ORDER_STATUS", 0);
                startActivity(intent);
                break;
            case R.id.rl_evaluate:
                startActivity(new Intent(context, MyEvaluationActivity.class));
                break;
            case R.id.ll_home_allOrder:
                intent=new Intent(context,OrderListActivity.class);
                intent.putExtra("PAY_STATUS",Constant.ORDER_PAY_ALL);
                startActivity(intent);
                break;
            case R.id.ll_home_dayOrder:
                intent=new Intent(context,OrderListActivity.class);
                intent.putExtra("PAY_STATUS",Constant.ORDER_PAY_TOTAL);
                startActivity(intent);
                break;
            case R.id.ll_home_nofinshOrder:
                startActivity(new Intent(context, SettlementOrderActivity.class));
                break;
        }
    }

    @Override
    public void getReviewEvalSuccess(String str) {
        ToastUtil.showShortToast(context, "评论成功");
        evaluateAdapter.getItem(pos).setGuideContent(editTextContext);
        evaluateAdapter.getItem(pos).setGuideDate();
        evaluateAdapter.notifyItemChanged(pos);
    }

    @Override
    public void getReviewEvalFailed(String msg) {
        ToastUtil.showShortToast(context, msg);
    }

}

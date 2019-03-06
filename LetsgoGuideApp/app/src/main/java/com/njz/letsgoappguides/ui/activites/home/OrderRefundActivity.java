package com.njz.letsgoappguides.ui.activites.home;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.home.OrderRefundAdapter;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.FixedItemTextView;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.model.home.OrderRefundDetailChildModel;
import com.njz.letsgoappguides.model.home.OrderRefundDetailModel;
import com.njz.letsgoappguides.model.send.SendOrderRefundChildModel;
import com.njz.letsgoappguides.presenter.home.OrderRefundContract;
import com.njz.letsgoappguides.presenter.home.OrderRefundDetailContract;
import com.njz.letsgoappguides.presenter.home.OrderRefundDetailPresenter;
import com.njz.letsgoappguides.presenter.home.OrderRefundPresenter;
import com.njz.letsgoappguides.util.DecimalUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.OrderRefundEvent;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshOrderListEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LGQ
 * Time: 2018/11/15
 * Function: 确认退款
 */

public class OrderRefundActivity extends BaseActivity implements OrderRefundDetailContract.View,OrderRefundContract.View {
    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.tv_guide_name)
    TextView tvGuideName;
    @BindView(R.id.tv_countdown)
    TextView tvCountdown;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.login_view_name)
    FixedItemTextView loginViewName;
    @BindView(R.id.login_view_phone)
    FixedItemTextView loginViewPhone;
    @BindView(R.id.fixed_view_city)
    TextView fixedViewCity;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_refund_time)
    TextView tvRefundTime;
    @BindView(R.id.tv_refund_reason)
    TextView tvRefundReason;
    @BindView(R.id.tv_refund_explain)
    TextView tvRefundExplain;
    @BindView(R.id.cv_refund_reason)
    FrameLayout cvRefundReason;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.rl_order_price)
    RelativeLayout rlOrderPrice;
    @BindView(R.id.tv_refund_used_price)
    TextView tvRefundUsedPrice;
    @BindView(R.id.rl_refund_used_price)
    RelativeLayout rlRefundUsedPrice;
    @BindView(R.id.tv_refund_penalty)
    TextView tvRefundPenalty;
    @BindView(R.id.rl_refund_penalty)
    RelativeLayout rlRefundPenalty;
    @BindView(R.id.tv_refund_price)
    TextView tvRefundPrice;
    @BindView(R.id.rl_refund_price)
    RelativeLayout rlRefundPrice;
    @BindView(R.id.iv_checkbox)
    ImageView ivCheckbox;
    @BindView(R.id.rl_checkbox)
    LinearLayout rlCheckbox;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.fixed_view_personl)
    FixedItemTextView fixed_view_personl;

    int refundId;
    OrderRefundDetailPresenter refundPresenter;
    OrderRefundPresenter refundPresenter2;

    OrderRefundAdapter refundAdapter;

    OrderRefundDetailModel datas;

    boolean isAllCheck;

    Disposable orderRefundDisposable;

    @Override
    protected void initView() {
        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        refundId = getIntent().getIntExtra("REFUND_ID", 0);

        Log.e("test","refundId"+refundId);

        cvRefundReason.setVisibility(View.VISIBLE);
        rlRefundPrice.setVisibility(View.VISIBLE);
        rlRefundPenalty.setVisibility(View.VISIBLE);
        rlRefundUsedPrice.setVisibility(View.GONE);

        initRecycler();

        initData();
    }

    public void initData() {
        refundPresenter = new OrderRefundDetailPresenter(context, this);
        refundPresenter2 = new OrderRefundPresenter(context, this);

        refundPresenter.orderQueryRefundOrder(refundId);

        initDisposable();

    }

    private void initDisposable() {
        orderRefundDisposable = RxBus2.getInstance().toObservable(OrderRefundEvent.class, new Consumer<OrderRefundEvent>() {
            @Override
            public void accept(OrderRefundEvent orderRefundEvent) throws Exception {
                float defaulMoney = 0;
                float refundMoney = 0;
                for (int i = 0; i < datas.getNjzRefundDetailsChildVOS().size(); i++) {
                    if(!datas.getNjzRefundDetailsChildVOS().get(i).isCheck()){
                        defaulMoney = DecimalUtil.add(defaulMoney, datas.getNjzRefundDetailsChildVOS().get(i).getDefaultMoney());
                        refundMoney = DecimalUtil.add(refundMoney, datas.getNjzRefundDetailsChildVOS().get(i).getRefundMoney());
                    }else {
                        defaulMoney = DecimalUtil.add(defaulMoney, 0);
                        refundMoney = DecimalUtil.add(refundMoney, datas.getNjzRefundDetailsChildVOS().get(i).getOrderPrice());
                    }
                }

                tvRefundPenalty.setText(defaulMoney + "");
                tvRefundPrice.setText(refundMoney + "");

                setAllCheckBtn();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(orderRefundDisposable !=null && !orderRefundDisposable.isDisposed()){
            orderRefundDisposable.dispose();
        }
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        refundAdapter = new OrderRefundAdapter(activity, new ArrayList<OrderRefundDetailChildModel>());
        recyclerView.setAdapter(refundAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        refundAdapter.setOnCheckClickListener(new OrderRefundAdapter.OnCheckClickListener() {
            @Override
            public void onCheckClick() {
                RxBus2.getInstance().post(new OrderRefundEvent());
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_refund;
    }

    @Override
    public void orderQueryRefundOrderSuccess(OrderRefundDetailModel datas) {
        this.datas = datas;

        //信息
        tvGuideName.setText(datas.getOrderNo());
        tvOrderStatus.setText(datas.getPayStatusStr());
        tvCountdown.setVisibility(View.VISIBLE);
        tvCountdown.setText(datas.getSureTime());

        fixedViewCity.setText(datas.getLocation());
        loginViewName.setContent(datas.getName());
        loginViewPhone.setContent(datas.getMobile());
        fixed_view_personl.setContent(datas.getPersonNum()+"");

        tvOrderPrice.setText(datas.getOrderPrice() + "");
        tvRefundPenalty.setText(datas.getDefaultMoney() + "");
        tvRefundPrice.setText(datas.getRefundMoney() + "");


        //已消费金额
        boolean isTravelGoing = false;
        for (int i = 0; i < datas.getNjzRefundDetailsChildVOS().size(); i++) {
            if (datas.getNjzRefundDetailsChildVOS().get(i).getChildOrderStatus() == Constant.ORDER_TRAVEL_GOING) {
                isTravelGoing = true;
            }
        }
        if (isTravelGoing) {
            rlRefundUsedPrice.setVisibility(View.VISIBLE);
            tvRefundUsedPrice.setText(datas.getUseMoney() + "");
        } else {
            rlRefundUsedPrice.setVisibility(View.GONE);
        }

        tvRefundTime.setText(datas.getApplyTime());
        tvRefundReason.setText(datas.getRefundReason());
        tvRefundExplain.setText(datas.getRefundContent());

        refundAdapter.setData(datas.getNjzRefundDetailsChildVOS());

        setAllCheckBtn();

    }

    //设置全选按钮状态
    public void setAllCheckBtn(){
        if(datas.getNjzRefundDetailsChildVOS() == null || datas.getNjzRefundDetailsChildVOS().size() == 0) return;
        for (int i = 0; i < datas.getNjzRefundDetailsChildVOS().size(); i++) {

            if(datas.getNjzRefundDetailsChildVOS().get(i).isPlatformCancel()){
                datas.getNjzRefundDetailsChildVOS().get(i).setCheck(true);
            }

            if(!datas.getNjzRefundDetailsChildVOS().get(i).isCheck()){
                ivCheckbox.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_checkbox_un));
                isAllCheck = false;
                return;
            }
        }
        isAllCheck = true;
        ivCheckbox.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_checkbox));
    }

    @Override
    public void orderQueryRefundOrderFailed(String msg) {
        showShortToast(msg);
    }

    @OnClick({R.id.rl_checkbox, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_checkbox:
                isAllCheck = !isAllCheck;
                ivCheckbox.setImageDrawable(ContextCompat.getDrawable(context,isAllCheck?R.mipmap.ic_checkbox:R.mipmap.ic_checkbox_un));
                for (int i = 0; i < datas.getNjzRefundDetailsChildVOS().size(); i++) {
                    if(datas.getNjzRefundDetailsChildVOS().get(i).isPlatformCancel()){
                        //平台扣除违约金代表默认选中
                        datas.getNjzRefundDetailsChildVOS().get(i).setCheck(true);
                    }else{
                        datas.getNjzRefundDetailsChildVOS().get(i).setCheck(isAllCheck);
                    }
                }
                refundAdapter.notifyDataSetChanged();
                RxBus2.getInstance().post(new OrderRefundEvent());
                break;
            case R.id.btn_submit:
                if(datas.getNjzRefundDetailsChildVOS() == null || datas.getNjzRefundDetailsChildVOS().size() == 0)
                    return;
                List<SendOrderRefundChildModel> lists  = new ArrayList<>();
                for (int i = 0; i < datas.getNjzRefundDetailsChildVOS().size(); i++) {
                    SendOrderRefundChildModel item = new SendOrderRefundChildModel(datas.getNjzRefundDetailsChildVOS().get(i).getChildRefundId()
                            ,datas.getNjzRefundDetailsChildVOS().get(i).isCheck()?1:0);
                    lists.add(item);
                }

                Log.e("test","lists"+lists.toString());
                refundPresenter2.orderSureRefundOrder(lists);
                break;
        }
    }

    @Override
    public void orderSureRefundOrderSuccess(String datas) {
        finish();
        RxBus2.getInstance().post(new RefreshOrderListEvent());
    }

    @Override
    public void orderSureRefundOrderFailed(String msg) {
        showShortToast(msg);
    }
}

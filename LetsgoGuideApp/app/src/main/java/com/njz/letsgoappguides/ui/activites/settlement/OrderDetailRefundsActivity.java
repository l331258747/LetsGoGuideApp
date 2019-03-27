package com.njz.letsgoappguides.ui.activites.settlement;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.settlement.OrderIncomeDetail2Adapter;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderDetailModel;
import com.njz.letsgoappguides.model.settlement.NjzOrderRefundEntityBean;
import com.njz.letsgoappguides.model.settlement.NjzRefundDetailsChildVOSBean;
import com.njz.letsgoappguides.model.settlement.OrderSettleBalanceChildModel;
import com.njz.letsgoappguides.model.settlement.OrderSettltRefundChildModel;
import com.njz.letsgoappguides.presenter.mine.SettleOrderDetailContract;
import com.njz.letsgoappguides.presenter.mine.SettleOrderDetailPresenter;
import com.njz.letsgoappguides.ui.activites.home.CustomPlanActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderDetailActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderRefundActivity;
import com.njz.letsgoappguides.util.dialog.DialogUtil;

import java.util.ArrayList;


/**
 * 待结算、已结算详细页面 - 退款单
 */
public class OrderDetailRefundsActivity extends OrderDetailActivity implements SettleOrderDetailContract.View{

    SettleOrderDetailPresenter mSettleOrderDetailPresenter;
    OrderIncomeDetail2Adapter refundAdapter2;

    OrderDetailModel refundModel;

    RelativeLayout rl_refund_penalty,rl_refund_price,rl_order_price,rl_refund_used_price;
    TextView tv_refund_penalty,tv_refund_price,tv_refund_used_price;
    FrameLayout cv_refund_reason;
    TextView tv_refund_reason,tv_refund_explain;

    int id;
    float priceA;
    float priceB;
    String DateC;

    @Override
    public void initData() {
        id = getIntent().getIntExtra("ID",0);
        priceA = getIntent().getFloatExtra("PRICE_A",0);
        priceB = getIntent().getFloatExtra("PRICE_B",0);
        DateC = getIntent().getStringExtra("DATE_C");

        mSettleOrderDetailPresenter = new SettleOrderDetailPresenter(this,context);
        mSettleOrderDetailPresenter.querySettlefulDetail(orderId);//已退款

        Log.e("test","已退款"+orderId);
    }

    @Override
    public void initView() {
        super.initView();
        rl_refund_penalty = $(R.id.rl_refund_penalty);
        rl_refund_price = $(R.id.rl_refund_price);
        rl_order_price = $(R.id.rl_order_price);
        rl_refund_used_price = $(R.id.rl_refund_used_price);
        tv_refund_used_price = $(R.id.tv_refund_used_price);
        tv_refund_penalty = $(R.id.tv_refund_penalty);
        tv_refund_price = $(R.id.tv_refund_price);
        cv_refund_reason = $(R.id.cv_refund_reason);
        tv_refund_reason = $(R.id.tv_refund_reason);
        tv_refund_explain = $(R.id.tv_refund_explain);

        tv_nosettle_money = $(R.id.tv_nosettle_money);//待结算金额
        tv_nosettle_moneys = $(R.id.tv_nosettle_moneys);
        tv_nosettle_date = $(R.id.tv_nosettle_date);//待结算日期
        tv_settle_date = $(R.id.tv_settle_date);
        tv_settle_sermoney = $(R.id.tv_settle_sermoney);//平台服务费
        rl_settle_sermoney = $(R.id.rl_settle_sermoney);//rl 平台服务费
        rl_settle_money = $(R.id.rl_settle_money);//rl 待结算金额
        rl_settle_date = $(R.id.rl_settle_date);//rl 待结算日期

        rl_settle_sermoney.setVisibility(View.VISIBLE);
        rl_settle_money.setVisibility(View.VISIBLE);
        rl_settle_date.setVisibility(View.VISIBLE);

        rl_order_price.setVisibility(View.GONE);
        rl_refund_used_price.setVisibility(View.GONE);
        rl_refund_price.setVisibility(View.VISIBLE);
        rl_refund_penalty.setVisibility(View.VISIBLE);
        cv_refund_reason.setVisibility(View.VISIBLE);

        ll_bottom.setVisibility(View.GONE);
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        refundAdapter2 = new OrderIncomeDetail2Adapter(activity, new ArrayList<NjzRefundDetailsChildVOSBean>());
        recyclerView.setAdapter(refundAdapter2);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_call:
                if(refundModel == null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context,refundModel.getMobile(),refundModel.getId(),0,refundModel.getGuideId());
                break;
            case R.id.btn_refund:
                intent = new Intent(context, OrderRefundActivity.class);
                intent.putExtra("REFUND_ID",orderId);
                startActivity(intent);
                break;
        }

    }


    //---------已完成详细订单start--------------
    @Override
    public void querySettleFinDetailSuccess( final OrderSettleBalanceChildModel str) {

    }

    @Override
    public void querySettleFinDetailFailed(String msg) {

    }
    //---------已完成详细订单end--------------


    //---------已退款详细订单start--------------
    @Override
    public void querySettlefulDetailSuccess(final OrderSettltRefundChildModel str) {


        //-------待结算订单详情start

//                refundModel = str;
        final NjzOrderRefundEntityBean orders=str.getNjzOrderRefundEntity();

        rl_refund_price.setVisibility(View.GONE);
        rl_order_price.setVisibility(View.VISIBLE);
        cv_refund_reason.setVisibility(View.VISIBLE);
        rl_refund_penalty.setVisibility(View.GONE);

        ll_order_create_time.setVisibility(View.VISIBLE);
        ll_order_pay_time.setVisibility(View.VISIBLE);
        ll_order_pay_method.setVisibility(View.VISIBLE);

        //头部
        tv_guide_name.setText(orders.getOrderNo());
        tv_order_status.setText("已退款");

        fixed_view_personl.setContent(orders.getPersonNumStr1());

        login_view_phone.setImgVisibility(View.GONE);
        login_view_phone.setContent(orders.getMobilehide());
        login_view_name.setContent(orders.getNameHide());

        login_view_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orders == null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context,orders.getMobile());
            }
        });
        et_special.setContent(TextUtils.isEmpty(orders.getSpecialRequire())?"无":orders.getSpecialRequire());

        //center_botom
        tv_refund_reason.setText(orders.getRefundReason());//退款原因
        tv_refund_explain.setText(orders.getRefundContent());//退款说明
        tv_nosettle_moneys .setText("￥"+priceA);//结算金额
        tv_settle_sermoney.setText("￥"+priceB);//平台服务费
        tv_order_price.setText("￥"+orders.getRefundMoney());//合计
        tv_refund_penalty.setText("￥"+orders.getDefaultMoney());//违约金
        tv_settle_date.setText(DateC);//结算日期
        if(id==10){
            tv_nosettle_money.setText("结算金额");//结算金额
            tv_nosettle_date .setText("结算日期");//结算日期
        }else if(id==11){

        }

        //底部
        ll_order_guide_time.setVisibility(View.VISIBLE);
        ll_order_travel_start.setVisibility(View.GONE);
        ll_order_travel_end.setVisibility(View.GONE);
        ll_order_refund_verify.setVisibility(View.VISIBLE);

        ll_order_no.setVisibility(View.VISIBLE);
        ll_order_refund_apply.setVisibility(View.VISIBLE);
        ll_order_create_time.setVisibility(View.VISIBLE);
        ll_order_pay_time.setVisibility(View.VISIBLE);
        ll_order_pay_method.setVisibility(View.VISIBLE);
        ll_order_no.setVisibility(View.VISIBLE);
        ll_order_refund_time.setVisibility(View.VISIBLE);

        tv_order_refund_apply.setText(""+orders.getApplyTime());//申请退款时间
        tv_order_create_time.setText(orders.getCreateTime());//创建时间
        tv_order_pay_time.setText(orders.getPayTime());//付款时间
        tv_order_pay_method.setText(orders.getPayType());//支付方式
        tv_order_no.setText(orders.getOrderNo());//订单编号
        tv_order_refund_time.setText(orders.getRefundTime());//退款时间
        tv_order_guide_time.setText(""+orders.getGuideSureTime());//导游确认时间
        tv_order_refund_verify.setText(""+orders.getGuideCheckTime());//退款确认时间 导游审核时间


        //------------查看方案 start------------
        if(str.getNjzOrderVO().getNjzChildOrderVOS().size()>0){
            if(str.getNjzOrderVO().getNjzChildOrderVOS().get(0).getValue().equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)){
                fixed_view_personl.setContent(orders.getPersonNumStr2());
                ll_bottom.setVisibility(View.VISIBLE);
                btn_view_desingn_scheme.setVisibility(View.VISIBLE);
            }
        }
        btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CustomPlanActivity.class);
                intent.putExtra("ORDER_ID",orders.getOrderId());
                intent.putExtra("GUIDE_PHONE",orders.getMobile());
                context.startActivity(intent);
            }
        });
        //------------查看方案 end------------
        isShowNoEdit(orders.getOrderNote());

        refundAdapter2.setData(str.getNjzOrderRefundEntity().getNjzRefundDetailsChildVOS());
        //--------------end
    }

    @Override
    public void querySettlefulDetailFailed(String msg) {
        showShortToast(msg);
    }
    //---------已退款详细订单end--------------
}

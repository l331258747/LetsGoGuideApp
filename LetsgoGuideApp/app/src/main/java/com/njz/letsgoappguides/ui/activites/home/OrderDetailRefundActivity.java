package com.njz.letsgoappguides.ui.activites.home;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.home.OrderDetailAdapter;
import com.njz.letsgoappguides.adapter.home.OrderRefundDetailAdapter;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderRefundDetailChildModel;
import com.njz.letsgoappguides.model.home.OrderRefundDetailModel;
import com.njz.letsgoappguides.presenter.home.OrderRefundDetailContract;
import com.njz.letsgoappguides.presenter.home.OrderRefundDetailPresenter;
import com.njz.letsgoappguides.ui.im.ChatActivity;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.dialog.RemarkDialog;

import java.util.ArrayList;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderDetailRefundActivity extends OrderDetailActivity implements OrderRefundDetailContract.View{

    OrderRefundDetailPresenter refundPresenter;
    OrderRefundDetailAdapter refundAdapter;

    OrderRefundDetailModel refundModel;

    RelativeLayout rl_refund_penalty,rl_refund_price,rl_order_price,rl_refund_used_price;
    TextView tv_refund_penalty,tv_refund_price,tv_refund_used_price;
    FrameLayout cv_refund_reason;
    TextView tv_refund_reason,tv_refund_explain;


    @Override
    public void initData() {
        refundPresenter = new OrderRefundDetailPresenter(context,this);
        refundPresenter.orderQueryRefundOrder(orderId);
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

        rl_order_price.setVisibility(View.GONE);
        rl_refund_used_price.setVisibility(View.GONE);
        rl_refund_price.setVisibility(View.VISIBLE);
        rl_refund_penalty.setVisibility(View.VISIBLE);
        cv_refund_reason.setVisibility(View.VISIBLE);
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        refundAdapter = new OrderRefundDetailAdapter(activity, new ArrayList<OrderRefundDetailChildModel>());
        recyclerView.setAdapter(refundAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_call:
                if(refundModel ==null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context,refundModel.getMobile(),refundModel.getId(),0,refundModel.getGuideId());
                break;
            case R.id.btn_refund:
                intent = new Intent(context, OrderRefundActivity.class);
                intent.putExtra("REFUND_ID",orderId);
                startActivity(intent);
                break;
            case R.id.ll_order_remarks:
            case R.id.ll_order_remarks_edit:
                RemarkDialog remarkDialog=new RemarkDialog(this,refundModel.getId(),ll_order_remarks, ll_order_remarks_edit,tv_order_remarks_context);
                remarkDialog.show();
                break;
        }

    }

    @Override
    public void orderQueryRefundOrderSuccess(final OrderRefundDetailModel str) {
        refundModel = str;

        ll_order_no.setVisibility(View.VISIBLE);
        tv_order_no.setText(str.getOrderNo());
        ll_order_create_time.setVisibility(View.VISIBLE);
        tv_order_create_time.setText(str.getCreateTime());

        if(!TextUtils.isEmpty(str.getPayTime())){
            ll_order_pay_time.setVisibility(View.VISIBLE);
            tv_order_pay_time.setText(str.getPayTime());
        }

        if(!TextUtils.isEmpty(str.getPayType())){
            ll_order_pay_method.setVisibility(View.VISIBLE);
            tv_order_pay_method.setText(str.getPayType());
        }

        login_view_name.setContent(str.getName());
        login_view_phone.setContent(str.getMobile());

        if(str.isCustom()){
            btn_view_desingn_scheme.setVisibility(View.VISIBLE);
        }

        switch (str.getRefundStatus()){
            case Constant.ORDER_REFUND_WAIT:
                ll_order_refund_apply.setVisibility(View.VISIBLE);
                tv_order_refund_apply.setText(str.getApplyTime());

                //底部按钮
                btn_call.setVisibility(View.VISIBLE);
                btn_refund.setVisibility(View.VISIBLE);

                //倒计时
                tv_countdown.setVisibility(View.VISIBLE);
                tv_countdown.setText(str.getSureTime());

                isShowRemarks(str.getOrderNote());
                break;
            case Constant.ORDER_REFUND_PROCESS:
                ll_order_refund_apply.setVisibility(View.VISIBLE);//申请退款时间
                tv_order_refund_apply.setText(str.getApplyTime());
                ll_order_refund_verify.setVisibility(View.VISIBLE);//导游退款审核时间
                tv_order_refund_verify.setText(str.getGuideCheckTime());

                isShowNoEdit(str.getOrderNote());
                break;
            case Constant.ORDER_REFUND_FINISH:
                ll_order_refund_verify.setVisibility(View.VISIBLE);//导游退款审核时间
                tv_order_refund_verify.setText(str.getGuideCheckTime());
                ll_order_refund_time.setVisibility(View.VISIBLE);//退款时间
                tv_order_refund_time.setText(str.getRefundTime());
                ll_order_refund_apply.setVisibility(View.VISIBLE);//申请退款时间
                tv_order_refund_apply.setText(str.getApplyTime());

                isShowNoEdit(str.getOrderNote());

                login_view_phone.setImgVisibility(View.GONE);

                login_view_phone.setContent(str.getMobilehide());
                login_view_name.setContent(str.getNameHide());
                break;
            case Constant.ORDER_REFUND_CANCEL:
            case Constant.ORDER_REFUND_PLAN_REFUSE:
                setCancelView(str);
                break;
        }

        //信息
        tv_guide_name.setText(str.getOrderNo());
        tv_order_status.setText(str.getPayStatusStr());
        fixed_view_personl.setContent(str.getPersonNum());

        login_view_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str == null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context,str.getMobile(),str.getId(),0,str.getGuideId());
            }
        });
        et_special.setContent(TextUtils.isEmpty(str.getSpecialRequire())?"无":str.getSpecialRequire());

        if(str.getRefundStatus() == Constant.ORDER_REFUND_CANCEL || str.getRefundStatus() == Constant.ORDER_REFUND_PLAN_REFUSE){
            rl_order_price.setVisibility(View.VISIBLE);
            tv_order_price.setText(str.getOrderPrice());

            rl_refund_price.setVisibility(View.GONE);
            rl_refund_penalty.setVisibility(View.GONE);
        }else{
            rl_order_price.setVisibility(View.VISIBLE);
            tv_order_price_title.setText("订单总额");
            tv_order_price.setText(str.getOrderPrice());

            rl_refund_price.setVisibility(View.VISIBLE);
            rl_refund_penalty.setVisibility(View.VISIBLE);

            tv_refund_penalty.setText("￥" + str.getDefaultMoney());
            tv_refund_price.setText("￥" + str.getRefundMoney());
        }

        tv_refund_penalty.setText("￥" + str.getDefaultMoney());
        tv_refund_price.setText("￥" + str.getRefundMoney());

        //已消费金额
        boolean isTravelGoing = false;
        for(int i = 0;i<str.getNjzRefundDetailsChildVOS().size();i++){

            //设置子单状态
            str.getNjzRefundDetailsChildVOS().get(i).setRefundStatus(str.getRefundStatus());//处理子单，显示退款ui，还是显示取消ui
            str.getNjzRefundDetailsChildVOS().get(i).setPlanStatus(str.getPlanStatus());//处理价待确定

            if(str.getNjzRefundDetailsChildVOS().get(i).getChildOrderStatus() == Constant.ORDER_TRAVEL_GOING){
                isTravelGoing = true;
            }
        }
        if(isTravelGoing){
            rl_refund_used_price.setVisibility(View.VISIBLE);
            tv_refund_used_price.setText("￥" + str.getUseMoney());
        }else{
            rl_refund_used_price.setVisibility(View.GONE);
        }

        tv_refund_reason.setText(str.getRefundReason());
        tv_refund_explain.setText(str.getRefundContent());

        ll_order_remarks.setOnClickListener(this);

        btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//查看方案
                Intent intent = new Intent(context,CustomPlanActivity.class);
                intent.putExtra("ORDER_ID",str.getOrderId());
                intent.putExtra("GUIDE_PHONE",str.getMobile());
                context.startActivity(intent);
            }
        });

        btn_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MySelfInfo.getInstance().getImLogin()){
                    showShortToast("用户未注册到im");
                    return;
                }

                if(refundModel == null) return;
                String name = "u_"+ refundModel.getUserId();
                String myName = EMClient.getInstance().getCurrentUser();
                if (!TextUtils.isEmpty(name)) {
                    if (name.equals(myName)) {
                        showShortToast("不能和自己聊天");
                        return;
                    }
                    Intent chat = new Intent(context, ChatActivity.class);
                    chat.putExtra(EaseConstant.EXTRA_USER_ID, name);  //对方账号
                    chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                    startActivity(chat);

                } else {
                    showShortToast("游客还未注册即时通讯，请使用电话联系TA");
                }
            }
        });

        refundAdapter.setData(str.getNjzRefundDetailsChildVOS());
    }

    @Override
    public void orderQueryRefundOrderFailed(String msg) {
        showShortToast(msg);
    }

    //refuse:true 拒绝接单：私人定制
    public void setCancelView(OrderRefundDetailModel str) {
        tv_refund_reason_title.setText("取消原因");
        tv_refund_explain_title.setText("取消说明");

        ll_order_cancel_time.setVisibility(View.VISIBLE);//取消时间
        tv_order_cancel_time.setText(str.getRefundTime());

        if(str.isCustom()){
            //私人定制,拒绝接单
            if(!TextUtils.isEmpty(str.getGuideSureTime())){
                ll_order_plan_confirm.setVisibility(View.VISIBLE);//导游私人定制确认时间
                tv_order_plan_confirm.setText(str.getGuideSureTime());
            }
            if(!TextUtils.isEmpty(str.getPlanDesignTime())){
                ll_order_plan_up.setVisibility(View.VISIBLE);//导游私人定制方案上传时间
                tv_order_plan_up.setText(str.getPlanDesignTime());
            }
        }
    }
}

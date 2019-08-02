package com.njz.letsgoappguides.ui.activites.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.SimpleImageAdapter;
import com.njz.letsgoappguides.adapter.home.OrderDetailAdapter;
import com.njz.letsgoappguides.adapter.home.OrderListAdapter;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.FixedItemTextView;
import com.njz.letsgoappguides.customview.widget.SpecialFixedItemEditView;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.model.home.EvaluateModel2;
import com.njz.letsgoappguides.model.home.OrderDetailChildModel;
import com.njz.letsgoappguides.model.home.OrderDetailModel;
import com.njz.letsgoappguides.presenter.home.OrderConfirmContract;
import com.njz.letsgoappguides.presenter.home.OrderConfirmPresenter;
import com.njz.letsgoappguides.presenter.home.OrderDetailContract;
import com.njz.letsgoappguides.presenter.home.OrderDetailPresenter;
import com.njz.letsgoappguides.presenter.home.OrderEvaluateContract;
import com.njz.letsgoappguides.presenter.home.OrderEvaluatePresenter;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalContract;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalPresenter;
import com.njz.letsgoappguides.ui.im.ChatActivity;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.dialog.RemarkDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshDetailEvent;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshEvaluateListEvent;
import com.njz.letsgoappguides.util.rxbus.busEvent.RefreshOrderListEvent;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LGQ
 * Time: 2018/9/20
 * Function:
 */

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View, OrderConfirmContract.View,
        View.OnClickListener, OrderEvaluateContract.View, ReviewEvalContract.View {

    public TextView tv_guide_name, tv_order_status, tv_countdown;

    public FixedItemTextView fixed_view_city, login_view_name, login_view_phone, fixed_view_personl, fixed_view_time, fixed_view_creattime;
    public SpecialFixedItemEditView et_special;

    public RecyclerView recyclerView;
    public TextView tv_order_price, btn_view_desingn_scheme;

    public FrameLayout fl_order_times;
    public LinearLayout ll_bottom;

    public LinearLayout ll_order_no, ll_order_create_time, ll_order_pay_time, ll_order_pay_method, ll_order_guide_time,
            ll_order_refund_apply, ll_order_refund_verify, ll_order_refund_time, ll_order_travel_start, ll_order_travel_end,ll_order_plan_confirm,ll_order_plan_up,ll_order_cancel_time;
    public TextView tv_order_no, tv_order_create_time, tv_order_pay_time, tv_order_pay_method, tv_order_guide_time, tv_order_refund_apply,
            tv_order_refund_verify, tv_order_refund_time, tv_order_travel_start, tv_order_travel_end, btn_desingn_scheme,tv_order_plan_confirm,tv_order_plan_up,tv_order_cancel_time;

    public TextView tv_order_price_title;

    public TextView btn_confirm_order, btn_call, btn_refuse_order, btn_refund, btn_consult;


    //待结算，已结算订单详情
    public TextView tv_nosettle_money, tv_nosettle_moneys, tv_nosettle_date, tv_settle_date, tv_settle_sermoney;
    public RelativeLayout rl_settle_sermoney, rl_settle_money, rl_settle_date;

    //退款单已取消订单
    public FrameLayout cv_refund_reason, fl_order_allprice;
    public TextView tv_refund_explain_title, tv_refund_reason_title, tv_refund_reason, tv_refund_explain, tv_order_refund_time_title;

    public View view_line_remark;
    public TextView tv_order_remarks_context;
    public LinearLayout ll_order_remarks, ll_order_remarks_edit;

    public OrderDetailPresenter mPresenter;
    public OrderConfirmPresenter confirmPresenter;
    public OrderEvaluatePresenter evaluatePresenter;
    public ReviewEvalPresenter evalPresenter;

    public TitleView titleView;

    public int orderId;

    public OrderDetailAdapter mAdapter;

    OrderDetailModel model;


    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {

        orderId = getIntent().getIntExtra("ORDER_ID", 0);

        titleView = $(R.id.titleView);
        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_countdown = $(R.id.tv_countdown);
        ll_bottom = $(R.id.ll_bottom);
        tv_order_status = $(R.id.tv_order_status);
        tv_guide_name = $(R.id.tv_guide_name);

        login_view_name = $(R.id.login_view_name);
        login_view_phone = $(R.id.login_view_phone);
        et_special = $(R.id.et_special);

        recyclerView = $(R.id.recycler_view);

        tv_order_price = $(R.id.tv_order_price);
        tv_order_price_title = $(R.id.tv_order_price_title);

        ll_order_no = $(R.id.ll_order_no);
        ll_order_create_time = $(R.id.ll_order_create_time);
        fixed_view_personl = $(R.id.fixed_view_personl);
        ll_order_pay_time = $(R.id.ll_order_pay_time);
        ll_order_pay_method = $(R.id.ll_order_pay_method);
        ll_order_guide_time = $(R.id.ll_order_guide_time);
        ll_order_refund_apply = $(R.id.ll_order_refund_apply);
        ll_order_refund_verify = $(R.id.ll_order_refund_verify);
        ll_order_refund_time = $(R.id.ll_order_refund_time);
        ll_order_travel_start = $(R.id.ll_order_travel_start);
        ll_order_travel_end = $(R.id.ll_order_travel_end);
        ll_order_plan_confirm = $(R.id.ll_order_plan_confirm);
        ll_order_plan_up = $(R.id.ll_order_plan_up);
        ll_order_cancel_time = $(R.id.ll_order_cancel_time);

        tv_order_no = $(R.id.tv_order_no);
        tv_order_create_time = $(R.id.tv_order_create_time);
        tv_order_pay_time = $(R.id.tv_order_pay_time);
        tv_order_pay_method = $(R.id.tv_order_pay_method);
        tv_order_guide_time = $(R.id.tv_order_guide_time);
        tv_order_refund_apply = $(R.id.tv_order_refund_apply);
        tv_order_refund_verify = $(R.id.tv_order_refund_verify);
        tv_order_refund_time = $(R.id.tv_order_refund_time);
        tv_order_travel_start = $(R.id.tv_order_travel_start);
        tv_order_travel_end = $(R.id.tv_order_travel_end);
        tv_order_plan_confirm = $(R.id.tv_order_plan_confirm);
        tv_order_plan_up = $(R.id.tv_order_plan_up);
        tv_order_cancel_time = $(R.id.tv_order_cancel_time);

        btn_desingn_scheme = $(R.id.btn_desingn_scheme);
        btn_refuse_order = $(R.id.btn_refuse_order);
        btn_refund = $(R.id.btn_refund);
        btn_call = $(R.id.btn_call);
        btn_confirm_order = $(R.id.btn_confirm_order);
        btn_view_desingn_scheme = $(R.id.btn_view_desingn_scheme);
        btn_consult = $(R.id.btn_consult);


        cv_refund_reason = $(R.id.cv_refund_reason);
        tv_refund_reason_title = $(R.id.tv_refund_reason_title);
        tv_refund_explain_title = $(R.id.tv_refund_explain_title);
        tv_refund_reason = $(R.id.tv_refund_reason);
        tv_refund_explain = $(R.id.tv_refund_explain);
        fl_order_allprice = $(R.id.fl_order_allprice);
        tv_order_refund_time_title = $(R.id.tv_order_refund_time_title);

        ll_order_remarks = $(R.id.ll_order_remarks);
        view_line_remark = $(R.id.view_line_remark);
        tv_order_remarks_context = $(R.id.tv_order_remarks_context);
        ll_order_remarks_edit = $(R.id.ll_order_remarks_edit);


        btn_refuse_order.setOnClickListener(this);
        btn_refund.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_confirm_order.setOnClickListener(this);
        btn_consult.setOnClickListener(this);

        btn_confirm_order.setVisibility(View.GONE);
        btn_call.setVisibility(View.GONE);
        btn_refuse_order.setVisibility(View.GONE);
        btn_refund.setVisibility(View.GONE);

        et_special.getEtView().setEnabled(false);

        ll_order_no.setVisibility(View.GONE);
        ll_order_create_time.setVisibility(View.GONE);
        ll_order_pay_time.setVisibility(View.GONE);
        ll_order_pay_method.setVisibility(View.GONE);
        ll_order_guide_time.setVisibility(View.GONE);
        ll_order_refund_apply.setVisibility(View.GONE);
        ll_order_refund_verify.setVisibility(View.GONE);
        ll_order_refund_time.setVisibility(View.GONE);
        ll_order_travel_start.setVisibility(View.GONE);
        ll_order_travel_end.setVisibility(View.GONE);
        btn_view_desingn_scheme.setVisibility(View.GONE);
        view_line_remark.setVisibility(View.GONE);
        ll_order_plan_confirm.setVisibility(View.GONE);
        ll_order_plan_up.setVisibility(View.GONE);
        ll_order_cancel_time.setVisibility(View.GONE);

        initRecycler();
        initEvaluateView();

        initData();
    }

    public void initData() {
        mPresenter = new OrderDetailPresenter(context, this);
        confirmPresenter = new OrderConfirmPresenter(context, this);
        evaluatePresenter = new OrderEvaluatePresenter(context, this);
        evalPresenter = new ReviewEvalPresenter(this, context);

        mPresenter.orderQueryOrder(orderId);

        initRefreshDisposable();
    }

    Disposable refreshDisposable;
    private void initRefreshDisposable() {
        refreshDisposable = RxBus2.getInstance().toObservable(RefreshDetailEvent.class, new Consumer<RefreshDetailEvent>() {
            @Override
            public void accept(RefreshDetailEvent refreshOrderListEvent) throws Exception {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(refreshDisposable !=null && !refreshDisposable.isDisposed()){
            refreshDisposable.dispose();
        }
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new OrderDetailAdapter(activity, new ArrayList<OrderDetailChildModel>());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_refuse_order:
                intent = new Intent(context, OrderRefuseActivity.class);
                intent.putExtra("ORDER_ID", model.getId());
                intent.putExtra("IS_CUSTOM", model.isCustom());
                startActivity(intent);
                break;
            case R.id.btn_call:
                if (model == null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context, model.getMobile(),model.getId(),0,model.getGuideId());
                break;
            case R.id.btn_confirm_order:
                DialogUtil.getInstance().getDefaultDialog(context, "是否确认接单", new DialogUtil.DialogCallBack() {
                    @Override
                    public void exectEvent(DialogInterface alterDialog) {
                        if (model.getNjzChildOrderVOS().get(0).getValue().equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)) {
                            confirmPresenter.guideSurePersonalServe(model.getId(), 1, "", "");
                        } else {
                            confirmPresenter.orderSureOrder(model.getId());
                        }
                    }
                }).show();
                break;
            case R.id.btn_consult:

                if (!MySelfInfo.getInstance().getImLogin()) {
                    showShortToast("用户未注册到im");
                    return;
                }

                if (model == null) return;
                String name = "u_" + model.getUserId();
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
                break;
            case R.id.ll_order_remarks:
            case R.id.ll_order_remarks_edit:
                RemarkDialog remarkDialog = new RemarkDialog(this, model.getId(), ll_order_remarks, ll_order_remarks_edit, tv_order_remarks_context);
                remarkDialog.show();
                break;
        }

    }

    @Override
    public void orderQueryOrderSuccess(final OrderDetailModel str) {
        model = str;
        login_view_name.setContent(str.getName());
        login_view_phone.setContent(str.getMobile());
        fixed_view_personl.setContent(str.getPersonNum());
        switch (str.getPayStatus()) {
            case Constant.ORDER_PAY_WAIT://订单状态，底部按钮   待付款详情

                ll_order_no.setVisibility(View.VISIBLE);//单号
                ll_order_create_time.setVisibility(View.VISIBLE);//创建时间
                tv_order_no.setText(str.getOrderNo());
                tv_order_create_time.setText(str.getCreateTime());

                if (str.isCustom()) {
                    tv_countdown.setVisibility(View.VISIBLE);//倒计时
                    tv_countdown.setText(str.getSureTime());
                    btn_call.setVisibility(View.VISIBLE);//电话
                    switch (str.getPlanStatus()) {
                        case Constant.ORDER_PLAN_GUIDE_WAIT:
                            btn_refuse_order.setVisibility(View.VISIBLE);//拒绝
                            btn_confirm_order.setVisibility(View.VISIBLE);//确认订单
                            break;
                        case Constant.ORDER_PLAN_PLANING:
                            btn_desingn_scheme.setVisibility(View.VISIBLE);//方案设计

                            ll_order_plan_confirm.setVisibility(View.VISIBLE);//导游确认时间
                            tv_order_plan_confirm.setText(str.getGuideSureTime());
                            break;
                        case Constant.ORDER_PLAN_USER_WAIT:
                            btn_desingn_scheme.setText("修改方案");//修改方案
                            btn_desingn_scheme.setVisibility(View.VISIBLE);

                            ll_order_plan_confirm.setVisibility(View.VISIBLE);//导游确认时间
                            tv_order_plan_confirm.setText(str.getGuideSureTime());
                            ll_order_plan_up.setVisibility(View.VISIBLE);//方案上传时间
                            tv_order_plan_up.setText(str.getPlanDesignTime());
                            break;
                    }
                }
                break;
            case Constant.ORDER_PAY_ALREADY://已支付待确认详情
                ll_order_no.setVisibility(View.VISIBLE);
                tv_order_no.setText(str.getOrderNo());
                ll_order_create_time.setVisibility(View.VISIBLE);
                ll_order_pay_time.setVisibility(View.VISIBLE);
                ll_order_pay_method.setVisibility(View.VISIBLE);
                tv_order_create_time.setText(str.getCreateTime());
                tv_order_pay_time.setText(str.getPayTime());
                tv_order_pay_method.setText(str.getPayType());

                switch (str.getOrderStatus()){
                    case Constant.ORDER_TRAVEL_NO_GO:
                        ll_order_guide_time.setVisibility(View.VISIBLE);
                        tv_order_guide_time.setText(str.getGuideSureTime());
                        break;
                    case Constant.ORDER_TRAVEL_GOING:
                        ll_order_guide_time.setVisibility(View.VISIBLE);
                        tv_order_guide_time.setText(str.getGuideSureTime());
                        ll_order_travel_start.setVisibility(View.VISIBLE);
                        tv_order_travel_start.setText(str.getStartDate());
                        break;
                }

                switch (str.getOrderStatus()) {
                    case Constant.ORDER_TRAVEL_WAIT:
                        //底部按钮
                        btn_refuse_order.setVisibility(View.VISIBLE);
                        btn_call.setVisibility(View.VISIBLE);
                        btn_confirm_order.setVisibility(View.VISIBLE);

                        //倒计时
                        tv_countdown.setVisibility(View.VISIBLE);
                        tv_countdown.setText(str.getSureTime());

                        isShowRemarks(str.getOrderNote());

                        break;
                    case Constant.ORDER_TRAVEL_NO_GO:
                        //倒计时
                        tv_countdown.setVisibility(View.VISIBLE);
                        tv_countdown.setText(str.getSureTime());

                        //订单状态
                        ll_order_guide_time.setVisibility(View.VISIBLE);
                        tv_order_guide_time.setText(str.getGuideSureTime());

                        isShowRemarks(str.getOrderNote());
                        break;
                    case Constant.ORDER_TRAVEL_GOING:
                        //订单状态
                        ll_order_guide_time.setVisibility(View.VISIBLE);
                        tv_order_guide_time.setText(str.getGuideSureTime());
                        ll_order_travel_start.setVisibility(View.VISIBLE);
                        tv_order_travel_start.setText(str.getStartDate());

                        //评价 行程中 订单详情中支持查看点评信息和导游回复信息
                        if (str.getReviewStatus() == Constant.ORDER_EVALUATE_YES) {
                            evaluatePresenter.orderQueryOrderReview(str.getId());
                        }

                        isShowRemarks(str.getOrderNote());
                        break;
                }

                if (str.isCustom()) {
                    btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                }

                break;
            case Constant.ORDER_PAY_FINISH://已完成
                if (str.isCustom()) {
                    btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                }

                //订单状态
                ll_order_no.setVisibility(View.VISIBLE);
                tv_order_no.setText(str.getOrderNo());
                ll_order_create_time.setVisibility(View.VISIBLE);
                tv_order_create_time.setText(str.getCreateTime());
                ll_order_pay_time.setVisibility(View.VISIBLE);
                tv_order_pay_time.setText(str.getPayTime());
                ll_order_pay_method.setVisibility(View.VISIBLE);
                tv_order_pay_method.setText(str.getPayType());
                ll_order_guide_time.setVisibility(View.VISIBLE);
                tv_order_guide_time.setText(str.getGuideSureTime());
                ll_order_travel_start.setVisibility(View.VISIBLE);
                tv_order_travel_start.setText(str.getStartDate());
                ll_order_travel_end.setVisibility(View.VISIBLE);
                tv_order_travel_end.setText(str.getEndDate());
                login_view_phone.setImgVisibility(View.GONE);

                login_view_phone.setContent(str.getMobilehide());
                login_view_name.setContent(str.getNameHide());

                //评价
                if (str.getReviewStatus() == Constant.ORDER_EVALUATE_YES) {
                    evaluatePresenter.orderQueryOrderReview(str.getId());
                }

                isShowNoEdit(str.getOrderNote());
                break;
        }

        tv_order_price.setText(str.getOrderPriceStr());

        tv_guide_name.setText(str.getOrderNo());
        tv_order_status.setText(str.getPayStatusStr());


        ll_order_remarks.setOnClickListener(this);
        ll_order_remarks_edit.setOnClickListener(this);

        login_view_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str == null) return;
                DialogUtil.getInstance().showGuideMobileDialog(context, str.getMobile(),str.getId(),0,str.getGuideId());
            }
        });

        et_special.setContent(TextUtils.isEmpty(str.getSpecialRequire()) ? "无" : str.getSpecialRequire());

        btn_desingn_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//设计方案
                Intent intent = new Intent(context, OrderDesingnSchemeActivity.class);
                intent.putExtra("STATUS", str.getPlanStatus());
                intent.putExtra("ORDER_ID", str.getId());
                context.startActivity(intent);
            }
        });

        btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//查看方案
                Intent intent = new Intent(context, CustomPlanActivity.class);
                intent.putExtra("ORDER_ID", str.getId());
                intent.putExtra("GUIDE_PHONE", str.getMobile());
                context.startActivity(intent);
            }
        });

        for (OrderDetailChildModel model : str.getNjzChildOrderVOS()) {
            model.setPayingStatus(str.getPayingStatus());
            model.setPlanStatus(str.getPlanStatus());
        }
        mAdapter.setData(str.getNjzChildOrderVOS());
    }

    public void isShowRemarks(String orderNote) {
        view_line_remark.setVisibility(View.VISIBLE);
        if (orderNote.equals("")) {
            ll_order_remarks.setVisibility(View.VISIBLE);
            ll_order_remarks_edit.setVisibility(View.GONE);
        } else {
            ll_order_remarks.setVisibility(View.GONE);
            ll_order_remarks_edit.setVisibility(View.VISIBLE);
            tv_order_remarks_context.setText(orderNote);
        }
    }

    public void isShowNoEdit(String orderNote) {
        view_line_remark.setVisibility(View.GONE);
        ll_order_remarks_edit.setVisibility(View.GONE);
        tv_order_remarks_context.setText(orderNote.equals("") ? "无" : orderNote);
        tv_order_remarks_context.setTextColor(getResources().getColor(R.color.color_cc));
    }

    @Override
    public void orderQueryOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void orderSureOrderSuccess(String datas) {
        showShortToast("接单成功");
        RxBus2.getInstance().post(new RefreshOrderListEvent());
        finish();
    }

    @Override
    public void guideSurePersonalServeSuccess(String datas) {
        showShortToast("接单成功");
        RxBus2.getInstance().post(new RefreshOrderListEvent());
        finish();
    }

    @Override
    public void guideSurePersonalServeFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void orderSureOrderFailed(String msg) {
        showShortToast(msg);
    }


    //---评价 start-----
    FrameLayout fl_evaluate;
    ImageView comment_head;
    TextView commont_name, commont_time, commont_score;
    TextView tv_comment_guide, tv_comment_trip, tv_comment_car, tv_comment_book;
    TextView tv_comment_content;
    LinearLayout ll_photo;
    RecyclerView recycler_view_comment;
    RelativeLayout rl_reply_et, rl_reply;
    EditText et_reply;
    TextView tv_comment_submit, reply_time, reply_content;

    public void initEvaluateView() {
        fl_evaluate = $(R.id.fl_evaluate);
        comment_head = $(R.id.comment_head);
        commont_name = $(R.id.commont_name);
        commont_time = $(R.id.commont_time);
        commont_score = $(R.id.commont_score);
        tv_comment_guide = $(R.id.tv_comment_guide);
        tv_comment_trip = $(R.id.tv_comment_trip);
        tv_comment_car = $(R.id.tv_comment_car);
        tv_comment_book = $(R.id.tv_comment_book);
        tv_comment_content = $(R.id.tv_comment_content);
        ll_photo = $(R.id.ll_photo);
        recycler_view_comment = $(R.id.recycler_view_comment);
        rl_reply_et = $(R.id.rl_reply_et);
        rl_reply = $(R.id.rl_reply);
        et_reply = $(R.id.et_reply);
        tv_comment_submit = $(R.id.tv_comment_submit);
        reply_time = $(R.id.reply_time);
        reply_content = $(R.id.reply_content);

        fl_evaluate.setVisibility(View.GONE);
    }

    public void setEvaluateView(final EvaluateModel2 data) {
        fl_evaluate.setVisibility(View.VISIBLE);
        GlideUtil.LoadCircleImage(context, data.getImgUrl(), comment_head);
        commont_name.setText(data.getNickname());
        commont_time.setText(data.getUserDate());
        commont_score.setText(data.getScore());
        tv_comment_content.setText(data.getUserContent());

        tv_comment_guide.setVisibility(View.GONE);
        tv_comment_trip.setVisibility(View.GONE);
        tv_comment_car.setVisibility(View.GONE);
        tv_comment_book.setVisibility(View.GONE);
        if (data.getServiceAttitude() > 0) {
            tv_comment_guide.setVisibility(View.VISIBLE);
            tv_comment_guide.setText(data.getServiceAttitudeStr());
        }
        if (data.getTravelArrange() > 0) {
            tv_comment_trip.setVisibility(View.VISIBLE);
            tv_comment_trip.setText(data.getTravelArrangeStr());
        }
        if (data.getCarCondition() > 0) {
            tv_comment_car.setVisibility(View.VISIBLE);
            tv_comment_car.setText(data.getCarConditionStr());
        }
        if (data.getServiceQuality() > 0) {
            tv_comment_book.setVisibility(View.VISIBLE);
            tv_comment_book.setText(data.getServiceQualityStr());
        }

        recycler_view_comment.setNestedScrollingEnabled(false);//滑动取消
        recycler_view_comment.setLayoutManager(new GridLayoutManager(
                recycler_view_comment.getContext(), 4));
        if (data.getImageUrls() == null || data.getImageUrls().size() == 0) {
            ll_photo.setVisibility(View.GONE);
        } else {
            ll_photo.setVisibility(View.VISIBLE);
            SimpleImageAdapter enterAdapter = new SimpleImageAdapter(context, data.getImageUrls());
            recycler_view_comment.setAdapter(enterAdapter);
        }

        if (TextUtils.isEmpty(data.getGuideContent())) {
            rl_reply.setVisibility(View.GONE);

            rl_reply_et.setVisibility(View.VISIBLE);
            tv_comment_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(et_reply.getText().toString().trim()))
                        showShortToast("回复不能为空");
                    else {
                        evalPresenter.getReviewEval(et_reply.getText().toString().trim(), data.getUserId(), data.getId(), data.getOrderId());
                    }
                }
            });

        } else {
            rl_reply_et.setVisibility(View.GONE);

            rl_reply.setVisibility(View.VISIBLE);
            reply_time.setText(data.getGuideDate());
            reply_content.setText(data.getGuideContent());
        }


    }


    @Override
    public void orderQueryOrderReviewSuccess(EvaluateModel2 datas) {
        setEvaluateView(datas);
    }

    @Override
    public void orderQueryOrderReviewFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getReviewEvalSuccess(String str) {
        evaluatePresenter.orderQueryOrderReview(model.getId());
        RxBus2.getInstance().post(new RefreshEvaluateListEvent());
    }

    @Override
    public void getReviewEvalFailed(String msg) {
        showShortToast(msg);
    }


    //---评价 end-----
}

package com.njz.letsgoappguides.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderBeanGroup;
import com.njz.letsgoappguides.model.home.OrderListChildModel;
import com.njz.letsgoappguides.model.home.OrderListModel;
import com.njz.letsgoappguides.model.mine.OrderSettleModel;
import com.njz.letsgoappguides.ui.activites.home.CustomPlanActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderDesingnSchemeActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderRefundActivity;
import com.njz.letsgoappguides.ui.activites.home.OrderRefuseActivity;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.dialog.RemarkDialog;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/8/21
 * Function:
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.BaseViewHolder> {


    private static final int ORDER_TYPE_TITLE = 10;
    private static final int ORDER_TYPE_FOOT = 12;

    Context mContext;
    List<OrderListModel> datas;

    private List<OrderBeanGroup> orderBeanGroups = new ArrayList<>();

    public OrderListAdapter(Context mContext, List<OrderListModel> datas) {
        this.mContext = mContext;
        this.datas = datas;

        setData(datas);
    }

    public void setData(List<OrderListModel> datas) {
        orderBeanGroups.clear();
        this.datas = datas;
        setData2(datas);
        notifyDataSetChanged();
    }

    public void addData(List<OrderListModel> datas) {
        this.datas.addAll(datas);
        setData(this.datas);
    }

    public List<OrderListModel> getData() {
        return datas;
    }

    public OrderListModel getItem(int position) {
        return this.datas.get(position);
    }

    public void setData2(List<OrderListModel> datas) {
        if (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                OrderBeanGroup serviceInfoGroup = new OrderBeanGroup();
                OrderListModel orderModel = datas.get(i);

                serviceInfoGroup.setLabelTab(OrderBeanGroup.LABEL_TAB_TITLE);
                serviceInfoGroup.setOrderNo(orderModel.getOrderNo());
                serviceInfoGroup.setId(orderModel.getId());
                serviceInfoGroup.setName(orderModel.getName());
                serviceInfoGroup.setPayStatus(orderModel.getPayStatus());
                serviceInfoGroup.setOrderStatus(orderModel.getOrderStatus());
                serviceInfoGroup.setReviewStatus(orderModel.getReviewStatus());
                serviceInfoGroup.setPayingStatus(orderModel.getPayingStatus());
                serviceInfoGroup.setPlanStatus(orderModel.getPlanStatus());
                serviceInfoGroup.setRefundStatus(orderModel.getRefundStatus());
                serviceInfoGroup.setSureTime(orderModel.getSureTime());
                serviceInfoGroup.setRefundId(orderModel.getRefundId());
                serviceInfoGroup.setIndex(i);
                orderBeanGroups.add(serviceInfoGroup);


                for (int j = 0; j < orderModel.getNjzChildOrderListVOS().size(); j++) {
                    OrderBeanGroup serviceInfoGroup2 = new OrderBeanGroup();
                    OrderListChildModel orderChildModel = orderModel.getNjzChildOrderListVOS().get(j);
                    orderChildModel.setPlanStatus(orderModel.getPlanStatus());
                    orderChildModel.setPayingStatus(orderModel.getPayingStatus());
                    serviceInfoGroup2.setLabelTab(OrderBeanGroup.LABEL_TAB_DEFAULT);
                    serviceInfoGroup2.setOrderChildModel(orderChildModel);
                    serviceInfoGroup2.setId(orderModel.getId());
                    serviceInfoGroup2.setRefundId(orderModel.getRefundId());
                    serviceInfoGroup2.setPayStatus(orderModel.getPayStatus());
                    serviceInfoGroup2.setRefundStatus(orderModel.getRefundStatus());
                    serviceInfoGroup2.setIndex(i);
                    orderBeanGroups.add(serviceInfoGroup2);
                }


                OrderBeanGroup serviceInfoGroup3 = new OrderBeanGroup();
                serviceInfoGroup3.setLabelTab(OrderBeanGroup.LABEL_TAB_FOOT);
                serviceInfoGroup3.setPayStatus(orderModel.getPayStatus());
                serviceInfoGroup3.setOrderPrice(orderModel.getOrderPrice());
                serviceInfoGroup3.setOrderStatus(orderModel.getOrderStatus());
                serviceInfoGroup3.setReviewStatus(orderModel.getReviewStatus());
                serviceInfoGroup3.setName(orderModel.getName());
                serviceInfoGroup3.setId(orderModel.getId());
                serviceInfoGroup3.setOrderNo(orderModel.getOrderNo());
                serviceInfoGroup3.setLocation(orderModel.getLocation());
                serviceInfoGroup3.setUserName(orderModel.getName());
                serviceInfoGroup3.setUserMobile(orderModel.getMobile());
                serviceInfoGroup3.setMobile(orderModel.getMobile());
                serviceInfoGroup3.setPayingStatus(orderModel.getPayingStatus());
                serviceInfoGroup3.setPlanStatus(orderModel.getPlanStatus());
                serviceInfoGroup3.setRefundStatus(orderModel.getRefundStatus());
                serviceInfoGroup3.setRefundId(orderModel.getRefundId());
                if (orderModel.getNjzChildOrderListVOS() != null
                        && orderModel.getNjzChildOrderListVOS().size() == 1
                        && orderModel.getNjzChildOrderListVOS().get(0).getServeType() == Constant.SERVER_TYPE_CUSTOM_ID) {
                    serviceInfoGroup3.setCustom(true);
                }
                orderBeanGroups.add(serviceInfoGroup3);
            }
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ORDER_TYPE_TITLE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_order_title, parent, false);
                return new TitleHolder(view);
            case ORDER_TYPE_FOOT:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_order_foot, parent, false);
                return new FootHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_order_defualt, parent, false);
                return new DefaultHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (orderBeanGroups.get(position).getLabelTab() == OrderBeanGroup.LABEL_TAB_TITLE)
            return ORDER_TYPE_TITLE;
        if (orderBeanGroups.get(position).getLabelTab() == OrderBeanGroup.LABEL_TAB_FOOT)
            return ORDER_TYPE_FOOT;
        return super.getItemViewType(position);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof DefaultHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderListChildModel data = orderBeanGroups.get(pos).getOrderChildModel();
            if (data == null) return;

            GlideUtil.LoadRoundImage(mContext, data.getTitleImg(), ((DefaultHolder) holder).iv_img, 5);
            ((DefaultHolder) holder).tv_title.setText(data.getTitle());
            ((DefaultHolder) holder).tv_comment.setText(data.getValuestr());
            ((DefaultHolder) holder).tv_total_price.setText(data.getOrderPriceStr());

            if (mOnItemClickListener != null) {
                ((DefaultHolder) holder).ll_order_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {//int orderId,int refundId,int payStatus,int refundStatus
                        mOnItemClickListener.onClick(orderBeanGroups.get(pos).getId(), orderBeanGroups.get(pos).getRefundId());
                    }
                });
            }
        }

        if (holder instanceof TitleHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderBeanGroup data = orderBeanGroups.get(pos);
            if (data == null) return;

            ((TitleHolder) holder).tv_order.setText(data.getOrderNo());
            ((TitleHolder) holder).tv_status.setText(data.getPayStatusStr());
            ((TitleHolder) holder).tv_name.setText(data.getName());

            if (mOnItemClickListener != null) {
                ((TitleHolder) holder).rl_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(data.getId(), data.getRefundId());
                    }
                });
            }

            switch (data.getPayStatus()) {
                case Constant.ORDER_PAY_REFUND:
                    switch (data.getRefundStatus()) {
                        case Constant.ORDER_REFUND_WAIT:
                            ((TitleHolder) holder).tv_countdown.setText(data.getSureTime());
                            ((TitleHolder) holder).tv_countdown.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
                case Constant.ORDER_PAY_ALREADY:
                    switch (data.getOrderStatus()) {
                        case Constant.ORDER_TRAVEL_WAIT:
                            ((TitleHolder) holder).tv_countdown.setText(data.getSureTime());
                            ((TitleHolder) holder).tv_countdown.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
            }
        }

        if (holder instanceof FootHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderBeanGroup data = orderBeanGroups.get(pos);
            if (data == null) return;
            ((FootHolder) holder).tv_order_price_title.setText("合计:");
            ((FootHolder) holder).tv_order_price_content.setText(data.getOrderPriceStr());
            ((FootHolder) holder).tv_order.setText(data.getOrderNo());

            ((FootHolder) holder).setbtn();
            switch (data.getPayStatus()) {
                case Constant.ORDER_PAY_WAIT://代付款订单
                    ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                    if (data.isCustom()) {//私人订制
                        switch (data.getPlanStatus()) {
                            case Constant.ORDER_PLAN_GUIDE_WAIT:
                                ((FootHolder) holder).btn_desingn_scheme.setVisibility(View.GONE);//设计方案
                                ((FootHolder) holder).btn_refuse_order.setVisibility(View.VISIBLE);//拒绝
                                ((FootHolder) holder).btn_confirm_order.setVisibility(View.VISIBLE);//确认接单
                                break;
                            case Constant.ORDER_PLAN_PLANING:
                                ((FootHolder) holder).btn_desingn_scheme.setText("设计方案");
                                ((FootHolder) holder).btn_desingn_scheme.setVisibility(View.VISIBLE);//设计方案
                                ((FootHolder) holder).btn_refuse_order.setVisibility(View.GONE);//拒绝
                                ((FootHolder) holder).btn_confirm_order.setVisibility(View.GONE);//确认接单
                                break;
                            case Constant.ORDER_PLAN_USER_WAIT://游客待确认，修改方案
                                ((FootHolder) holder).btn_desingn_scheme.setText("修改方案");
                                ((FootHolder) holder).btn_desingn_scheme.setVisibility(View.VISIBLE);//设计方案
                                ((FootHolder) holder).btn_refuse_order.setVisibility(View.GONE);//拒绝
                                ((FootHolder) holder).btn_confirm_order.setVisibility(View.GONE);//确认接单
                                break;
                        }
                    }
                    break;
                case Constant.ORDER_PAY_ALREADY://待确认订单  已确认订单 未出行  行程中
                    ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                    if (data.isCustom()) {//私人订制
                        ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                    } else {
                        ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.GONE);
                    }
                    switch (data.getOrderStatus()) {
                        case Constant.ORDER_TRAVEL_WAIT:
                            ((FootHolder) holder).btn_refuse_order.setVisibility(View.VISIBLE);
                            ((FootHolder) holder).btn_confirm_order.setVisibility(View.VISIBLE);
                            ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.GONE);
                            break;
                    }
                    break;
                case Constant.ORDER_PAY_FINISH:
                    ((FootHolder) holder).foot_btns.setVisibility(View.GONE);
                    if (data.isCustom()) {//私人订制
                        ((FootHolder) holder).foot_btns.setVisibility(View.VISIBLE);
                        ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                    }
                    break;
                case Constant.ORDER_PAY_REFUND:
                    ((FootHolder) holder).foot_btns.setVisibility(View.GONE);
                    ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.GONE);
                    if (data.isCustom()) {//私人订制
                        ((FootHolder) holder).foot_btns.setVisibility(View.VISIBLE);
                        ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                    }
                    switch (data.getRefundStatus()) {
                        case Constant.ORDER_REFUND_WAIT:
                            ((FootHolder) holder).foot_btns.setVisibility(View.VISIBLE);
                            ((FootHolder) holder).btn_refund.setVisibility(View.VISIBLE);
                            ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                            break;
                        case Constant.ORDER_REFUND_CANCEL:
                        case Constant.ORDER_REFUND_PLAN_REFUSE:
                            ((FootHolder) holder).foot_btns.setVisibility(View.VISIBLE);
                            ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
            }

            ((FootHolder) holder).btn_refund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderRefundActivity.class);
                    intent.putExtra("REFUND_ID", data.getRefundId());
                    mContext.startActivity(intent);
                }
            });

            ((FootHolder) holder).btn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.getInstance().showGuideMobileDialog(mContext, data.getMobile());
                }
            });
            ((FootHolder) holder).btn_refuse_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderRefuseActivity.class);
                    intent.putExtra("ORDER_ID", data.getId());
                    intent.putExtra("VALUE", data.getOrderChildModel().getValue());
                    mContext.startActivity(intent);
                }
            });
            ((FootHolder) holder).btn_confirm_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onConfirmClickListener != null) {
                        onConfirmClickListener.onClick(data.getId(), data.getOrderChildModel().getValue());
                    }
                }
            });
            ((FootHolder) holder).btn_desingn_scheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//设计方案
                    Intent intent = new Intent(mContext, OrderDesingnSchemeActivity.class);
                    intent.putExtra("STATUS", data.getPlanStatus());
                    intent.putExtra("ORDER_ID", data.getId());
                    mContext.startActivity(intent);
                }
            });

            ((FootHolder) holder).btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//查看方案
                    Intent intent = new Intent(mContext, CustomPlanActivity.class);
                    intent.putExtra("ORDER_ID", data.getId());
                    intent.putExtra("GUIDE_PHONE", data.getMobile());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orderBeanGroups.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class TitleHolder extends OrderListAdapter.BaseViewHolder implements View.OnClickListener {
        TextView tv_order, tv_status, tv_name, tv_countdown;
        RelativeLayout rl_status;

        TitleHolder(View itemView) {
            super(itemView);
            tv_order = itemView.findViewById(R.id.tv_order);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_status = itemView.findViewById(R.id.tv_status);
            rl_status = itemView.findViewById(R.id.rl_status);
            tv_countdown = itemView.findViewById(R.id.tv_countdown);

            rl_status.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class DefaultHolder extends OrderListAdapter.BaseViewHolder {
        ImageView iv_img;
        TextView tv_title, tv_comment, tv_total_price;
        LinearLayout ll_order_item;

        DefaultHolder(View itemView) {
            super(itemView);
            ll_order_item = itemView.findViewById(R.id.ll_order_item);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);
        }
    }


    public class FootHolder extends OrderListAdapter.BaseViewHolder {
        TextView tv_order_price_content, tv_order_price_title, tv_order;
        TextView btn_refuse_order, btn_call, btn_confirm_order, btn_refund, btn_desingn_scheme, btn_view_desingn_scheme;
        HorizontalScrollView foot_btns;

        FootHolder(View itemView) {
            super(itemView);
            tv_order_price_content = itemView.findViewById(R.id.tv_order_price_content);
            tv_order_price_title = itemView.findViewById(R.id.tv_order_price_title);
            tv_order = itemView.findViewById(R.id.tv_order);

            btn_refuse_order = itemView.findViewById(R.id.btn_refuse_order);
            btn_call = itemView.findViewById(R.id.btn_call);
            btn_confirm_order = itemView.findViewById(R.id.btn_confirm_order);
            btn_refund = itemView.findViewById(R.id.btn_refund);

            foot_btns = itemView.findViewById(R.id.foot_btns);
            btn_desingn_scheme = itemView.findViewById(R.id.btn_desingn_scheme);//设计方案
            btn_view_desingn_scheme = itemView.findViewById(R.id.btn_view_desingn_scheme);//查看方案
        }

        public void setbtn() {
            btn_view_desingn_scheme.setVisibility(View.GONE);
            btn_desingn_scheme.setVisibility(View.GONE);
            btn_refuse_order.setVisibility(View.GONE);
            btn_call.setVisibility(View.GONE);
            btn_confirm_order.setVisibility(View.GONE);
            btn_refund.setVisibility(View.GONE);
            foot_btns.setVisibility(View.VISIBLE);
        }
    }

    //---------事件 start---------
    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int orderId, int refundId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    OnConfirmClickListener onConfirmClickListener;

    public interface OnConfirmClickListener {
        void onClick(int orderId, String value);
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }


    //---------事件 end---------

}

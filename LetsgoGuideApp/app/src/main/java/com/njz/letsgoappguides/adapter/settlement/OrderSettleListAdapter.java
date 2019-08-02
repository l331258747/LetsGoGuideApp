package com.njz.letsgoappguides.adapter.settlement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
import com.njz.letsgoappguides.model.settlement.OrderSettleBeanGroup;
import com.njz.letsgoappguides.model.settlement.OrderSettleChildModel;
import com.njz.letsgoappguides.model.settlement.OrderSettleModel;
import com.njz.letsgoappguides.ui.activites.home.CustomPlanActivity;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/8/21
 * Function: 待结算
 */

public class OrderSettleListAdapter extends RecyclerView.Adapter<OrderSettleListAdapter.BaseViewHolder> {

    private static final int ORDER_TYPE_TITLE = 10;
    private static final int ORDER_TYPE_FOOT = 12;

    Context mContext;
    List<OrderSettleModel> datas;

    private List<OrderSettleBeanGroup> orderBeanGroups = new ArrayList<>();

    public OrderSettleListAdapter(Context mContext, List<OrderSettleModel> datas) {
        this.mContext = mContext;
        this.datas = datas;

        setData(datas);
    }

    public void setData(List<OrderSettleModel> datas) {
        orderBeanGroups.clear();
        this.datas = datas;
        setData2(datas);
        notifyDataSetChanged();
    }

    public void addData(List<OrderSettleModel> datas){
        this.datas.addAll(datas);
        setData(this.datas);
    }

    public List<OrderSettleModel> getData(){
        return datas;
    }

    public OrderSettleModel getItem(int position){
        return this.datas.get(position);
    }

    public void setData2(List<OrderSettleModel> datas) {
        if (datas != null) {
            for (int i = 0;i<datas.size();i++){
                OrderSettleBeanGroup serviceInfoGroup = new OrderSettleBeanGroup();
                OrderSettleModel orderModel = datas.get(i);

                serviceInfoGroup.setLabelTab(OrderSettleBeanGroup.LABEL_TAB_TITLE);
                serviceInfoGroup.setOrderNo(orderModel.getOrderNo());
                serviceInfoGroup.setName(orderModel.getName());
                serviceInfoGroup.setPayStatus(Constant.ORDER_PAY_REFUND);
                serviceInfoGroup.setStatus(orderModel.getStatus());
                serviceInfoGroup.setReviewStatus(Constant.ORDER_EVALUATE_NO);
                orderBeanGroups.add(serviceInfoGroup);
                for (int j = 0; j<orderModel.getChildOrder().size();j++){
                    OrderSettleBeanGroup serviceInfoGroup2 = new OrderSettleBeanGroup();
                    OrderSettleChildModel orderChildModel = orderModel.getChildOrder().get(j);
                    serviceInfoGroup2.setLabelTab(OrderSettleBeanGroup.LABEL_TAB_DEFAULT);
                    serviceInfoGroup2.setChildOrder(orderChildModel);
                    serviceInfoGroup2.setStatus(orderModel.getStatus());
                    serviceInfoGroup2.setBalanceMoney(orderModel.getBalanceMoney());
                    serviceInfoGroup2.setPlatformMoney(orderModel.getPlatformMoney());
                    serviceInfoGroup2.setBalanceDate(orderModel.getBalanceDate());
                    orderBeanGroups.add(serviceInfoGroup2);
                }
                OrderSettleBeanGroup serviceInfoGroup3 = new OrderSettleBeanGroup();
                serviceInfoGroup3.setLabelTab(OrderSettleBeanGroup.LABEL_TAB_FOOT);
                serviceInfoGroup3.setPayStatus(Constant.ORDER_PAY_REFUND);
                serviceInfoGroup.setStatus(orderModel.getStatus());
                serviceInfoGroup3.setReviewStatus(Constant.ORDER_EVALUATE_NO);
                serviceInfoGroup3.setName(orderModel.getName());
                serviceInfoGroup3.setOrderNo(orderModel.getOrderNo());
                serviceInfoGroup3.setLocation(orderModel.getLocation());
                serviceInfoGroup3.setBalanceMoney(orderModel.getBalanceMoney());
                serviceInfoGroup3.setPlatformMoney(orderModel.getPlatformMoney());
                serviceInfoGroup3.setBalanceDate(orderModel.getBalanceDate());
                serviceInfoGroup3.setOrderPrice(orderModel.getOrderPrice());
                serviceInfoGroup3.setMobile(orderModel.getMobile());
                OrderSettleChildModel infos=new OrderSettleChildModel();
                if(orderModel.getChildOrder() != null && orderModel.getChildOrder().size() > 0){
                    infos.setValue(orderModel.getChildOrder().get(0).getValue());
                    infos.setOrderId(orderModel.getChildOrder().get(0).getOrderId());
                    serviceInfoGroup3.setChildOrder(infos);
                }
                orderBeanGroups.add(serviceInfoGroup3);
            }
        }
    }

    @Override
    public OrderSettleListAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ORDER_TYPE_TITLE:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_order_title, parent, false);
                return new OrderSettleListAdapter.TitleHolder(view);
            case ORDER_TYPE_FOOT:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_order_foot, parent, false);
                return new OrderSettleListAdapter.FootHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_order_defualt, parent, false);
                return new OrderSettleListAdapter.DefaultHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (orderBeanGroups.get(position).getLabelTab() == OrderSettleBeanGroup.LABEL_TAB_TITLE)
            return ORDER_TYPE_TITLE;
        if (orderBeanGroups.get(position).getLabelTab() == OrderSettleBeanGroup.LABEL_TAB_FOOT)
            return ORDER_TYPE_FOOT;
        return super.getItemViewType(position);

    }

    @Override
    public void onBindViewHolder(OrderSettleListAdapter.BaseViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof DefaultHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderSettleChildModel data = orderBeanGroups.get(pos).getChildOrder();
            if (data == null) return;

            GlideUtil.LoadRoundImage(mContext,data.getTitleImg(),((DefaultHolder) holder).iv_img,5);

            ((DefaultHolder) holder).tv_title.setText(data.getTitle());

            ((DefaultHolder) holder).tv_price.setText( data.getValuestr());

            ((DefaultHolder) holder).tv_total_price.setText("￥" + data.getOrderPrice());

            if (mOnItemClickListener != null) {//待结算订单
                ((DefaultHolder) holder).ll_order_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (orderBeanGroups.get(pos).getStatus()){
                            case 1://已退款
                                mOnItemClickListener.onClick(
                                        data.getNjzChildOrderRefundEntity().getRefundId(),
                                        orderBeanGroups.get(pos).getStatus(),
                                        orderBeanGroups.get(pos).getBalanceMoney(),
                                        orderBeanGroups.get(pos).getPlatformMoney(),
                                        orderBeanGroups.get(pos).getBalanceDate());
                                break;
                            case 2:
                                mOnItemClickListener.onClick(
                                        orderBeanGroups.get(pos).getChildOrder().getOrderId(),
                                        orderBeanGroups.get(pos).getStatus(),
                                        orderBeanGroups.get(pos).getBalanceMoney(),
                                        orderBeanGroups.get(pos).getPlatformMoney(),
                                        orderBeanGroups.get(pos).getBalanceDate());
                                break;
                        }
                    }
                });
            }

        }

        if (holder instanceof TitleHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderSettleBeanGroup data = orderBeanGroups.get(pos);
            if (data == null) return;

            ((TitleHolder) holder).tv_order.setText(data.getOrderNo());
            ((TitleHolder) holder).tv_status.setText(data.getsettleStatus());
            ((TitleHolder) holder).tv_name.setText(data.getName());

        }

        if (holder instanceof FootHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderSettleBeanGroup data = orderBeanGroups.get(pos);
            if (data == null) return;

            ((FootHolder) holder).setbtn();

            if(data.getChildOrder()!=null) {
                if(data.getChildOrder().getValue()!=null){
                    if (data.getChildOrder().getValue().equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)) {
                        ((FootHolder) holder).foot_btns.setVisibility(View.VISIBLE);
                        ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                    }
                }

            }

            ((FootHolder) holder).tv_order_price_title.setText("合计:");
            ((FootHolder) holder).tv_order_price_content.setText("￥" + data.getOrderPrice());
            ((FootHolder) holder).tv_order.setText(data.getOrderNo());

            ((FootHolder) holder).ll_settle_id.setVisibility(View.VISIBLE);

            ((FootHolder) holder).tv_settle_money.setText("￥"+data.getBalanceMoney());
//            ((FootHolder) holder).tv_settle_date.setText(""+data.getBalanceDate());
            ((FootHolder) holder).tv_settle_date.setText("三个工作日内");

            ((FootHolder) holder).btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,CustomPlanActivity.class);
                    intent.putExtra("ORDER_ID",data.getChildOrder().getOrderId());
                    intent.putExtra("GUIDE_PHONE",data.getMobile());
                    mContext.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return orderBeanGroups.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class TitleHolder extends OrderSettleListAdapter.BaseViewHolder {
        TextView tv_order,tv_status,tv_name;
        RelativeLayout rl_status;

        TitleHolder(View itemView) {
            super(itemView);
            tv_order = itemView.findViewById(R.id.tv_order);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_status = itemView.findViewById(R.id.tv_status);
            rl_status = itemView.findViewById(R.id.rl_status);

        }

    }

    public class DefaultHolder extends OrderSettleListAdapter.BaseViewHolder {
        ImageView iv_img;
        TextView tv_title,tv_price,tv_num,tv_total_price;
        LinearLayout ll_order_item;

        DefaultHolder(View itemView) {
            super(itemView);
            ll_order_item = itemView.findViewById(R.id.ll_order_item);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
//            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            tv_price = itemView.findViewById(R.id.tv_comment);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);
        }
    }


    public class FootHolder extends OrderSettleListAdapter.BaseViewHolder {
        TextView tv_order_price_content,tv_order_price_title,btn_view_desingn_scheme,tv_order;
        TextView btn_refuse_order, btn_call, btn_confirm_order, btn_refund,tv_settle_date,tv_settle_money;
        HorizontalScrollView foot_btns;
        LinearLayout ll_settle_id;

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

            ll_settle_id = itemView.findViewById(R.id.ll_settle_id);
            tv_settle_date = itemView.findViewById(R.id.tv_settle_date);
            tv_settle_money = itemView.findViewById(R.id.tv_settle_money);
            btn_view_desingn_scheme = itemView.findViewById(R.id.btn_view_desingn_scheme);
        }

        public void setbtn(){
            btn_refuse_order.setVisibility(View.GONE);
            btn_call.setVisibility(View.GONE);
            btn_confirm_order.setVisibility(View.GONE);
            btn_refund.setVisibility(View.GONE);

            foot_btns.setVisibility(View.GONE);
        }
    }

    //---------事件 start---------
    OrderSettleListAdapter.OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onClick(int orderId,int status,float priceA,float priceB,String dateC);
    }
    public void setOnItemClickListener(OrderSettleListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    OrderSettleListAdapter.OnConfirmClickListener onConfirmClickListener;
    public interface OnConfirmClickListener {
        void onClick(int orderId);
    }
    public void setOnConfirmClickListener(OrderSettleListAdapter.OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    //---------事件 end---------

}

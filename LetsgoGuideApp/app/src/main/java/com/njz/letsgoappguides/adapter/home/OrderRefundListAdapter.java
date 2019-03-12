package com.njz.letsgoappguides.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.njz.letsgoappguides.model.home.OrderRefundBeanGroup;
import com.njz.letsgoappguides.model.home.OrderRefundChildModel;
import com.njz.letsgoappguides.model.home.OrderRefundModel;
import com.njz.letsgoappguides.ui.activites.home.CustomPlanActivity;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/8/21
 * Function:
 */

public class OrderRefundListAdapter extends RecyclerView.Adapter<OrderRefundListAdapter.BaseViewHolder> {


    private static final int ORDER_TYPE_TITLE = 10;
    private static final int ORDER_TYPE_FOOT = 12;

    Context mContext;
    List<OrderRefundModel> datas;

    private List<OrderRefundBeanGroup> orderBeanGroups = new ArrayList<>();

    public OrderRefundListAdapter(Context mContext, List<OrderRefundModel> datas) {
        this.mContext = mContext;
        this.datas = datas;

        setData(datas);
    }

    public void setData(List<OrderRefundModel> datas) {
        orderBeanGroups.clear();
        this.datas = datas;
        setData2(datas);
        notifyDataSetChanged();
    }

    public void addData(List<OrderRefundModel> datas){
        this.datas.addAll(datas);
        setData(this.datas);
    }

    public List<OrderRefundModel> getData(){
        return datas;
    }

    public OrderRefundModel getItem(int position){
        return this.datas.get(position);
    }

    public void setData2(List<OrderRefundModel> datas) {
        if (datas != null) {
            for (int i = 0;i<datas.size();i++){
                OrderRefundBeanGroup serviceInfoGroup = new OrderRefundBeanGroup();
                OrderRefundModel orderModel = datas.get(i);

                serviceInfoGroup.setLabelTab(OrderRefundBeanGroup.LABEL_TAB_TITLE);
                serviceInfoGroup.setOrderNo(orderModel.getOrderNo());
                serviceInfoGroup.setId(orderModel.getId());
                serviceInfoGroup.setName(orderModel.getName());
                serviceInfoGroup.setPayStatus(orderModel.getPayStatus());
                serviceInfoGroup.setOrderStatus(orderModel.getOrderStatus());
                serviceInfoGroup.setReviewStatus(Constant.ORDER_EVALUATE_NO);
                serviceInfoGroup.setRefundStatus(orderModel.getRefundStatus());
                serviceInfoGroup.setOrderId(orderModel.getOrderId());
                orderBeanGroups.add(serviceInfoGroup);
                for (int j = 0; j<orderModel.getNjzChildOrderToRefundVOS().size();j++){
                    OrderRefundBeanGroup serviceInfoGroup2 = new OrderRefundBeanGroup();
                    OrderRefundChildModel orderChildModel = orderModel.getNjzChildOrderToRefundVOS().get(j);
                    serviceInfoGroup2.setLabelTab(OrderRefundBeanGroup.LABEL_TAB_DEFAULT);
                    serviceInfoGroup2.setOrderChildModel(orderChildModel);
                    serviceInfoGroup2.setId(orderModel.getId());
                    serviceInfoGroup2.setPayStatus(orderModel.getPayStatus());
                    serviceInfoGroup2.setOrderId(orderModel.getOrderId());
                    orderBeanGroups.add(serviceInfoGroup2);
                }
                OrderRefundBeanGroup serviceInfoGroup3 = new OrderRefundBeanGroup();
                serviceInfoGroup3.setLabelTab(OrderRefundBeanGroup.LABEL_TAB_FOOT);
                serviceInfoGroup3.setPayStatus(orderModel.getPayStatus());
                serviceInfoGroup3.setOrderPrice(orderModel.getRefundMoney());
                serviceInfoGroup3.setOrderStatus(orderModel.getOrderStatus());
                serviceInfoGroup3.setReviewStatus(Constant.ORDER_EVALUATE_NO);
                serviceInfoGroup3.setName(orderModel.getName());
                serviceInfoGroup3.setId(orderModel.getId());
                serviceInfoGroup3.setRefundStatus(orderModel.getRefundStatus());
                serviceInfoGroup3.setOrderNo(orderModel.getOrderNo());
                serviceInfoGroup3.setLocation(orderModel.getLocation());
                serviceInfoGroup3.setRefundMoney(orderModel.getRefundMoney());
                serviceInfoGroup3.setMobile(orderModel.getMobile());
                serviceInfoGroup3.setOrderId(orderModel.getOrderId());
                OrderRefundChildModel infos=new OrderRefundChildModel();
                infos.setValue(orderModel.getNjzChildOrderToRefundVOS().get(0).getValue());
                serviceInfoGroup3.setOrderChildModel(infos);
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
        if (orderBeanGroups.get(position).getLabelTab() == OrderRefundBeanGroup.LABEL_TAB_TITLE)
            return ORDER_TYPE_TITLE;
        if (orderBeanGroups.get(position).getLabelTab() == OrderRefundBeanGroup.LABEL_TAB_FOOT)
            return ORDER_TYPE_FOOT;
        return super.getItemViewType(position);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof DefaultHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderRefundChildModel data = orderBeanGroups.get(pos).getOrderChildModel();
            if (data == null) return;

            GlideUtil.LoadRoundImage(mContext,data.getTitleImg(),((DefaultHolder) holder).iv_img,5);

            ((DefaultHolder) holder).tv_title.setText(data.getTitle());

//            ((DefaultHolder) holder).tv_price.setText("￥" + data.getPrice());
            ((DefaultHolder) holder).tv_price.setText(data.getValuestr());


            ((DefaultHolder) holder).tv_total_price.setText("￥" + data.getOrderPrice());

            if (mOnItemClickListener != null) {//payStatus==3已取消  payStatus==4退款
                ((DefaultHolder) holder).ll_order_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(orderBeanGroups.get(pos).getId(),orderBeanGroups.get(pos).getPayStatus(),orderBeanGroups.get(pos).getOrderId());
                    }
                });
            }

        }

        if (holder instanceof TitleHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderRefundBeanGroup data = orderBeanGroups.get(pos);
            if (data == null) return;

            ((TitleHolder) holder).tv_order.setText(data.getOrderNo());
            ((TitleHolder) holder).tv_status.setText(data.getPayStatusStr());
            ((TitleHolder) holder).tv_name.setText(data.getName());

            if(mOnItemClickListener != null){//payStatus==3已取消  payStatus==4退款
                ((TitleHolder) holder).rl_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(data.getId(),data.getPayStatus(),data.getOrderId());
                    }
                });
            }
        }

        if (holder instanceof FootHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderRefundBeanGroup data = orderBeanGroups.get(pos);
            if (data == null) return;

            ((FootHolder) holder).tv_order_price_title.setText("合计:");
            ((FootHolder) holder).tv_order_price_content.setText("" + data.getRefundMoney());
            ((FootHolder) holder).tv_order.setText(data.getOrderNo());

            ((FootHolder) holder).setbtn();
            switch (data.getPayStatus()){
                case Constant.ORDER_PAY_REFUND:
                    switch (data.getRefundStatus()){
                        case Constant.ORDER_REFUND_WAIT:
                            ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                            ((FootHolder) holder).btn_refund.setVisibility(View.VISIBLE);
                            break;
                        case Constant.ORDER_REFUND_PROCESS:
                            ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                            break;
                        case Constant.ORDER_REFUND_FINISH:
                            ((FootHolder) holder).foot_btns.setVisibility(View.GONE);
                            break;
                    }
                    ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.GONE);
                    if(data.getOrderChildModel().getValue().equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)) {//私人订制
                        ((FootHolder) holder).foot_btns.setVisibility(View.VISIBLE);
                        ((FootHolder) holder).btn_view_desingn_scheme.setVisibility(View.VISIBLE);
                        switch (data.getRefundStatus()){
                            case Constant.ORDER_REFUND_WAIT:
                                ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                                ((FootHolder) holder).btn_refund.setVisibility(View.VISIBLE);
                                break;
                            case Constant.ORDER_REFUND_PROCESS:
                            case Constant.ORDER_REFUND_FINISH:
//                                ((FootHolder) holder).btn_call.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                    break;
//                case Constant.ORDER_PAY_CANCEL:
//
//                    break;
            }

            ((FootHolder) holder).btn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtil.getInstance().showGuideMobileDialog(mContext,data.getMobile());
                }
            });

            ((FootHolder) holder).btn_refund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//退款
                    if (onConfirmClickListener != null) {
                        onConfirmClickListener.onClick(data.getId());
                    }
                }
            });

            ((FootHolder) holder).btn_view_desingn_scheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//查看方案
                    Intent intent = new Intent(mContext,CustomPlanActivity.class);
                    intent.putExtra("ORDER_ID",data.getOrderId());
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

    public class TitleHolder extends OrderRefundListAdapter.BaseViewHolder implements View.OnClickListener {
        TextView tv_order,tv_status,tv_name;
        RelativeLayout rl_status;

        TitleHolder(View itemView) {
            super(itemView);
            tv_order = itemView.findViewById(R.id.tv_order);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_status = itemView.findViewById(R.id.tv_status);
            rl_status = itemView.findViewById(R.id.rl_status);

            rl_status.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class DefaultHolder extends OrderRefundListAdapter.BaseViewHolder {
        ImageView iv_img;
        TextView tv_title,tv_price,tv_num,tv_total_price;
        LinearLayout ll_order_item;

        DefaultHolder(View itemView) {
            super(itemView);
            ll_order_item = itemView.findViewById(R.id.ll_order_item);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_comment);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);
        }
    }


    public class FootHolder extends OrderRefundListAdapter.BaseViewHolder {
        TextView tv_order_price_content,tv_order_price_title ,tv_order;
        TextView btn_refuse_order, btn_call, btn_confirm_order, btn_refund,btn_view_desingn_scheme;
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
            btn_view_desingn_scheme = itemView.findViewById(R.id.btn_view_desingn_scheme);

            foot_btns = itemView.findViewById(R.id.foot_btns);
        }

        public void setbtn(){
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
        void onClick(int ids,int payStatus,int orderId);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    OnConfirmClickListener onConfirmClickListener;
    public interface OnConfirmClickListener {
        void onClick(int orderId);
    }
    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    //---------事件 end---------

}

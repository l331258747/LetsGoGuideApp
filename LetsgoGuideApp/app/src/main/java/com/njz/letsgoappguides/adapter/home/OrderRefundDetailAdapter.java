package com.njz.letsgoappguides.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderRefundDetailChildModel;
import com.njz.letsgoappguides.ui.activites.service.ServicePreviewActivity;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/8/16
 * Function:
 */

public class OrderRefundDetailAdapter extends RecyclerView.Adapter<OrderRefundDetailAdapter.ViewHolder> {

    private Context context;
    private List<OrderRefundDetailChildModel> datas;

    public OrderRefundDetailAdapter(Context context, List<OrderRefundDetailChildModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setData(List<OrderRefundDetailChildModel> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_order_detail_refund, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof ViewHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderRefundDetailChildModel data = datas.get(pos);
            if (data == null) return;

            //信息
            GlideUtil.LoadRoundImage(context, data.getTitleImg(), holder.iv_img,5);
            holder.tv_title.setText(data.getTitle());
            holder.tv_price_unit.setText(data.getValuestr());
            holder.tv_price_total.setText("￥" + data.getOrderPrice());

            holder.ll_count.setVisibility(View.VISIBLE);
            if(data.getServeType() == Constant.SERVER_TYPE_GUIDE_ID || data.getServeType() == Constant.SERVER_TYPE_CUSTOM_ID){
                holder.ll_count.setVisibility(View.GONE);
            }
            holder.tv_count_content.setText(data.getCountContent());

            holder.tv_time_title.setText(data.getTimeTitle());
            holder.tv_time_content.setText(data.getTravelDate());
            holder.tv_location_content.setText(data.getLocation());

            if(data.getRefundStatus() == Constant.ORDER_REFUND_CANCEL ||  data.getRefundStatus() == Constant.ORDER_REFUND_PLAN_REFUSE){
                holder.ll_travel_money.setVisibility(View.GONE);
            }else{
                holder.ll_travel_money.setVisibility(View.VISIBLE);
                holder.tv_penalty_content.setText("￥"+data.getDefaultMoney());
                holder.tv_refund_price_content.setText("￥"+data.getRefundMoney());
            }

            if(data.getGuideOrPlatform() == 0){
                holder.tv_GM_cancel.setVisibility(View.VISIBLE);
                holder.tv_penalty_content.setTextColor(ContextCompat.getColor(context,R.color.color_99));
                holder.tv_penalty_content.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                holder.tv_penalty_content.setTextColor(ContextCompat.getColor(context,R.color.color_ff4000));
                holder.tv_penalty_content.getPaint().setFlags(0);
            }

            holder.ll_travel_going.setVisibility(View.GONE);
            if(data.getChildOrderStatus() == Constant.ORDER_TRAVEL_GOING){
                holder.ll_travel_going.setVisibility(View.VISIBLE);
                holder.tv_travel_day_content.setText(data.getUseDay()+"天");
                holder.tv_not_travel_day_content.setText(data.getUnUseDay()+"天");
                holder.tv_used_price_content.setText("￥" + data.getUseMoney());
            }

            holder.fl_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ServicePreviewActivity.class);
                    intent.putExtra("SERVICE_ID",data.getServeId());
                    intent.putExtra("PREVIEWID",Constant.PREVIEWIDDETAIL);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_title, tv_price_unit, tv_price_total;
        LinearLayout ll_count,ll_travel_going,ll_travel_money,ll_bug_get;
        TextView tv_count_title, tv_count_content, tv_time_title, tv_time_content
                ,tv_travel_day_content,tv_used_price_content,tv_not_travel_day_content,tv_GM_cancel,tv_bug_get_content,tv_location_content;
        FrameLayout fl_parent;

        TextView tv_penalty_title,tv_penalty_content,tv_refund_price_title,tv_refund_price_content;

        public ViewHolder(View itemView) {
            super(itemView);
            fl_parent = itemView.findViewById(R.id.fl_parent);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            ll_travel_going = itemView.findViewById(R.id.ll_travel_going);
            tv_price_unit = itemView.findViewById(R.id.tv_price_unit);
            tv_GM_cancel = itemView.findViewById(R.id.tv_GM_cancel);
            tv_price_total = itemView.findViewById(R.id.tv_price_total);
            ll_count = itemView.findViewById(R.id.ll_count);
            tv_bug_get_content = itemView.findViewById(R.id.tv_bug_get_content);
            ll_bug_get = itemView.findViewById(R.id.ll_bug_get);
            tv_time_content = itemView.findViewById(R.id.tv_time_content);
            tv_time_title = itemView.findViewById(R.id.tv_time_title);
            tv_count_content = itemView.findViewById(R.id.tv_count_content);
            tv_count_title = itemView.findViewById(R.id.tv_count_title);
            tv_penalty_title = itemView.findViewById(R.id.tv_penalty_title);
            tv_penalty_content = itemView.findViewById(R.id.tv_penalty_content);
            tv_refund_price_title = itemView.findViewById(R.id.tv_refund_price_title);
            tv_refund_price_content = itemView.findViewById(R.id.tv_refund_price_content);
            tv_travel_day_content = itemView.findViewById(R.id.tv_travel_day_content);
            tv_used_price_content = itemView.findViewById(R.id.tv_used_price_content);
            tv_not_travel_day_content = itemView.findViewById(R.id.tv_not_travel_day_content);
            ll_travel_money = itemView.findViewById(R.id.ll_travel_money);
            tv_location_content = itemView.findViewById(R.id.tv_location_content);
        }
    }
}

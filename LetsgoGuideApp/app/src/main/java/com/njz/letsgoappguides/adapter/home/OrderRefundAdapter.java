package com.njz.letsgoappguides.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderRefundDetailChildModel;
import com.njz.letsgoappguides.ui.activites.home.RefundRuleActivity;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/9/26
 * Function:
 */

public class OrderRefundAdapter extends RecyclerView.Adapter<OrderRefundAdapter.ViewHolder> {

    Context context;
    List<OrderRefundDetailChildModel> datas;

    public OrderRefundAdapter(Context context, List<OrderRefundDetailChildModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setData(List<OrderRefundDetailChildModel> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_refund, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder == null) return;
        final int pos = holder.getAdapterPosition();
        final OrderRefundDetailChildModel data = datas.get(pos);

        GlideUtil.LoadRoundImage(context, data.getTitleImg(), holder.iv_img, 5);
        holder.tv_title.setText(data.getTitle());
        holder.tv_one_price.setText(data.getValuestr());
        holder.tv_total_price.setText("￥" + data.getOrderPrice());

        holder.tv_not_travel_price.setText("￥" + data.getDefaultMoney());
        holder.tv_refund_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RefundRuleActivity.class);
                intent.putExtra("serviceId",data.getChildOrderId());
                intent.putExtra("serverType",data.getServeType());
                context.startActivity(intent);

            }
        });
        holder.tv_refund_price.setText("￥" + data.getRefundMoney());

        holder.rl_travel_day.setVisibility(View.GONE);
        holder.rl_travel_price.setVisibility(View.GONE);
        holder.rl_not_travel_day.setVisibility(View.GONE);

        if(data.getChildOrderStatus() == Constant.ORDER_TRAVEL_GOING){
            holder.rl_travel_day.setVisibility(View.VISIBLE);
            holder.rl_travel_price.setVisibility(View.VISIBLE);
            holder.rl_not_travel_day.setVisibility(View.VISIBLE);

            holder.tv_travel_day.setText(data.getUseDay() + "天");
            holder.tv_travel_price.setText("￥" + data.getUseMoney());
            holder.tv_not_travel_day.setText(data.getUnUseDay() + "天");

        }

        holder.tv_time_title.setText(data.getTimeTitle());
        holder.tv_time_content.setText(data.getTravelDate());
        holder.tv_count_content.setText(data.getCountContent());

        //平台扣除违约金 不显示按钮
        if(data.isPlatformCancel()){
            holder.rl_checkbox.setVisibility(View.GONE);
            data.setCheck(true);
        }else{
            holder.rl_checkbox.setVisibility(View.VISIBLE);
            holder.iv_checkbox.setImageDrawable(ContextCompat.getDrawable(context,data.isCheck()?R.mipmap.ic_checkbox:R.mipmap.ic_checkbox_un));
        }

        holder.rl_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setCheck(!data.isCheck());
                holder.iv_checkbox.setImageDrawable(ContextCompat.getDrawable(context,data.isCheck()?R.mipmap.ic_checkbox:R.mipmap.ic_checkbox_un));
                if(mOnCheckClickListener != null){
                    mOnCheckClickListener.onCheckClick();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_img;
        TextView tv_title,tv_one_price,tv_total_price,tv_travel_day,tv_travel_price,tv_count_content;
        TextView tv_not_travel_day,tv_not_travel_price,tv_refund_rule,tv_refund_price;
        RelativeLayout rl_travel_day,rl_travel_price,rl_not_travel_day,rl_not_travel_price,rl_refund_price;
        RelativeLayout rl_time;
        TextView tv_time_content,tv_time_title;
        LinearLayout rl_checkbox;
        ImageView iv_checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            rl_checkbox = itemView.findViewById(R.id.rl_checkbox);
            iv_checkbox = itemView.findViewById(R.id.iv_checkbox);


            rl_time = itemView.findViewById(R.id.rl_time);
            tv_time_content = itemView.findViewById(R.id.tv_time_content);
            tv_time_title = itemView.findViewById(R.id.tv_time_title);
            tv_count_content = itemView.findViewById(R.id.tv_count_content);

            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_one_price = itemView.findViewById(R.id.tv_one_price);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);

            tv_travel_day = itemView.findViewById(R.id.tv_travel_day);//出行天数
            tv_travel_price = itemView.findViewById(R.id.tv_travel_price);//价格
            tv_not_travel_day = itemView.findViewById(R.id.tv_not_travel_day);//未出行天数
            tv_not_travel_price = itemView.findViewById(R.id.tv_not_travel_price);//违约金
            tv_refund_rule = itemView.findViewById(R.id.tv_refund_rule);//规则
            tv_refund_price = itemView.findViewById(R.id.tv_refund_price);//退款金额

            rl_travel_day = itemView.findViewById(R.id.rl_travel_day);
            rl_travel_price = itemView.findViewById(R.id.rl_travel_price);
            rl_not_travel_day = itemView.findViewById(R.id.rl_not_travel_day);
            rl_not_travel_price = itemView.findViewById(R.id.rl_not_travel_price);
            rl_refund_price = itemView.findViewById(R.id.rl_refund_price);
        }
    }

    OnCheckClickListener mOnCheckClickListener;

    public interface OnCheckClickListener {
        void onCheckClick();
    }

    public void setOnCheckClickListener(OnCheckClickListener onCheckClickListener) {
        this.mOnCheckClickListener = onCheckClickListener;
    }

}

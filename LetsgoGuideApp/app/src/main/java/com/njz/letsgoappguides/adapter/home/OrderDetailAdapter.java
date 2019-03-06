package com.njz.letsgoappguides.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderDetailChildModel;
import com.njz.letsgoappguides.ui.activites.service.ServicePreviewActivity;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/8/16
 * Function:
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private Context context;
    private List<OrderDetailChildModel> datas;

    public OrderDetailAdapter(Context context, List<OrderDetailChildModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setData(List<OrderDetailChildModel> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_order_submit, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof ViewHolder) {
            final int pos = holder.getAdapterPosition();
            final OrderDetailChildModel data = datas.get(pos);
            if (data == null) return;

            GlideUtil.LoadRoundImage(context, data.getTitleImg(), holder.iv_img,5);
            holder.tv_title.setText(data.getTitle());
            holder.tv_price_unit.setText(data.getValuestr());
            holder.tv_price_total.setText(data.getOrderPriceStr());

            if(data.getServeType() == Constant.SERVER_TYPE_GUIDE_ID || data.getServeType() == Constant.SERVER_TYPE_CUSTOM_ID){
                holder.ll_count.setVisibility(View.GONE);
            }

            holder.tv_count_content.setText(data.getCountContent());
            holder.tv_time_content.setText(data.getTravelDate());

            holder.tv_time_title.setText(data.getTimeTitle());
            holder.tv_location_content.setText(data.getLocation());

            if(data.getServeType() == Constant.SERVER_TYPE_CUSTOM_ID){
                holder.ll_bug_get.setVisibility(View.VISIBLE);
                holder.tv_bug_get.setText(data.getBugGet()+"");
            }else{
                holder.ll_bug_get.setVisibility(View.GONE);
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
        TextView tv_title, tv_price_unit,  tv_price_total;
        LinearLayout ll_count,ll_time,ll_bug_get;
        TextView  tv_count_content, tv_time_title, tv_time_content,tv_bug_get,tv_location_content;
        FrameLayout fl_parent;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            fl_parent = itemView.findViewById(R.id.fl_parent);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price_unit = itemView.findViewById(R.id.tv_price_unit);
            tv_price_total = itemView.findViewById(R.id.tv_price_total);
            ll_count = itemView.findViewById(R.id.ll_count);
            ll_time = itemView.findViewById(R.id.ll_time);
            ll_bug_get = itemView.findViewById(R.id.ll_bug_get);
            tv_time_content = itemView.findViewById(R.id.tv_time_content);
            tv_time_title = itemView.findViewById(R.id.tv_time_title);
            tv_count_content = itemView.findViewById(R.id.tv_count_content);
            tv_bug_get = itemView.findViewById(R.id.tv_bug_get);
            tv_location_content = itemView.findViewById(R.id.tv_location_content);

        }
    }
}

package com.njz.letsgoappguides.adapter.server;

import android.content.Context;
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
import com.njz.letsgoappguides.customview.widget.PriceView;
import com.njz.letsgoappguides.model.server.ServerListModel;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerListAdapter2 extends RecyclerView.Adapter<ServerListAdapter2.ViewHolder> {


    Context context;
    List<ServerListModel> datas;

    public ServerListAdapter2(Context context, List<ServerListModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_server2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder == null) return;
        final int pos = holder.getAdapterPosition();
        final ServerListModel data = datas.get(pos);
        if (data == null) return;

        if (!TextUtils.isEmpty(data.getTitleImg())) {
            String titleImg = data.getTitleImg();
            ArrayList<String> list = StringUtils.stringToList(titleImg);
            GlideUtil.LoadRoundImage(context, list.get(0), holder.iv_img, 0);
        }
        holder.tv_server_name.setText(data.getTitle());
        holder.tv_location.setText(data.getAddress());
        holder.tv_server_type.setText(data.getServeTypeName());
        holder.pv_price.setPrice(data.getServePrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void addData(List<ServerListModel> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setData(List<ServerListModel> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<ServerListModel> getDatas() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout cardView;
        ImageView iv_img;
        TextView tv_server_name, tv_location, tv_server_type;
        PriceView pv_price;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_server_name = itemView.findViewById(R.id.tv_server_name);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_server_type = itemView.findViewById(R.id.tv_server_type);
            pv_price = itemView.findViewById(R.id.pv_price);
        }
    }

    //---------事件 start---------
    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ServerListModel model);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    //---------事件 end---------
}

package com.njz.letsgoappguides.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.model.settlement.IncomeListInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHodler>{


    Context mContext;
    List<IncomeListInfo> mData;

    public IncomeAdapter(Context mContext, List<IncomeListInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        if (holder == null) return;
        final int pos = holder.getAdapterPosition();
        final IncomeListInfo data = mData.get(pos);
        if (data == null) return;
        holder.tv_income_title.setText(data.getOrderNo());
        holder.tv_income_datetinme.setText(data.getBalanceTime());
        holder.tv_income_money.setText("￥"+data.getBalancePrice());
        holder.ll_income_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(data.getId(),data.getRefId(),data.getBalancePrice(),data.getPlatformMoney());
            }
        });
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_income, parent, false);
        return new IncomeAdapter.ViewHodler(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{
        TextView tv_income_title,tv_income_datetinme,tv_income_money;
        LinearLayout ll_income_id;

        public ViewHodler(View itemView) {
            super(itemView);

            tv_income_title = itemView.findViewById(R.id.tv_income_title);
            tv_income_datetinme = itemView.findViewById(R.id.tv_income_datetinme);
            tv_income_money = itemView.findViewById(R.id.tv_income_money);
            ll_income_id = itemView.findViewById(R.id.ll_income_id);

        }
    }

    public void setData(List<IncomeListInfo> dynamis) {
        this.mData = dynamis;
        notifyDataSetChanged();
    }


    public void addData(List<IncomeListInfo> dynamis) {
        this.mData.addAll(dynamis);
        notifyDataSetChanged();
    }

    //---------事件 start---------
    OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onClick(int orderNo,int refId,float priceA,float priceB);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

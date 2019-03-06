package com.njz.letsgoappguides.adapter.mine;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHodler> {

    Context mContext;
    List<GuideMacroEntityList> datas;

    public LanguageAdapter(Context mContext, List<GuideMacroEntityList> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public LanguageAdapter.ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_language, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(final LanguageAdapter.ViewHodler holder, int position) {
        if (holder == null) return;
        final int pos = holder.getAdapterPosition();
        final GuideMacroEntityList data = datas.get(pos);
        if (data == null) return;

        holder.tv_name.setText(data.getName());

        holder.iv_checkbox.setImageDrawable(ContextCompat.getDrawable(mContext,data.isSelect()?R.mipmap.reg_check:R.mipmap.no_check_view));

        holder.rl_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setSelect(!data.isSelect());
                holder.iv_checkbox.setImageDrawable(ContextCompat.getDrawable(mContext,data.isSelect()?R.mipmap.reg_check:R.mipmap.no_check_view));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class ViewHodler extends RecyclerView.ViewHolder{
        RelativeLayout rl_parent;
        TextView tv_name;
        ImageView iv_checkbox;

        public ViewHodler(View itemView) {
            super(itemView);

            rl_parent = itemView.findViewById(R.id.rl_parent);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_checkbox = itemView.findViewById(R.id.iv_checkbox);
        }
    }
}

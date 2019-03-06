package com.njz.letsgoappguides.adapter.pop;

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
import com.njz.letsgoappguides.model.other.PopupSelectModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {

    private Context context;
    private List<PopupSelectModel> data;

    public SimpleTextAdapter(Context context, List<PopupSelectModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_simple_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.simpleText.setText(data.get(position).getName());
        holder.rl_simple_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onClick(position);
                }
            }
        });

        if(data.get(position).isSelect()){
            holder.simpleText.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
            holder.iv_simple_text.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_select));
        }else{
            holder.simpleText.setTextColor(ContextCompat.getColor(context,R.color.color_text));
            holder.iv_simple_text.setImageDrawable(null);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView simpleText;
        ImageView iv_simple_text;
        RelativeLayout rl_simple_text;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleText = itemView.findViewById(R.id.simple_text);
            iv_simple_text = itemView.findViewById(R.id.iv_simple_text);
            rl_simple_text = itemView.findViewById(R.id.rl_simple_text);
        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

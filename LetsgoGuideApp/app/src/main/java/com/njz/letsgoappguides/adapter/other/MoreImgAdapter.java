package com.njz.letsgoappguides.adapter.other;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.ui.activites.mine.BigImageActivity;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2019/3/18
 * Function:
 */

public class MoreImgAdapter extends RecyclerView.Adapter<MoreImgAdapter.ViewHolder> {

    public final static int TYPE_ADD = 1;
    public final static int TYPE_PHOTO = 2;

    public final static int MAX = 6;

    List<String> images;
    Context context;

    public MoreImgAdapter(Context context, List<String> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public MoreImgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_more_img, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreImgAdapter.ViewHolder holder, final int position) {
        if (holder == null) return;

        if (holder.getItemViewType() == TYPE_ADD) {
            holder.iv_img.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.add_img_big));
            holder.iv_img.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.iv_delete.setVisibility(View.GONE);
            holder.iv_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        onClickListener.onClick();
                    }
                }
            });
        } else {
            final String str = images.get(position);
            if (TextUtils.isEmpty(str)) return;

            holder.iv_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtil.LoadImage(context, str, holder.iv_img);
            holder.iv_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BigImageActivity.startActivity((Activity) context, position, images);
                }
            });
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onDelect(position);
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == images.size()) {
            return TYPE_ADD;
        }
        return TYPE_PHOTO;
    }

    @Override
    public int getItemCount() {
        int count = images.size() + 1;
        if (count > MAX) {
            count = MAX;
        }
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img,iv_delete;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            iv_delete = itemView.findViewById(R.id.iv_delete);
        }
    }

    OnClickListener onClickListener;

    public void onClickLisenter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick();
        void onDelect(int position);
    }
}

package com.njz.letsgoappguides.adapter.mine;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.SimpleImageAdapter;
import com.njz.letsgoappguides.customview.widget.DynamicImageView;
import com.njz.letsgoappguides.customview.widget.myTextView.ShowAllTextView;
import com.njz.letsgoappguides.model.evaluation.ReviewList;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalContract;
import com.njz.letsgoappguides.presenter.mine.ReviewEvalPresenter;
import com.njz.letsgoappguides.ui.activites.mine.BigImageActivity;
import com.njz.letsgoappguides.util.GsonUtil;
import com.njz.letsgoappguides.util.ToastUtil;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/11/12.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.DynamicViewHolder> {

    List<ReviewList> dynamis;
    Context mContext;

    public DynamicAdapter(Context context, List<ReviewList> dynamis) {
        this.dynamis = dynamis;
        this.mContext = context;
    }

    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.evaluation_item_dynamic, parent, false);
        return new DynamicViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(final DynamicViewHolder holder, final int position) {
        if (holder == null) return;
        final int pos = holder.getAdapterPosition();
        final ReviewList data = dynamis.get(pos);
        if (data == null) return;

        GlideUtil.LoadCircleImage(mContext, data.getImgUrl(), holder.iv_img);
        holder.tv_name.setText(data.getNickname());
        holder.tv_time.setText(data.getUserDate());
        holder.round_eval_tv.setText(data.getScore() + "");//圆形评分

        holder.eval_tv_guide.setVisibility(View.GONE);
        holder.eval_tv_arrange.setVisibility(View.GONE);
        holder.eval_tv_car.setVisibility(View.GONE);
        holder.eval_tv_service.setVisibility(View.GONE);
        if(data.getServiceAttitude() > 0 ){
            holder.eval_tv_guide.setVisibility(View.VISIBLE);
            holder.eval_tv_guide.setText(data.getServiceAttitudeStr());
        }
        if(data.getTravelArrange() > 0 ){
            holder.eval_tv_arrange.setVisibility(View.VISIBLE);
            holder.eval_tv_arrange.setText(data.getTravelArrangeStr());
        }
        if(data.getCarCondition() > 0){
            holder.eval_tv_car.setVisibility(View.VISIBLE);
            holder.eval_tv_car.setText(data.getCarConditionStr());
        }
        if(data.getServiceQuality() > 0){
            holder.eval_tv_service.setVisibility(View.VISIBLE);
            holder.eval_tv_service.setText(data.getServiceQualityStr());
        }

        if (TextUtils.isEmpty(data.getUserContent())) {//内容
            holder.tv_content.setVisibility(View.GONE);
        } else {
            holder.tv_content.setVisibility(View.VISIBLE);
            holder.tv_content.setText("" + data.getUserContent());
        }
        if (TextUtils.isEmpty(data.getGuideContent())) {//回复内容
            holder.eval_fl.setVisibility(View.GONE);
            holder.tv_backInfo.setVisibility(View.VISIBLE);
        } else {
            holder.eval_fl.setVisibility(View.VISIBLE);
            holder.tv_backInfo.setVisibility(View.GONE);

            holder.tv_content_back.setText("" + data.getGuideContent());
            holder.tv_time_back.setText(data.getGuideDate());//回复时间
        }

        holder.mRecyclerView.setNestedScrollingEnabled(false);//滑动取消
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(
                holder.mRecyclerView.getContext(), 4));

        if (data.getImageUrls() == null || data.getImageUrls().size() == 0) {
            holder.ll_photo.setVisibility(View.GONE);
        } else {
            holder.ll_photo.setVisibility(View.VISIBLE);
            SimpleImageAdapter enterAdapter = new SimpleImageAdapter(mContext, data.getImageUrls());
            enterAdapter.setOnItemClickListener(new SimpleImageAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    BigImageActivity.startActivity((Activity) mContext, position, data.getImageUrls());
                }
            });
            holder.mRecyclerView.setAdapter(enterAdapter);
        }


        holder.tv_backInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView contentBack = holder.tv_content_back;
                mOnItemClickListener.onItemClick(pos, data.getUserId(), data.getId(), data.getOrderId(), contentBack);

            }
        });

        holder.tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDetailClickListener==null) return;
                onDetailClickListener.onItemClick(data.getOrderId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dynamis.size();
    }

    //--------------View Holder start----------------
    public class DynamicViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_parent;
        ImageView iv_img;
        TextView tv_name, tv_time, tv_location, eval_tv_guide, eval_tv_arrange, eval_tv_car, eval_tv_service, round_eval_tv, tv_time_back;
        TextView tv_content, tv_content_back;
        RelativeLayout rl_head;
        RelativeLayout eval_fl;
        Button tv_backInfo;
        LinearLayout ll_photo;
        RecyclerView mRecyclerView;


        DynamicViewHolder(View itemView) {
            super(itemView);
            ll_parent = itemView.findViewById(R.id.ll_parent);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            eval_tv_guide = itemView.findViewById(R.id.eval_tv_guide);
            eval_tv_arrange = itemView.findViewById(R.id.eval_tv_arrange);
            eval_tv_car = itemView.findViewById(R.id.eval_tv_car);
            eval_tv_service = itemView.findViewById(R.id.eval_tv_service);
            round_eval_tv = itemView.findViewById(R.id.round_eval_tv);
            tv_time_back = itemView.findViewById(R.id.tv_time_back);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_content_back = itemView.findViewById(R.id.tv_content_back);
            tv_location = itemView.findViewById(R.id.tv_location);
            rl_head = itemView.findViewById(R.id.rl_head);
            eval_fl = itemView.findViewById(R.id.eval_fl);
            tv_backInfo = itemView.findViewById(R.id.tv_backInfo);

            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            ll_photo = itemView.findViewById(R.id.ll_photo);
        }

    }


    //--------------View Holder end----------------

    public ReviewList getItem(int position) {
        return this.dynamis.get(position);
    }

    public List<ReviewList> getDatas() {
        return this.dynamis;
    }

    public void setData(List<ReviewList> dynamis) {
        this.dynamis = dynamis;
        notifyDataSetChanged();
    }


    public void setItemData(int position) {
        notifyItemChanged(position);
    }

    public void addData(List<ReviewList> dynamis) {
        this.dynamis.addAll(dynamis);
        notifyDataSetChanged();
    }

    //---------事件 start---------
    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, String userId, int id, int orderId, TextView contentBack);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    OnDetailClickListener onDetailClickListener;
    public interface OnDetailClickListener {
        void onItemClick(int orderId);
    }

    public void setOnDetailClickListener(OnDetailClickListener onDetailClickListener) {
        this.onDetailClickListener = onDetailClickListener;
    }

    //---------事件 end---------


}

package com.njz.letsgoappguides.customview.widget.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.pop.SimpleTextAdapter;
import com.njz.letsgoappguides.model.other.PopupSelectModel;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.density.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerListPop extends BackgroundDarkPopupWindow {

    private View contentView;
    private Context context;

    private RecyclerView recyclerView;
    private SimpleTextAdapter simpleTextAdapter;

    private List<PopupSelectModel> datas;

    public ServerListPop(final Context context, View parentView,List<PopupSelectModel> datas) {
        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        this.context = context;
        this.datas = datas;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_server_list, null);

        this.setContentView(contentView);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        // 实例化一个ColorDrawable颜色为半透明
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        this.setOutsideTouchable(false);

        this.setFocusable(true);

        initView();

    }
    private void initView() {
        recyclerView = contentView.findViewById(R.id.recycler_view);

        ViewGroup.LayoutParams lp = recyclerView.getLayoutParams();
        if (datas.size() > 5) {
            lp.height = AppUtils.dip2px(40 * 5);
            recyclerView.setLayoutParams(lp);
        }

    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        simpleTextAdapter = new SimpleTextAdapter(context, datas);
        recyclerView.setAdapter(simpleTextAdapter);
        simpleTextAdapter.setOnItemClickListener(new SimpleTextAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if(onItemClickLisener == null) return;
                dismissPopupWindow();
                onItemClickLisener.onClick(position);
            }
        });
    }

    public void showPopupWindow(View parent) {
        if(datas ==null || datas.size()==0) return;
        if (!this.isShowing()) {
            initRecycler();

            setDarkStyle(-1);
            setDarkColor(Color.parseColor("#a0000000"));
            resetDarkPosition();
            darkBelow(parent);
            showAsDropDown(parent, 0, 0);
        }
    }

    public void dismissPopupWindow() {
        if (this.isShowing())
            this.dismiss();
    }

    OnItemClickLisener onItemClickLisener;
    public interface OnItemClickLisener {
        void onClick(int position);
    }
    public void setOnitemClickLisener(OnItemClickLisener onItemClickLisener) {
        this.onItemClickLisener = onItemClickLisener;
    }
}

package com.njz.letsgoappguides.util.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.home.OrderListAdapter;
import com.njz.letsgoappguides.adapter.mine.LanguageAdapter;
import com.njz.letsgoappguides.model.home.OrderListModel;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.ui.activites.mysetting.PersonalActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguoqiang on 2018/1/5.
 */

public class LanguageDialog extends Dialog implements View.OnClickListener {

    Activity mContext;
    RelativeLayout layoutParent;
    TextView tv_close,tv_confirm;
    RecyclerView recyclerView;

    List<GuideMacroEntityList> data;
    TextView personalTvCheckb;

    public LanguageDialog(Activity context,TextView personalTvCheckb) {
        super(context);
        mContext = context;
        this.personalTvCheckb=personalTvCheckb;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_language, null);
        this.setContentView(layout);

        getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        layoutParent = layout.findViewById(R.id.layout_parent);
        tv_close = layout.findViewById(R.id.tv_close);
        tv_confirm = layout.findViewById(R.id.tv_confirm);
        recyclerView = layout.findViewById(R.id.recycler_view);

        tv_close.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);//确定
        layoutParent.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        LanguageAdapter mAdapter = new LanguageAdapter(mContext, data);
        recyclerView.setAdapter(mAdapter);
    }


    public void onClick(View view) {
        if (view.getId() == R.id.tv_confirm) {
            //TODO 提供接口在外部请求提交数据
            personalTvCheckb.setText(getLanguage());
        }
        dismiss();
    }

    private String getLanguage() {
        StringBuffer languages = new StringBuffer("");
        for (int i=0;i<data.size();i++){
            if(data.get(i).isSelect()){
                languages.append(data.get(i).getName()+",");
            }else{
                data.get(i).setSelect(false);
            }
        }
        String str = languages.toString();
        str = str.endsWith(",")?str.substring(0,str.length()-1):str;
        return str;
    }

    public void setData(List<GuideMacroEntityList> data) {
        if(data == null || data.size() == 0) return;
        for (GuideMacroEntityList item : data) {
            for (GuideMacroEntityList item2 : MySelfInfo.getInstance().getGuideMacroEntityList()) {
                if (item.getId() == item2.getId()) {
                    item.setSelect(true);
                    continue;
                }
            }
        }

        this.data = data;
    }

    public List<GuideMacroEntityList> getData() {
        List<GuideMacroEntityList> list=new ArrayList<GuideMacroEntityList>();
        for (int i=0;i<data.size();i++){
            if(data.get(i).isSelect()){
                list.add(data.get(i));
            }
        }
        return list;
    }
}

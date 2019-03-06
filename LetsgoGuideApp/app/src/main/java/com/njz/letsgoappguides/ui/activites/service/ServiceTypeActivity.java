package com.njz.letsgoappguides.ui.activites.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.ServerTypeModel;
import com.njz.letsgoappguides.presenter.server.ServerTypeContract;
import com.njz.letsgoappguides.presenter.server.ServerTypePresenter;
import com.njz.letsgoappguides.ui.activites.mysetting.GuideAuthenticationActivity;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.ServiceTypeEvent;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/12/25
 * Function:
 */

public class ServiceTypeActivity extends BaseActivity implements View.OnClickListener, ServerTypeContract.View{

    public static final int SERVER_TYPE_GUIDE_ID = 1;
    public static final int SERVER_TYPE_FEATURE_ID = 4;
    public static final int SERVER_TYPE_CUSTOM_ID = 3;
    public static final int SERVER_TYPE_HOTEL_ID = 5;
    public static final int SERVER_TYPE_TICKET_ID = 6;
    public static final int SERVER_TYPE_CAR_ID = 2;

    TitleView titleView;

    TextView tv_guide,tv_car,tv_custom,tv_feature,tv_hotel,tv_ticket,tv_submit;


    ServerTypePresenter mPresenter;
    GetServeDicListModel.NjzGuideServeDicVoListBean model;
    List<GetServeDicListModel.NjzGuideServeDicVoListBean> lists;
    int serverType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_type;
    }

    @Override
    protected void initView() {
        serverType = getIntent().getIntExtra("SERVER_TYPE",-1);

        titleView = $(R.id.titleView);
        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_submit = $(R.id.tv_submit);
        tv_guide = $(R.id.tv_guide);
        tv_car = $(R.id.tv_car);
        tv_custom = $(R.id.tv_custom);
        tv_feature = $(R.id.tv_feature);
        tv_hotel = $(R.id.tv_hotel);
        tv_ticket = $(R.id.tv_ticket);

        tv_guide.setOnClickListener(this);
        tv_car.setOnClickListener(this);
        tv_custom.setOnClickListener(this);
        tv_feature.setOnClickListener(this);
        tv_hotel.setOnClickListener(this);
        tv_ticket.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

        initData();
    }

    private void initData() {
        mPresenter = new ServerTypePresenter(context,this);
        mPresenter.getServeDicList(true);
    }

    public void setBtnSelect(int serveType){
        setCleanBtn();
        switch (serveType){
            case SERVER_TYPE_GUIDE_ID:
                tv_guide.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_theme33_hollow));
                tv_guide.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                break;
            case SERVER_TYPE_FEATURE_ID:
                tv_feature.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_theme33_hollow));
                tv_feature.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                break;
            case SERVER_TYPE_CUSTOM_ID:
                tv_custom.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_theme33_hollow));
                tv_custom.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                break;
            case SERVER_TYPE_HOTEL_ID:
                tv_hotel.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_theme33_hollow));
                tv_hotel.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                break;
            case SERVER_TYPE_TICKET_ID:
                tv_ticket.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_theme33_hollow));
                tv_ticket.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                break;
            case SERVER_TYPE_CAR_ID:
                tv_car.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_theme33_hollow));
                tv_car.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                break;
        }
        if(lists == null){
            return;
        }
        for (int i =0 ;i<lists.size();i++){
            if(lists.get(i).getId() == serveType){
                model = lists.get(i);
            }
        }
    }

    public void setCleanBtn(){
        tv_guide.setBackgroundColor(ContextCompat.getColor(context,R.color.color_d9));
        tv_guide.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        tv_feature.setBackgroundColor(ContextCompat.getColor(context,R.color.color_d9));
        tv_feature.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        tv_custom.setBackgroundColor(ContextCompat.getColor(context,R.color.color_d9));
        tv_custom.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        tv_hotel.setBackgroundColor(ContextCompat.getColor(context,R.color.color_d9));
        tv_hotel.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        tv_ticket.setBackgroundColor(ContextCompat.getColor(context,R.color.color_d9));
        tv_ticket.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        tv_car.setBackgroundColor(ContextCompat.getColor(context,R.color.color_d9));
        tv_car.setTextColor(ContextCompat.getColor(context,R.color.color_text));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_guide:
                setBtnSelect(SERVER_TYPE_GUIDE_ID);
                break;
            case R.id.tv_car:
                if(!TextUtils.equals(MySelfInfo.getInstance().getDriveViable(),"2")){
                    DialogUtil.getInstance().getDefaultDialog(context, "驾驶证认证提醒",
                            "完成驾驶证认证后，才能发布包车接送服务内容，赶快去认证吧！", "去认证", new DialogUtil.DialogCallBack() {
                                @Override
                                public void exectEvent(DialogInterface alterDialog) {
                                    startActivity(new Intent(context, GuideAuthenticationActivity.class));
                                }
                            }).show();
                    return;
                }
                setBtnSelect(SERVER_TYPE_CAR_ID);
                break;
            case R.id.tv_custom:
                if(!TextUtils.equals(MySelfInfo.getInstance().getGuideViable(),"2")){
                    DialogUtil.getInstance().getDefaultDialog(context, "导游资格认证提醒",
                            "完成导游资格认证后，才能发布私人定制服务内容，赶快去认证吧！", "去认证", new DialogUtil.DialogCallBack() {
                                @Override
                                public void exectEvent(DialogInterface alterDialog) {
                                    startActivity(new Intent(context, GuideAuthenticationActivity.class));
                                }
                            }).show();
                    return;
                }
                setBtnSelect(SERVER_TYPE_CUSTOM_ID);
                break;
            case R.id.tv_feature:
                setBtnSelect(SERVER_TYPE_FEATURE_ID);
                break;
            case R.id.tv_hotel:
                setBtnSelect(SERVER_TYPE_HOTEL_ID);
                break;
            case R.id.tv_ticket:
                setBtnSelect(SERVER_TYPE_TICKET_ID);
                break;
            case R.id.tv_submit:
                if(model == null || lists == null){
                    return;
                }
                RxBus2.getInstance().post(new ServiceTypeEvent(model));
                finish();
                break;
        }
    }

    @Override
    public void serveGetServeSuccess(List<ServerTypeModel> datas) {

    }

    @Override
    public void serveGetServeFailed(String msg) {

    }

    @Override
    public void getServeDicListSuccess(GetServeDicListModel datas) {
        lists = datas.getNjzGuideServeDicVoList();
        setBtnSelect(serverType);
    }

    @Override
    public void getServeDicListFailed(String msg) {
        showShortToast(msg);
    }
}

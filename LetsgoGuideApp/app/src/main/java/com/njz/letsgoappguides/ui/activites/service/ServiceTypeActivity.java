package com.njz.letsgoappguides.ui.activites.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
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

    LinearLayout ll_guide,ll_car,ll_custom,ll_feature,ll_hotel,ll_ticket;


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

        ll_guide = $(R.id.ll_guide);
        ll_car = $(R.id.ll_car);
        ll_custom = $(R.id.ll_custom);
        ll_feature = $(R.id.ll_feature);
        ll_hotel = $(R.id.ll_hotel);
        ll_ticket = $(R.id.ll_ticket);

        ll_guide.setOnClickListener(this);
        ll_car.setOnClickListener(this);
        ll_custom.setOnClickListener(this);
        ll_feature.setOnClickListener(this);
        ll_hotel.setOnClickListener(this);
        ll_ticket.setOnClickListener(this);

        initData();
    }

    private void initData() {
        mPresenter = new ServerTypePresenter(context,this);
        mPresenter.getServeDicList(true);
    }

    public void setBtnSelect(int serveType){
        if(lists == null){
            return;
        }
        for (int i =0 ;i<lists.size();i++){
            if(lists.get(i).getId() == serveType){
                model = lists.get(i);
            }
        }

        if(model == null || lists == null){
            return;
        }
        RxBus2.getInstance().post(new ServiceTypeEvent(model));
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_guide:
                setBtnSelect(SERVER_TYPE_GUIDE_ID);
                break;
            case R.id.ll_car:
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
            case R.id.ll_custom:
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
            case R.id.ll_feature:
                setBtnSelect(SERVER_TYPE_FEATURE_ID);
                break;
            case R.id.ll_hotel:
                setBtnSelect(SERVER_TYPE_HOTEL_ID);
                break;
            case R.id.ll_ticket:
                setBtnSelect(SERVER_TYPE_TICKET_ID);
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
    }

    @Override
    public void getServeDicListFailed(String msg) {
        showShortToast(msg);
    }
}

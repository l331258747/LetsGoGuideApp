package com.njz.letsgoappguides.presenter.server;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.server.ServiceCalPriceInfo;
import com.njz.letsgoappguides.model.server.ServiceDetailInfo;

import java.util.List;


public class ServerPricePresenter implements ServerPriceContract.Presenter {

    Context context;
    ServerPriceContract.View iView;

    public ServerPricePresenter(Context context, ServerPriceContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getServeCalPriceInfo(int njzFormatIdId) {
        ResponseCallback listener = new ResponseCallback<List<ServiceCalPriceInfo>>() {
            @Override
            public void onSuccess(List<ServiceCalPriceInfo> infos) {
                iView.getServeCalPriceInfoSuccess(infos);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getServeCalPriceInfoFailed(errorMsg);
            }
        };
        MethodApi.getServeFormatPrice(njzFormatIdId,new ProgressSubscriber(listener, context));
    }


    @Override
    public void getMonthPriceInfo(int njzFormatId, String time) {
        ResponseCallback listener = new ResponseCallback<List<ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean>>() {
            @Override
            public void onSuccess(List<ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean> infos) {
                iView.getMonthPriceInfoSuccess(infos);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getMonthPriceInfoFailed(errorMsg);
            }
        };
        MethodApi.getMonthPrice(njzFormatId,time,new ProgressSubscriber(listener, context));
    }
}

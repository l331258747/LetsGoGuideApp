package com.njz.letsgoappguides.presenter.server;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.BasePageModel;
import com.njz.letsgoappguides.model.server.ServerListModel;
import com.njz.letsgoappguides.model.server.ServiceDetailInfo;


public class ServerDetailPresenter implements ServerDetailContract.Presenter {

    Context context;
    ServerDetailContract.View iView;

    public ServerDetailPresenter(Context context, ServerDetailContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getServeDetail(int njzGuideServeId){
        ResponseCallback listener = new ResponseCallback<ServiceDetailInfo>() {
            @Override
            public void onSuccess(ServiceDetailInfo datainfo) {
                iView.getServeDetailSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getServeDetailFailed(errorMsg);
            }
        };
        MethodApi.getServeDetail(njzGuideServeId,new ProgressSubscriber(listener, context));
    }
}

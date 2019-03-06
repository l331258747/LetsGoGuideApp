package com.njz.letsgoappguides.presenter.server;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.GetUpdateServiceInfo;


public class GetUpdateServInfoPresenter implements GetUpdateServInfoContract.Presenter {

    Context context;
    GetUpdateServInfoContract.View iView;

    public GetUpdateServInfoPresenter(Context context, GetUpdateServInfoContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getUpdateServInfo(int njzGuideServeId) {
        ResponseCallback listener = new ResponseCallback<GetUpdateServiceInfo>() {
            @Override
            public void onSuccess(GetUpdateServiceInfo infos) {
                iView.getUpdateServInfoSuccess(infos);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getUpdateServInfoFailed(errorMsg);
            }
        };
        MethodApi.getUpdaServiceInfo(njzGuideServeId,new ProgressSubscriber(listener, context,true));
    }

    @Override
    public void updaServiceInfo(AutoServiceModel infos) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String infos) {
                iView.updaServiceInfoSuccess(infos);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.updaServiceInfoFailed(errorMsg);
            }
        };
        MethodApi.updaServiceInfo(infos,new ProgressSubscriber(listener, context,true));
    }
}

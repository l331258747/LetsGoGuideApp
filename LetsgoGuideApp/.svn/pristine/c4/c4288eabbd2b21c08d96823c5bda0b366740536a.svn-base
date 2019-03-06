package com.njz.letsgoappguides.presenter.server;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.CityModel;

import java.util.List;


public class AddServicePresenter implements AddServiceContract.Presenter {

    Context context;
    AddServiceContract.View iView;

    public AddServicePresenter(Context context, AddServiceContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void addService(AutoServiceModel data) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String str) {
                iView.addServiceSuccess(str);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.addServiceFailed(errorMsg);
            }
        };
        MethodApi.addService(data,new ProgressSubscriber(listener, context,true));
    }
}

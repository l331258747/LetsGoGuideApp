package com.njz.letsgoappguides.presenter.server;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class GetCityPresenter implements GetCityContract.Presenter {

    Context context;
    GetCityContract.View iView;

    public GetCityPresenter(Context context, GetCityContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void cityGetCity() {
        ResponseCallback listener = new ResponseCallback<List<CityModel>>() {
            @Override
            public void onSuccess(List<CityModel> datainfo) {
                iView.cityGetCitySuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.cityGetCityFailed(errorMsg);
            }
        };
        MethodApi.cityGetCity(new ProgressSubscriber(listener, context,false));
    }

    @Override
    public void getServiceCityList() {
        ResponseCallback listener = new ResponseCallback<List<GetServiceCityModel>>() {
            @Override
            public void onSuccess(List<GetServiceCityModel> datainfo) {
                Log.e("test",datainfo.toString());
                iView.getServiceCityListSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getServiceCityListFailed(errorMsg);
            }
        };
        MethodApi.getAreaListAndChildrens(new ProgressSubscriber(listener, context,false));
    }
}

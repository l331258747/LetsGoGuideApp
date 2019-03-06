package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.HomeModel;

/**
 * Created by LGQ
 * Time: 2018/11/12
 * Function:
 */

public class HomePresenter implements HomeContract.Presenter {

    Context context;
    HomeContract.View iView;

    public HomePresenter(Context context, HomeContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderIndex() {
        ResponseCallback listener = new ResponseCallback<HomeModel>() {
            @Override
            public void onSuccess(HomeModel datainfo) {
                iView.orderIndexSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderIndexFailed(errorMsg);
            }
        };
        MethodApi.orderIndex(new ProgressSubscriber(listener, context,false));
    }
}

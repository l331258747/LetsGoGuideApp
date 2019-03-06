package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.HomeModel;


public class AddOrderRemarkPresenter implements AddOrderRemarkContract.Presenter {

    Context context;
    AddOrderRemarkContract.View iView;

    public AddOrderRemarkPresenter(Context context, AddOrderRemarkContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void addOrderNote(int orderId,String orderNote) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.addOrderNoteSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.addOrderNoteFailed(errorMsg);
            }
        };
        MethodApi.addOrderNote(orderId,orderNote,new ProgressSubscriber(listener, context,false));
    }
}

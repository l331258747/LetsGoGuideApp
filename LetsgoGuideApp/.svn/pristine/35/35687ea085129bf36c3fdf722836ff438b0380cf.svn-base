package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderConfirmPresenter implements OrderConfirmContract.Presenter {
    Context context;
    OrderConfirmContract.View iView;

    public OrderConfirmPresenter(Context context, OrderConfirmContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderSureOrder(int orderId) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.orderSureOrderSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderSureOrderFailed(errorMsg);
            }
        };
        MethodApi.orderSureOrder(orderId,new ProgressSubscriber(listener, context));
    }

    @Override
    public void guideSurePersonalServe(int orderId,int planStatus,String refuseReason, String refuseExplain) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.guideSurePersonalServeSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.guideSurePersonalServeFailed(errorMsg);
            }
        };
        MethodApi.guideSurePersonalServe(orderId, planStatus, refuseReason, refuseExplain,new ProgressSubscriber(listener, context));
    }
}

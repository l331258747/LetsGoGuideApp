package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.OrderDetailModel;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderDetailPresenter implements OrderDetailContract.Presenter{

    Context context;
    OrderDetailContract.View iView;

    public OrderDetailPresenter(Context context, OrderDetailContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderQueryOrder(int orderId) {
        ResponseCallback listener = new ResponseCallback<OrderDetailModel>() {
            @Override
            public void onSuccess(OrderDetailModel datainfo) {
                iView.orderQueryOrderSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderQueryOrderFailed(errorMsg);
            }
        };
        MethodApi.orderQueryOrder(orderId,new ProgressSubscriber(listener, context));
    }
}

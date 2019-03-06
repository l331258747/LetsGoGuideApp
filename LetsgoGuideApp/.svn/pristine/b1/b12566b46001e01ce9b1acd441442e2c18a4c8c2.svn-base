package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.OrderDetailModel;
import com.njz.letsgoappguides.model.home.OrderRefundDetailModel;

import java.net.ContentHandler;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderRefundDetailPresenter implements OrderRefundDetailContract.Presenter {

    Context context;
    OrderRefundDetailContract.View iView;

    public OrderRefundDetailPresenter(Context context, OrderRefundDetailContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderQueryRefundOrder(int refundId) {
        ResponseCallback listener = new ResponseCallback<OrderRefundDetailModel>() {
            @Override
            public void onSuccess(OrderRefundDetailModel datainfo) {
                iView.orderQueryRefundOrderSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderQueryRefundOrderFailed(errorMsg);
            }
        };
        MethodApi.orderQueryRefundOrder(refundId,new ProgressSubscriber(listener, context));
    }
}

package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.send.SendOrderRefundChildModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/15
 * Function:
 */

public class OrderRefundPresenter implements OrderRefundContract.Presenter{
    Context context;
    OrderRefundContract.View iView;

    public OrderRefundPresenter(Context context, OrderRefundContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderSureRefundOrder(List<SendOrderRefundChildModel> maps) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.orderSureRefundOrderSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderSureRefundOrderFailed(errorMsg);
            }
        };
        MethodApi.orderSureRefundOrder(maps,new ProgressSubscriber(listener, context));
    }
}

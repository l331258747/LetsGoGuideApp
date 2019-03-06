package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.CancelReasonListInfo;
import com.njz.letsgoappguides.model.home.HomeModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderRefusePresenter implements OrderRefuseContract.Presenter {
    Context context;
    OrderRefuseContract.View iView;

    public OrderRefusePresenter(Context context, OrderRefuseContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderRefuseOrder(int orderId, String refuseReason, String refuseExplain) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.orderRefuseOrderSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderRefuseOrderFailed(errorMsg);
            }
        };
        MethodApi.orderRefuseOrder(orderId,refuseReason,refuseExplain,new ProgressSubscriber(listener, context));
    }

    @Override
    public void queryCancelReasonList() {
        ResponseCallback listener = new ResponseCallback<List<CancelReasonListInfo>>() {
            @Override
            public void onSuccess(List<CancelReasonListInfo> datainfo) {
                iView.queryCancelReasonListSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.queryCancelReasonListFailed(errorMsg);
            }
        };
        MethodApi.queryCancelReasonList(new ProgressSubscriber(listener, context));
    }
}

package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.settlement.OrderSettleBalanceChildModel;
import com.njz.letsgoappguides.model.settlement.OrderSettltRefundChildModel;

/**
 * Created by Administrator on 2018/11/26.
 */

public class SettleOrderDetailPresenter implements SettleOrderDetailContract.Presenter{

    SettleOrderDetailContract.View iView;
    Context context;

    public SettleOrderDetailPresenter(SettleOrderDetailContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void querySettleFinDetail(int orderId) {
        ResponseCallback listener = new ResponseCallback<OrderSettleBalanceChildModel>() {
            @Override
            public void onSuccess(OrderSettleBalanceChildModel data) {
                iView.querySettleFinDetailSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.querySettleFinDetailFailed(errorMsg);
            }
        };
        MethodApi.queryAwaitBalanceChildOrder( orderId, new ProgressSubscriber(listener, context));
    }

    @Override
    public void querySettlefulDetail(int refundId) {
        ResponseCallback listener = new ResponseCallback<OrderSettltRefundChildModel>() {
            @Override
            public void onSuccess(OrderSettltRefundChildModel data) {
                iView.querySettlefulDetailSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.querySettlefulDetailFailed(errorMsg);
            }
        };
        MethodApi.queryRefundChildOrder( refundId, new ProgressSubscriber(listener, context));
    }
}

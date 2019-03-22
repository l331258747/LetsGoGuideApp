package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.settlement.OrderSettleModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderSettleListPresenter implements OrderSettleListContract.Presenter {

    Context context;
    OrderSettleListContract.View iView;

    public OrderSettleListPresenter(Context context, OrderSettleListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderSettleList(int page, int limit, String search) {
        ResponseCallback listener = new ResponseCallback<List<OrderSettleModel>>() {
            @Override
            public void onSuccess(List<OrderSettleModel> datainfo) {
                iView.orderSettleListSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderSettleListFailed(errorMsg);
            }
        };
        MethodApi.querySettlementOrderList(page, limit, search, new ProgressSubscriber(listener, context, false));
    }
}

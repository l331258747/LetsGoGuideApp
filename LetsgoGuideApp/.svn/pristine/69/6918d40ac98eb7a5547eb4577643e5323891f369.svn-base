package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.BasePageModel;
import com.njz.letsgoappguides.model.home.OrderListModel;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderListPresenter implements OrderListContract.Presenter {

    Context context;
    OrderListContract.View iView;

    public OrderListPresenter(Context context, OrderListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void orderList(int payStatus, int orderStatus, int page, int limit, String search) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<OrderListModel>>() {
            @Override
            public void onSuccess(BasePageModel<OrderListModel> datainfo) {
                iView.orderListSuccess(datainfo.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderListFailed(errorMsg);
            }
        };
        MethodApi.orderList(payStatus, orderStatus, page, limit, search, new ProgressSubscriber(listener, context,false));
    }
}

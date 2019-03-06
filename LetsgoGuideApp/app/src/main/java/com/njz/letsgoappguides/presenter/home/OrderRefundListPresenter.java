package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.BasePageModel;
import com.njz.letsgoappguides.model.home.OrderRefundModel;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderRefundListPresenter implements OrderRefundListContract.Presenter {

    Context context;
    OrderRefundListContract.View iView;

    public OrderRefundListPresenter(Context context, OrderRefundListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderRefundList(int page, int limit, String search) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<OrderRefundModel>>() {
            @Override
            public void onSuccess(BasePageModel<OrderRefundModel> datainfo) {
                iView.orderRefundListSuccess(datainfo.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderRefundListFailed(errorMsg);
            }
        };
        MethodApi.orderRefundList(page, limit, search, new ProgressSubscriber(listener, context, false));
    }
}

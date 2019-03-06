package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.OrderDesign2Info;
import com.njz.letsgoappguides.model.home.OrderDesignInfo;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderDesingnPresenter implements OrderDesingnContract.Presenter {
    Context context;
    OrderDesingnContract.View iView;

    public OrderDesingnPresenter(Context context, OrderDesingnContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderDesingn(int orderId) {
        ResponseCallback listener = new ResponseCallback<List<OrderDesignInfo>>() {
            @Override
            public void onSuccess(List<OrderDesignInfo> datainfo) {
                iView.orderDesingnSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderDesingnFailed(errorMsg);
            }
        };
        MethodApi.orderQueryDesingnInfo(orderId,new ProgressSubscriber(listener, context));
    }

    @Override
    public void orderOfferDesign(OrderDesign2Info orderDesignInfo) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.orderOfferDesignSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderOfferDesignFailed(errorMsg);
            }
        };
        MethodApi.orderOfferDesign(orderDesignInfo,new ProgressSubscriber(listener, context));
    }
}

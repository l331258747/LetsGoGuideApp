package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.EvaluateModel2;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public class OrderEvaluatePresenter implements OrderEvaluateContract.Presenter {

    Context context;
    OrderEvaluateContract.View iView;

    public OrderEvaluatePresenter(Context context, OrderEvaluateContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderQueryOrderReview(int orderId) {
        ResponseCallback listener = new ResponseCallback<EvaluateModel2>() {
            @Override
            public void onSuccess(EvaluateModel2 datainfo) {
                iView.orderQueryOrderReviewSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderQueryOrderReviewFailed(errorMsg);
            }
        };
        MethodApi.orderQueryOrderReview(orderId,new ProgressSubscriber(listener, context,false));
    }
}

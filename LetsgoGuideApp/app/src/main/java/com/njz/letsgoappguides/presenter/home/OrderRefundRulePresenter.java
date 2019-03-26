package com.njz.letsgoappguides.presenter.home;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.ServiceRefundRuleParentModel;

/**
 * Created by LGQ
 * Time: 2018/11/16
 * Function:
 */

public class OrderRefundRulePresenter implements OrderRefundRuleContract.Presenter{

    Context context;
    OrderRefundRuleContract.View iView;

    public OrderRefundRulePresenter(Context context, OrderRefundRuleContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void orderRefundRule(int serverId) {
        ResponseCallback listener = new ResponseCallback<ServiceRefundRuleParentModel>() {
            @Override
            public void onSuccess(ServiceRefundRuleParentModel datainfo) {
                iView.orderRefundRuleSuccess(datainfo.getNjzGuideServeChildPlanEntity());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.orderRefundRuleFailed(errorMsg);
            }
        };
        MethodApi.orderRefundRule(serverId,new ProgressSubscriber(listener, context));
    }
}

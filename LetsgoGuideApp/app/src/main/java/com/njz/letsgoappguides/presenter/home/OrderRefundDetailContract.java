package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.OrderRefundDetailModel;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public interface OrderRefundDetailContract {

    interface Presenter {
        void orderQueryRefundOrder(int refundId);
    }

    interface View {
        void orderQueryRefundOrderSuccess(OrderRefundDetailModel datas);
        void orderQueryRefundOrderFailed(String msg);
    }
}

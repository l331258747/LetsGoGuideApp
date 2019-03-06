package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.OrderDetailModel;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public interface OrderDetailContract {

    interface Presenter {
        void orderQueryOrder(int orderId);
    }

    interface View {
        void orderQueryOrderSuccess(OrderDetailModel datas);
        void orderQueryOrderFailed(String msg);
    }
}

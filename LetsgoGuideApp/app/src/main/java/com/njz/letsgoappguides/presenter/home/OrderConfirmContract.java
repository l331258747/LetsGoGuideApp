package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.HomeModel;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public interface OrderConfirmContract  {

    interface Presenter {
        void orderSureOrder(int orderId);
        void guideSurePersonalServe(int orderId,int planStatus,String refuseReason,String refuseExplain);
    }

    interface View {
        void orderSureOrderSuccess(String datas);
        void orderSureOrderFailed(String msg);
        void guideSurePersonalServeSuccess(String datas);
        void guideSurePersonalServeFailed(String msg);
    }
}

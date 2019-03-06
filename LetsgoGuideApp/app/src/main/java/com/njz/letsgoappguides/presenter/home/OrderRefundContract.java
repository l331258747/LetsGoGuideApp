package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.send.SendOrderRefundChildModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/15
 * Function:
 */

public interface OrderRefundContract {

    interface Presenter {
        void orderSureRefundOrder(List<SendOrderRefundChildModel> maps);
    }

    interface View {
        void orderSureRefundOrderSuccess(String datas);
        void orderSureRefundOrderFailed(String msg);
    }
}

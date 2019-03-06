package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.OrderRefundModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public interface OrderRefundListContract {

    interface Presenter {
        void orderRefundList(int page,int limit,String search);
    }

    interface View {
        void orderRefundListSuccess(List<OrderRefundModel> datas);
        void orderRefundListFailed(String msg);
    }

}

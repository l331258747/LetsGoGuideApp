package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.CancelReasonListInfo;
import com.njz.letsgoappguides.model.home.HomeModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public interface OrderRefuseContract {

    interface Presenter {
        void orderRefuseOrder(int orderId,String refuseReason,String refuseExplain);
        void queryCancelReasonList();
    }

    interface View {
        void orderRefuseOrderSuccess(String datas);
        void orderRefuseOrderFailed(String msg);
        void queryCancelReasonListSuccess(List<CancelReasonListInfo> datas);
        void queryCancelReasonListFailed(String msg);
    }

}

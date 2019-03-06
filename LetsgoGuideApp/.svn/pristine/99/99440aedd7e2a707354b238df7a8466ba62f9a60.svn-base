package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.HomeModel;
import com.njz.letsgoappguides.model.home.OrderListModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public interface OrderListContract  {

    interface Presenter {
        void orderList(int payStatus,int orderStatus, int page,int limit,String search);
    }

    interface View {
        void orderListSuccess(List<OrderListModel> datas);
        void orderListFailed(String msg);
    }
}

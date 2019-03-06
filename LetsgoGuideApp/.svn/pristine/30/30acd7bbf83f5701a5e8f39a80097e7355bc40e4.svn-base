package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.OrderDesign2Info;
import com.njz.letsgoappguides.model.home.OrderDesignInfo;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public interface OrderDesingnContract {

    interface Presenter {
        void orderDesingn(int orderId);
        void orderOfferDesign(OrderDesign2Info orderDesignInfo);
    }

    interface View {
        void orderDesingnSuccess(List<OrderDesignInfo> datas);
        void orderDesingnFailed(String msg);
        void orderOfferDesignSuccess(String datas);
        void orderOfferDesignFailed(String msg);
    }
}

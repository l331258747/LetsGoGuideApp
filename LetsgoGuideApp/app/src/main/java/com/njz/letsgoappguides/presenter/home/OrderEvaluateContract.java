package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.EvaluateModel2;
import com.njz.letsgoappguides.model.home.OrderDetailModel;

/**
 * Created by LGQ
 * Time: 2018/11/14
 * Function:
 */

public interface OrderEvaluateContract {

    interface Presenter {
        void orderQueryOrderReview(int orderId);
    }

    interface View {
        void orderQueryOrderReviewSuccess(EvaluateModel2 datas);
        void orderQueryOrderReviewFailed(String msg);
    }
}

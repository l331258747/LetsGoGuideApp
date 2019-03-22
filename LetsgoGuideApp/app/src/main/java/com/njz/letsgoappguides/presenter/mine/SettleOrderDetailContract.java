package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.settlement.OrderSettleBalanceChildModel;
import com.njz.letsgoappguides.model.settlement.OrderSettltRefundChildModel;

/**
 * Created by Administrator on 2018/11/26.
 */

public interface SettleOrderDetailContract {

    interface Presenter {
        void querySettleFinDetail(int orderId);//结算已完成订单详情
        void querySettlefulDetail(int refundId);//结算退款订单详情
    }

    interface View {
        void querySettleFinDetailSuccess(OrderSettleBalanceChildModel str);
        void querySettleFinDetailFailed(String msg);

        void querySettlefulDetailSuccess(OrderSettltRefundChildModel str);
        void querySettlefulDetailFailed(String msg);
    }
}

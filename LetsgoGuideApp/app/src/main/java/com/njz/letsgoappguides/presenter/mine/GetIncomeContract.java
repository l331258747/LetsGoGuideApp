package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.settlement.IncomeInfo;

/**
 * Created by Administrator on 2018/11/26.
 */

public interface GetIncomeContract {

    interface Presenter {
        void getIncomeInfo(int page,int limit);
    }

    interface View {
        void getIncomeSuccess(IncomeInfo str);
        void getIncomeFailed(String msg);
    }
}

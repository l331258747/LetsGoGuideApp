package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.settlement.IncomeInfo;
import com.njz.letsgoappguides.model.settlement.IncomeListInfo;
import com.njz.letsgoappguides.model.settlement.PageUtilsBean;

/**
 * Created by Administrator on 2018/11/26.
 */

public class GetIncomePresenter implements GetIncomeContract.Presenter{

    GetIncomeContract.View iView;
    Context context;

    public GetIncomePresenter(GetIncomeContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getIncomeInfo(int page,int limit) {
        ResponseCallback listener = new ResponseCallback<IncomeInfo<PageUtilsBean<IncomeListInfo>>>() {
            @Override
            public void onSuccess(IncomeInfo<PageUtilsBean<IncomeListInfo>> data) {
                iView.getIncomeSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getIncomeFailed(errorMsg);
            }
        };
        MethodApi.getBalanceOrderList( page,limit, new ProgressSubscriber(listener, context,false));
    }

}

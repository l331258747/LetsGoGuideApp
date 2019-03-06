package com.njz.letsgoappguides.presenter.message;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.BasePageModel;
import com.njz.letsgoappguides.model.message.NotifyMainModel;


/**
 * Created by LGQ
 * Time: 2018/10/12
 * Function:
 */

public class NotifyListPresenter implements NotifyListContract.Presenter {

    Context context;
    NotifyListContract.View iView;

    public NotifyListPresenter(Context context, NotifyListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void msgPushGetReceiveMsgList(String type, int limit, int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<NotifyMainModel>>() {
            @Override
            public void onSuccess(BasePageModel<NotifyMainModel> data) {
                iView.msgPushGetReceiveMsgListSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.msgPushGetReceiveMsgListFailed(errorMsg);
            }
        };
        MethodApi.msgGetReceiveMsgList(type,limit,page,new ProgressSubscriber(listener,context,false));
    }
}

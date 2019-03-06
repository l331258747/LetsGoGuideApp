package com.njz.letsgoappguides.presenter.message;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.message.NotifyMainModel;

import java.util.List;


/**
 * Created by LGQ
 * Time: 2018/10/11
 * Function:
 */

public class NotifyMainPresenter implements NotifyMainContract.Presenter {

    Context context;
    NotifyMainContract.View iView;

    public NotifyMainPresenter(Context context, NotifyMainContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void msgPushGetSendMsgList() {
        ResponseCallback listener = new ResponseCallback<List<NotifyMainModel>>() {
            @Override
            public void onSuccess(List<NotifyMainModel> data) {
                iView.msgPushGetSendMsgListSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.msgPushGetSendMsgListFailed(errorMsg);
            }
        };
        MethodApi.msgReceiveKindList(new ProgressSubscriber(listener,context,false));
    }
}

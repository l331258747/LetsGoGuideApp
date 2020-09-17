package com.njz.letsgoappguides.presenter.im;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;

/**
 * Created by LGQ
 * Time: 2018/10/12
 * Function:
 */

public class IMPresenter implements IMContract.Presenter {

    Context context;
    IMContract.View iView;

    public IMPresenter(Context context, IMContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void saveMessage(String fromId, String toId, String chatType, String msg) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String  data) {
                iView.saveMessageSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.saveMessageFailed(errorMsg);
            }
        };
        MethodApi.saveMessage(fromId,toId,chatType,msg,new ProgressSubscriber(listener,context,false));
    }
}

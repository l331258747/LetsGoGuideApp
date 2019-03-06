package com.njz.letsgoappguides.presenter;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.login.Datas;

/**
 * Created by Administrator on 2018/11/6.
 */

public class ForgotPresenter implements ForgotContract.Presenter{

    ForgotContract.View iView;
    Context context;

    public ForgotPresenter(ForgotContract.View iView, Context context) {
        this.iView = iView;
        this.context = context;
    }

    @Override
    public void forgotPsd(String mobile,String captcha,String newPassword) {
        ResponseCallback listener=new ResponseCallback<String>() {
            @Override
            public void onSuccess(String msg) {
                iView.forgotPsdSuccess(msg);
            }

            @Override
            public void onFault(String msg) {
                iView.forgotPsdFailed(msg);
            }
        };
        MethodApi.msgResetPassword(mobile,captcha,newPassword,new ProgressSubscriber(listener,context));
    }

    @Override
    public void userSmsSend(String mobile, String type) {
        ResponseCallback listener=new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                iView.userSmsSendSuccess(s);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.userSmsSendFailed(errorMsg);
            }
        };
        MethodApi.userSmsSend(mobile, type, new ProgressSubscriber(listener, context));
    }
}

package com.njz.letsgoappguides.presenter.setting;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.presenter.ForgotContract;

/**
 * Created by Administrator on 2018/11/8.
 */

public class SetPhonePresenter implements SetPhoneContract.Presenter{

    SetPhoneContract.View iView;
    Context context;

    public SetPhonePresenter(SetPhoneContract.View iView, Context context) {
        this.iView = iView;
        this.context = context;
    }

    @Override
    public void SetPhone(String mobile,String captcha,String password) {
        ResponseCallback listener=new ResponseCallback<String>() {
            @Override
            public void onSuccess(String msg) {
                iView.SetPhoneSuccess(msg);
            }

            @Override
            public void onFault(String msg) {
                iView.SetPhoneFailed(msg);
            }
        };
        MethodApi.updateMobile(mobile,captcha,password,new ProgressSubscriber(listener,context));
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

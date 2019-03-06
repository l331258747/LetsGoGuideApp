package com.njz.letsgoappguides.presenter;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.login.Datas;

/**
 * Created by Administrator on 2018/11/5.
 */

public class RegisterPresenter implements RegisterContract.Presenter {


    RegisterContract.View iView;
    Context context;

    public RegisterPresenter(RegisterContract.View iView, Context context) {
        this.iView = iView;
        this.context = context;
    }

    @Override
    public void msgCheckRegister(String mobile, String password , String passwordSure, String captcha) {
        Log.e("test","msgCheckRegister");
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datas) {
                iView.msgCheckRegisterSuccess("成功");
                Log.e("test","onSuccess"+datas);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.msgCheckRegisterFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.register(mobile, password,passwordSure,captcha, new ProgressSubscriber(listener, context));
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

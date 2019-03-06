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

public class CodeLoginPresenter implements CodeLoginContract.Presenter{

    CodeLoginContract.View iView;
    Context context;

    public CodeLoginPresenter(CodeLoginContract.View view, Context context) {
        this.iView = view;
        this.context = context;
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

    @Override
    public void msgCheckLogin(String mobile, String captcha) {
        ResponseCallback listener = new ResponseCallback<Datas>() {
            @Override
            public void onSuccess(Datas datas) {
                iView.msgCheckLoginSuccess(datas);
                Log.e("test","onSuccess"+datas);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.msgCheckLoginFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.msgCheckLogin(mobile, captcha, new ProgressSubscriber(listener, context));
    }
}

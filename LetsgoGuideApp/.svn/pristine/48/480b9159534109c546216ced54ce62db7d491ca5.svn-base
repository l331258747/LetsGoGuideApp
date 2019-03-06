package com.njz.letsgoappguides.presenter;

import android.content.Context;
import android.util.Log;
import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.login.Datas;

/**
 * Created by Administrator on 2018/11/2.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View iView;
    Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void login(String mobile, String password) {
        Log.e("test","LoginPresenter");
        ResponseCallback listener = new ResponseCallback<Datas>() {
            @Override
            public void onSuccess(Datas datas) {
                iView.loginSuccess(datas);
                Log.e("test","onSuccess");
            }


            @Override
            public void onFault(String errorMsg) {
                iView.loginFailed(errorMsg);
                Log.e("test","onFault");
            }
        };


        //MethodApi.login(mobile, password, new ProgressSubscriber(listener, context));
        MethodApi.login(mobile, password, new ProgressSubscriber(listener, context));

    }



}

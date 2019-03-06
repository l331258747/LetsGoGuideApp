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

public class ExitLoginPresenter implements ExitLoginContract.Presenter {

    ExitLoginContract.View iView;
    Context context;

    public ExitLoginPresenter(ExitLoginContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void exitlogin() {
        Log.e("test","LoginPresenter");
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datas) {
                iView.exitloginSuccess(datas);
                Log.e("test","onSuccess");
            }


            @Override
            public void onFault(String errorMsg) {
                iView.exitloginFailed(errorMsg);
                Log.e("test","onFault");
            }
        };


        MethodApi.exitLogin(new ProgressSubscriber(listener, context));

    }



}

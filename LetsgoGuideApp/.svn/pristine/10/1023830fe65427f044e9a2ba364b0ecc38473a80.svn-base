package com.njz.letsgoappguides.presenter.setting;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.presenter.LoginContract;

/**
 * Created by Administrator on 2018/11/8.
 */

public class UpdataPsdPresenter implements UpdataPsdContract.Presenter{

    UpdataPsdContract.View iView;
    Context context;

    public UpdataPsdPresenter(UpdataPsdContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void updataPsd(String password,String newpassword) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String str) {
                iView.updataPsdSuccess(str);
                Log.e("test","onSuccess");
            }


            @Override
            public void onFault(String errorMsg) {
                iView.updataPsdFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.updataPassword(password,newpassword, new ProgressSubscriber(listener, context));
    }


}

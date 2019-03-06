package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.ToAuthenInfo;
import com.njz.letsgoappguides.model.login.UserVo;

/**
 * Created by Administrator on 2018/11/16.
 */

public class IdentityAuthenPresenter implements IdentityAuthenContract.Presenter{

    IdentityAuthenContract.View iView;
    Context context;

    public IdentityAuthenPresenter(IdentityAuthenContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void toAuthInfo(ToAuthenInfo data) {
        ResponseCallback listener = new ResponseCallback<UserVo>() {
            @Override
            public void onSuccess(UserVo str) {
                iView.toAuthInfoSuccess(str);
                Log.e("test","onSuccess"+str.toString());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.toAuthInfoFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.toAuthInfo( data, new ProgressSubscriber(listener, context));
    }

}

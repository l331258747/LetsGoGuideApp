package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.GuideValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;

/**
 * Created by Administrator on 2018/11/16.
 */

public class GuideAuthenPresenter implements GuideAuthenContract.Presenter{

    GuideAuthenContract.View iView;
    Context context;

    public GuideAuthenPresenter(GuideAuthenContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void toAuthInfo(GuideValidInfo data) {
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
        MethodApi.guideValidInfo( data, new ProgressSubscriber(listener, context));
    }

}

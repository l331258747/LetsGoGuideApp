package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;

/**
 * Created by Administrator on 2018/11/16.
 */

public class CarAuthenPresenter implements CarAuthenContract.Presenter{

    CarAuthenContract.View iView;
    Context context;

    public CarAuthenPresenter(CarAuthenContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void toAuthInfo(DriveValidInfo data) {
        ResponseCallback listener = new ResponseCallback<UserVo>() {
            @Override
            public void onSuccess(UserVo data ) {
                iView.toAuthInfoSuccess(data);
                Log.e("test","onSuccess"+data.toString());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.toAuthInfoFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.driveValidInfo( data, new ProgressSubscriber(listener, context));
    }

}

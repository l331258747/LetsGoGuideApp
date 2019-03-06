package com.njz.letsgoappguides.presenter.other;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.authentication.DriveTypeInfo;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/16
 * Function:
 */

public class DriveTypePresenter implements DriveTypeContract.Presenter {

    Context context;
    DriveTypeContract.View iView;

    public DriveTypePresenter(Context context, DriveTypeContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getDriveType() {
        ResponseCallback listener = new ResponseCallback<List<DriveTypeInfo>>() {
            @Override
            public void onSuccess(List<DriveTypeInfo> list) {
                iView.getDriveTypeSuccess(list);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getDriveTypeFailed(errorMsg);
            }
        };
        MethodApi.getDriveType(new ProgressSubscriber(listener, context));
    }
}

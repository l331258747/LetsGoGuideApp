package com.njz.letsgoappguides.presenter.other;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.home.OrderDetailModel;

import java.io.File;

/**
 * Created by LGQ
 * Time: 2018/11/16
 * Function:
 */

public class UpLoadPresenter implements UpLoadContract.Presenter {

    Context context;
    UpLoadContract.View iView;

    public UpLoadPresenter(Context context, UpLoadContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void upUpload(File file) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.upUploadSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.upUploadFailed(errorMsg);
            }
        };
        MethodApi.upUpload(file,new ProgressSubscriber(listener, context,false));
    }
}

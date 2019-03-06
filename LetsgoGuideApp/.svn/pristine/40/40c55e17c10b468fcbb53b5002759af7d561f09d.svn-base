package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.model.mine.BinkIntoInfo;
import com.njz.letsgoappguides.model.mine.GetBackListInfo;
import com.njz.letsgoappguides.model.mine.GetBinkInfo;
import com.njz.letsgoappguides.model.server.GetUpdateServiceInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */

public class BinkSavePresenter implements BinkSaveContract.Presenter{

    BinkSaveContract.View iView;
    Context context;

    public BinkSavePresenter(BinkSaveContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void toBinkSave(BinkIntoInfo data) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data ) {
                iView.toBinkSaveSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.toBinkSaveSuccess(errorMsg);
            }
        };
        MethodApi.binkSave( data, new ProgressSubscriber(listener, context));
    }

    @Override
    public void toBinkUpdate(BinkIntoInfo mBinkIntoInfo) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data ) {
                iView.toBinkUpdateSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.toBinkUpdateFailed(errorMsg);
            }
        };
        MethodApi.binkUpdate( mBinkIntoInfo, new ProgressSubscriber(listener, context));
    }

    @Override
    public void getBinkinfo() {
        ResponseCallback listener = new ResponseCallback<GetBinkInfo>() {
            @Override
            public void onSuccess(GetBinkInfo data ) {
                iView.getBinkinfoSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getBinkinfoFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.binkInfo(  new ProgressSubscriber(listener, context));
    }

    @Override
    public void getBackList() {
        ResponseCallback listener = new ResponseCallback<List<GetBackListInfo>>() {
            @Override
            public void onSuccess(List<GetBackListInfo> data ) {
                iView.getBackListSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getBackListFailed(errorMsg);
            }
        };
        MethodApi.getBackList(  new ProgressSubscriber(listener, context));
    }
}

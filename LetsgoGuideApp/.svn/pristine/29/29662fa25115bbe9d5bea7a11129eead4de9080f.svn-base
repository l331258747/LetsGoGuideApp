package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.authentication.AuthenInfo;

/**
 * Created by Administrator on 2018/11/16.
 */

public class QueryAuthenPresenter implements QueryAuthenContract.Presenter{

    QueryAuthenContract.View iView;
    Context context;

    public QueryAuthenPresenter(QueryAuthenContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void queryAuthen() {
        ResponseCallback listener = new ResponseCallback<AuthenInfo>() {
            @Override
            public void onSuccess(AuthenInfo infos) {
                iView.queryAuthenSuccess(infos);
                Log.e("test","onSuccess"+infos.toString());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.queryAuthenFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.queryAuthInfo( new ProgressSubscriber(listener, context));
    }
}

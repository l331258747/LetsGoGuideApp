package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.Result;
import com.njz.letsgoappguides.model.evaluation.Datainfo;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.presenter.LoginContract;

/**
 * Created by Administrator on 2018/11/9.
 */

public class MyEvaluationPresenter implements MyEvaluationContract.Presenter{


    MyEvaluationContract.View iView;
    Context context;

    public MyEvaluationPresenter(MyEvaluationContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getEvaluationInfo(int page, int limit, int type) {
        ResponseCallback listener = new ResponseCallback<Datainfo>() {
            @Override
            public void onSuccess(Datainfo datainfo) {
                iView.getEvaluationSuccess(datainfo);
                Log.e("test","onSuccess"+datainfo.toString());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getEvaluationFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.review(page, limit, type, new ProgressSubscriber(listener, context,false));
    }
}

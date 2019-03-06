package com.njz.letsgoappguides.presenter.setting;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.mine.FeedBackInfo;

/**
 * Created by Administrator on 2018/11/13.
 */

public class FeedBackPresenter implements FeedBackContract.Presenter{


    FeedBackContract.View iView;
    Context context;

    public FeedBackPresenter(FeedBackContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void saveFeedback(FeedBackInfo infos) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String str) {
                iView.saveFeedbackSuccess(str);
                Log.e("test","onSuccess");
            }


            @Override
            public void onFault(String errorMsg) {
                iView.saveFeedbackFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.feedBackSave(infos, new ProgressSubscriber(listener, context));
    }

}

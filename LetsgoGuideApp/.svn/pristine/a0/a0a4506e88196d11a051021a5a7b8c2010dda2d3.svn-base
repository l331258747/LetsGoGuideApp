package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.evaluation.Datainfo;

/**
 * Created by Administrator on 2018/11/14.
 */

public class ReviewEvalPresenter implements ReviewEvalContract.Presenter{


    ReviewEvalContract.View iView;
    Context context;

    public ReviewEvalPresenter(ReviewEvalContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getReviewEval(String guideContent, String userId,int id, int orderId) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String str) {
                iView.getReviewEvalSuccess(str);
                Log.e("test","onSuccess"+str);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getReviewEvalFailed(errorMsg);
                Log.e("test","onFault");
            }
        };
        MethodApi.guideReview(guideContent,  userId, id, orderId, new ProgressSubscriber(listener, context));
    }
}

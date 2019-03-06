package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.mine.BinkIntoInfo;
import com.njz.letsgoappguides.model.mine.GetBinkInfo;
import com.njz.letsgoappguides.model.mine.OperationGuideModel;

/**
 * Created by Administrator on 2018/11/26.
 */

public class OperationGuidePresenter implements OperationGuideContract.Presenter{

    OperationGuideContract.View iView;
    Context context;

    public OperationGuidePresenter(OperationGuideContract.View view, Context context) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getOperationGuide() {
        ResponseCallback listener = new ResponseCallback<OperationGuideModel>() {
            @Override
            public void onSuccess(OperationGuideModel data ) {
                iView.getOperationGuideSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOperationGuideFailed(errorMsg);
            }
        };
        MethodApi.getOperationGuide( new ProgressSubscriber(listener, context));
    }


}

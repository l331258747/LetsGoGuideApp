package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.mine.LabelModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public class LabelPresenter implements LabelContract.Presenter {
    Context context;
    LabelContract.View iView;

    public LabelPresenter(Context context, LabelContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void userGetSign() {
        ResponseCallback listener = new ResponseCallback<List<LabelModel>>() {
            @Override
            public void onSuccess(List<LabelModel> datainfo) {
                iView.userGetSignSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.userGetSignFailed(errorMsg);
            }
        };
        MethodApi.userGetSign(new ProgressSubscriber(listener, context,false));
    }
}

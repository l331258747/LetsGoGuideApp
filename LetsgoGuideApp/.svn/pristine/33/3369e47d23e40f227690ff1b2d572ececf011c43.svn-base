package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.mine.LabelModel;
import com.njz.letsgoappguides.model.mine.MyInfoData;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public class ModifyInfoPresenter implements ModifyInfoContract.Presenter {

    Context context;
    ModifyInfoContract.View iView;

    public ModifyInfoPresenter(Context context, ModifyInfoContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void userUpdate(MyInfoData data) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String datainfo) {
                iView.userUpdateSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.userUpdateFailed(errorMsg);
            }
        };
        MethodApi.userUpdate(data,new ProgressSubscriber(listener, context,false));
    }
}

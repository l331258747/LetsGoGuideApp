package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.login.Datas;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.model.login.UserVo;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public class GetLUserinfoPresenter implements GetUserinfoContract.Presenter {

    Context context;
    GetUserinfoContract.View iView;

    public GetLUserinfoPresenter(Context context, GetUserinfoContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getUserinfo(boolean isShowDialog) {
        ResponseCallback listener = new ResponseCallback<UserVo>() {
            @Override
            public void onSuccess(UserVo str) {
                iView.getUserinfoSuccess(str);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getUserinfoFailed(errorMsg);
            }
        };
        MethodApi.getUserInfo(new ProgressSubscriber(listener, context,isShowDialog));

    }
}

package com.njz.letsgoappguides.presenter.setting;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.presenter.ForgotContract;
import com.njz.letsgoappguides.util.AESOperator;
import com.njz.letsgoappguides.util.ToastUtil;

/**
 * Created by Administrator on 2018/11/8.
 */

public class SetPhonePresenter implements SetPhoneContract.Presenter{

    SetPhoneContract.View iView;
    Context context;

    public SetPhonePresenter(SetPhoneContract.View iView, Context context) {
        this.iView = iView;
        this.context = context;
    }

    @Override
    public void SetPhone(String mobile,String captcha,String password) {
        ResponseCallback listener=new ResponseCallback<String>() {
            @Override
            public void onSuccess(String msg) {
                iView.SetPhoneSuccess(msg);
            }

            @Override
            public void onFault(String msg) {
                iView.SetPhoneFailed(msg);
            }
        };
        MethodApi.updateMobile(mobile,captcha,password,new ProgressSubscriber(listener,context));
    }

    @Override
    public void userSmsSend(String mobile, String type) {
        ResponseCallback listener=new ResponseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                iView.userSmsSendSuccess(s);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.userSmsSendFailed(errorMsg);
            }
        };
        String enString = null;
        try {
            enString = AESOperator.getInstance().encrypt(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showShortToast(context,"加密错误");
            return;
        }
        MethodApi.userSmsSend(enString, type, new ProgressSubscriber(listener, context));
    }

}

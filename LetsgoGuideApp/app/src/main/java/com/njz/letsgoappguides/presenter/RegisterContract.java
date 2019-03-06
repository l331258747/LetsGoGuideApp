package com.njz.letsgoappguides.presenter;

/**
 * Created by Administrator on 2018/11/5.
 */

public interface RegisterContract {

    interface Presenter {
        void msgCheckRegister(String mobile, String password , String passwordSure, String captcha);

        void userSmsSend(String mobile,String type);
    }

    interface View {
        void msgCheckRegisterSuccess(String str);

        void msgCheckRegisterFailed(String msg);

        void userSmsSendSuccess(String str);

        void userSmsSendFailed(String msg);
    }
}

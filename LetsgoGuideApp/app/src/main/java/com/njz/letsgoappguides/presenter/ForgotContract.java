package com.njz.letsgoappguides.presenter;

import com.njz.letsgoappguides.model.login.Datas;

/**
 * Created by Administrator on 2018/11/6.
 */

public interface ForgotContract {
    interface Presenter {
        void forgotPsd(String mobile,String captcha,String newPassword);
        void userSmsSend(String mobile,String type);
    }

    interface View {
        void forgotPsdSuccess(String msg);

        void forgotPsdFailed(String msg);

        void userSmsSendSuccess(String msg);

        void userSmsSendFailed(String msg);
    }
}

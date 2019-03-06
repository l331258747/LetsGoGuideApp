package com.njz.letsgoappguides.presenter;

import com.njz.letsgoappguides.model.login.Datas;

/**
 * Created by Administrator on 2018/11/6.
 */

public interface CodeLoginContract {

    interface Presenter {
        void msgCheckLogin(String mobile,String msg);
        void userSmsSend(String mobile,String type);
    }

    interface View {
        void msgCheckLoginSuccess(Datas str);

        void msgCheckLoginFailed(String msg);

        void userSmsSendSuccess(String str);

        void userSmsSendFailed(String msg);
    }
}

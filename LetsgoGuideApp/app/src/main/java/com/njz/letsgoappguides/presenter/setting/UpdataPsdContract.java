package com.njz.letsgoappguides.presenter.setting;

import com.njz.letsgoappguides.model.login.Datas;

/**
 * Created by Administrator on 2018/11/8.
 */

public interface UpdataPsdContract {
    interface Presenter {
        void updataPsd(String password,String newpassword );
    }

    interface View {
        void updataPsdSuccess(String str);
        void updataPsdFailed(String msg);
    }
}

package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.authentication.AuthenInfo;

/**
 * Created by Administrator on 2018/11/16.
 */

public interface QueryAuthenContract {

    interface Presenter {
        void queryAuthen();
    }

    interface View {
        void queryAuthenSuccess(AuthenInfo infos);
        void queryAuthenFailed(String msg);
    }
}

package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.GuideValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;

/**
 * Created by Administrator on 2018/11/16.
 */

public interface GuideAuthenContract {

    interface Presenter {
        void toAuthInfo(GuideValidInfo data);
    }

    interface View {
        void toAuthInfoSuccess(UserVo str);
        void toAuthInfoFailed(String msg);
    }
}

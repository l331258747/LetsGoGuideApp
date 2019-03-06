package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.authentication.AuthenInfo;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;

/**
 * Created by Administrator on 2018/11/16.
 */

public interface CarAuthenContract {

    interface Presenter {
        void toAuthInfo(DriveValidInfo validInfo);
    }

    interface View {
        void toAuthInfoSuccess(UserVo data);
        void toAuthInfoFailed(String msg);
    }
}

package com.njz.letsgoappguides.presenter.server;

import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.GetUpdateServiceInfo;


public interface GetUpdateServInfoContract {

    interface Presenter {
        void getUpdateServInfo(int njzGuideServeId);
        void updaServiceInfo(AutoServiceModel infos);

    }

    interface View {
        void getUpdateServInfoSuccess(GetUpdateServiceInfo infos);
        void getUpdateServInfoFailed(String msg);

        void updaServiceInfoSuccess(String infos);
        void updaServiceInfoFailed(String msg);
    }

}

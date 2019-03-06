package com.njz.letsgoappguides.presenter.server;

import com.njz.letsgoappguides.model.home.HomeModel;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.ServerTypeModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public interface ServerTypeContract {

    interface Presenter {
        void serveGetServe();
        void getServeDicList(boolean isShow);
    }

    interface View {
        void serveGetServeSuccess(List<ServerTypeModel> datas);
        void serveGetServeFailed(String msg);

        void getServeDicListSuccess(GetServeDicListModel datas);
        void getServeDicListFailed(String msg);
    }
}

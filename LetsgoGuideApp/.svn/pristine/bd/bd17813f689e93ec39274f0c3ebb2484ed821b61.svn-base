package com.njz.letsgoappguides.presenter.server;

import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.ServerListModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public interface ServerListContract {

    interface Presenter {
        void serveList(int page,int limit,String address,String serveType,String status,String key);
    }

    interface View {
        void serveListSuccess(List<ServerListModel> datas);
        void serveListFailed(String msg);
    }
}

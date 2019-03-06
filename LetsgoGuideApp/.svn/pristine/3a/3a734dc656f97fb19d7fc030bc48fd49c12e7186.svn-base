package com.njz.letsgoappguides.presenter.server;

import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public interface GetCityContract {

    interface Presenter {
        void cityGetCity();
        void getServiceCityList();
    }

    interface View {
        void cityGetCitySuccess(List<CityModel> datas);
        void cityGetCityFailed(String msg);

        void getServiceCityListSuccess(List<GetServiceCityModel> datas);
        void getServiceCityListFailed(String msg);
    }

}

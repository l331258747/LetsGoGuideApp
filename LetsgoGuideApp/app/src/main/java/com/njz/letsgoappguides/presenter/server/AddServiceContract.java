package com.njz.letsgoappguides.presenter.server;

import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.CityModel;

import java.util.List;


public interface AddServiceContract {

    interface Presenter {
        void addService(AutoServiceModel data);
    }

    interface View {
        void addServiceSuccess(String str);
        void addServiceFailed(String msg);
    }

}

package com.njz.letsgoappguides.presenter.server;

import com.njz.letsgoappguides.model.server.ServiceCalPriceInfo;

import java.util.List;


public interface ServerPriceContract {

    interface Presenter {
        void getServeCalPriceInfo(int njzFormatIdId);
        void getMonthPriceInfo(int njzFormatId,String time);
    }

    interface View {
        void getServeCalPriceInfoSuccess(List<ServiceCalPriceInfo> datas);
        void getServeCalPriceInfoFailed(String msg);
        void getMonthPriceInfoSuccess(List<ServiceCalPriceInfo.NjzGuideServeFormatPriceEntityListBean> datas);
        void getMonthPriceInfoFailed(String msg);
    }
}

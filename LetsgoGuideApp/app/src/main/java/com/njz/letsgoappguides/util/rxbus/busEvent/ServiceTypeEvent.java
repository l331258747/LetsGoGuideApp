package com.njz.letsgoappguides.util.rxbus.busEvent;

import com.njz.letsgoappguides.model.server.NjzGuideServeDicVoListBean;

/**
 * Created by LGQ
 * Time: 2018/12/25
 * Function:
 */

public class ServiceTypeEvent {

    NjzGuideServeDicVoListBean model;

    public ServiceTypeEvent(NjzGuideServeDicVoListBean model) {
        this.model = model;
    }

    public NjzGuideServeDicVoListBean getModel() {
        return model;
    }

}

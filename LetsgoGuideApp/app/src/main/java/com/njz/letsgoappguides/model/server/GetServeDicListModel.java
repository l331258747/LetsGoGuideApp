package com.njz.letsgoappguides.model.server;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */

public class GetServeDicListModel {


    private List<NjzGuideServeDicVoListBean> njzGuideServeDicVoList;

    public List<NjzGuideServeDicVoListBean> getNjzGuideServeDicVoList() {
        return njzGuideServeDicVoList;
    }

    public void setNjzGuideServeDicVoList(List<NjzGuideServeDicVoListBean> njzGuideServeDicVoList) {
        this.njzGuideServeDicVoList = njzGuideServeDicVoList;
    }

    @Override
    public String toString() {
        return "GetServeDicListModel{" +
                "njzGuideServeDicVoList=" + njzGuideServeDicVoList +
                '}';
    }


}

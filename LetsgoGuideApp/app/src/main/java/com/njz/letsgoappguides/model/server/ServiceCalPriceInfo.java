package com.njz.letsgoappguides.model.server;

import java.util.List;

/**
 * Created by Administrator on 2018/12/18.
 */

public class ServiceCalPriceInfo {


    /**
     * njzGuideServeFormatPriceEntityList : [{"formatId":212,"monthInt":1,"yearInt":2019,"price":369,"dateInt":16,"id":86}]
     * key : 201901
     */

    private String key;
    private List<NjzGuideServeFormatPriceEntityListBean> njzGuideServeFormatPriceEntityList;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<NjzGuideServeFormatPriceEntityListBean> getNjzGuideServeFormatPriceEntityList() {
        return njzGuideServeFormatPriceEntityList;
    }

    public void setNjzGuideServeFormatPriceEntityList(List<NjzGuideServeFormatPriceEntityListBean> njzGuideServeFormatPriceEntityList) {
        this.njzGuideServeFormatPriceEntityList = njzGuideServeFormatPriceEntityList;
    }



    @Override
    public String toString() {
        return "ServiceCalPriceInfo{" +
                "key='" + key + '\'' +
                ", njzGuideServeFormatPriceEntityList=" + njzGuideServeFormatPriceEntityList +
                '}';
    }
}

package com.njz.letsgoappguides.model.server;

import java.util.List;

public class NjzGuideServeDicVoListBean {
    /**
     * id : 1
     * uniqueValue : xdpy
     * name : 向导陪游
     * formatUnit : 天
     * isShow : true
     * njzGuideServeFormatDicVos : [{"id":6,"uniqueValue":"xdpy_cx","formatName":"车型","pointServeType":"xdpy","formatUnit":"1","power":7,"order":0,"canSelectMaxNum":1,"canSelectMinNum":0,"isRequisite":false,"isPower":false},{"id":7,"uniqueValue":"xdpy_yy","formatName":"语言","pointServeType":"xdpy","formatUnit":"1","power":3,"order":0,"canSelectMaxNum":1,"canSelectMinNum":1,"isRequisite":true,"isPower":true}]
     */

    private int id;
    private String uniqueValue;
    private String name;
    private String formatUnit;
    private boolean isShow;
    private List<NjzGuideServeFormatDicVosBean> njzGuideServeFormatDicVos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatUnit() {
        return formatUnit;
    }

    public void setFormatUnit(String formatUnit) {
        this.formatUnit = formatUnit;
    }

    public boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public List<NjzGuideServeFormatDicVosBean> getNjzGuideServeFormatDicVos() {
        return njzGuideServeFormatDicVos;
    }

    public void setNjzGuideServeFormatDicVos(List<NjzGuideServeFormatDicVosBean> njzGuideServeFormatDicVos) {
        this.njzGuideServeFormatDicVos = njzGuideServeFormatDicVos;
    }

    @Override
    public String toString() {
        return "NjzGuideServeDicVoListBean{" +
                "id=" + id +
                ", uniqueValue='" + uniqueValue + '\'' +
                ", name='" + name + '\'' +
                ", formatUnit='" + formatUnit + '\'' +
                ", isShow=" + isShow +
                ", njzGuideServeFormatDicVos=" + njzGuideServeFormatDicVos +
                '}';
    }


}
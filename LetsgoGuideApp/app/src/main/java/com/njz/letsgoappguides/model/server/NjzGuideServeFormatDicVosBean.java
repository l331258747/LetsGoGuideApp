package com.njz.letsgoappguides.model.server;

public class NjzGuideServeFormatDicVosBean {
    /**
     * id : 6
     * uniqueValue : xdpy_cx
     * formatName : 车型
     * pointServeType : xdpy
     * formatUnit : 1
     * power : 7
     * order : 0
     * canSelectMaxNum : 1
     * canSelectMinNum : 0
     * isRequisite : false
     * isPower : false
     */

    private int id;
    private String uniqueValue;
    private String formatName;
    private String pointServeType;
    private String formatUnit;
    private int power;
    private int order;
    private int canSelectMaxNum;
    private int canSelectMinNum;
    private boolean isRequisite;
    private boolean isPower;

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

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getPointServeType() {
        return pointServeType;
    }

    public void setPointServeType(String pointServeType) {
        this.pointServeType = pointServeType;
    }

    public String getFormatUnit() {
        return formatUnit;
    }

    public void setFormatUnit(String formatUnit) {
        this.formatUnit = formatUnit;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCanSelectMaxNum() {
        return canSelectMaxNum;
    }

    public void setCanSelectMaxNum(int canSelectMaxNum) {
        this.canSelectMaxNum = canSelectMaxNum;
    }

    public int getCanSelectMinNum() {
        return canSelectMinNum;
    }

    public void setCanSelectMinNum(int canSelectMinNum) {
        this.canSelectMinNum = canSelectMinNum;
    }

    public boolean isIsRequisite() {
        return isRequisite;
    }

    public void setIsRequisite(boolean isRequisite) {
        this.isRequisite = isRequisite;
    }

    public boolean isIsPower() {
        return isPower;
    }

    public void setIsPower(boolean isPower) {
        this.isPower = isPower;
    }

    @Override
    public String toString() {
        return "NjzGuideServeFormatDicVosBean{" +
                "id=" + id +
                ", uniqueValue='" + uniqueValue + '\'' +
                ", formatName='" + formatName + '\'' +
                ", pointServeType='" + pointServeType + '\'' +
                ", formatUnit='" + formatUnit + '\'' +
                ", power=" + power +
                ", order=" + order +
                ", canSelectMaxNum=" + canSelectMaxNum +
                ", canSelectMinNum=" + canSelectMinNum +
                ", isRequisite=" + isRequisite +
                ", isPower=" + isPower +
                '}';
    }
}
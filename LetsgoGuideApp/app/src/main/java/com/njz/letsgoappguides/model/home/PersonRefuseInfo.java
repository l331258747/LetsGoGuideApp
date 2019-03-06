package com.njz.letsgoappguides.model.home;

/**
 * Created by Administrator on 2018/12/21.
 */

public class PersonRefuseInfo {

    private int orderId;
    private int planStatus;
    private String refuseReason;
    private String refuseExplain;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseExplain() {
        return refuseExplain;
    }

    public void setRefuseExplain(String refuseExplain) {
        this.refuseExplain = refuseExplain;
    }

    @Override
    public String toString() {
        return "PersonRefuseInfo{" +
                "orderId=" + orderId +
                ", planStatus=" + planStatus +
                ", refuseReason='" + refuseReason + '\'' +
                ", refuseExplain='" + refuseExplain + '\'' +
                '}';
    }


    public PersonRefuseInfo(int orderId, int planStatus, String refuseReason, String refuseExplain) {
        this.orderId = orderId;
        this.planStatus = planStatus;
        this.refuseReason = refuseReason;
        this.refuseExplain = refuseExplain;
    }
}

package com.njz.letsgoappguides.model.settlement;

import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/11/30.
 */

public class OrderSettleModel {

    private float balanceMoney;
    private float platformMoney;
    private String orderNo;
    private String balanceDate;
    private String name;
    private String location;
    private float orderPrice;
    private int status;
    private String mobile;
    private List<OrderSettleChildModel> childOrder;

    public float getPlatformMoney() {
        return platformMoney;
    }

    public float getBalanceMoney() {
        return balanceMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderSettleChildModel> getChildOrder() {
        return childOrder;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}

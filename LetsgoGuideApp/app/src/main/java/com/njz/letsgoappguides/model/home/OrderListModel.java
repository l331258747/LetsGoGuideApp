package com.njz.letsgoappguides.model.home;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderListModel {


    /**
     * guideSureTime : null
     * beBalancedDate : 2018-11-18 00:00:00
     * payTime : null
     * guideId : 13
     * orderStatus : 0
     * njzChildOrderListVOS : []
     * payPrice : 0
     * orderPrice : 0.1
     * id : 267
     * sureTime :
     * balancePrice : 0
     * beforTravel :
     * guideMobile :
     * guideName : 付中意
     * orderNo : CS201811131552288463
     * specialRequire :
     * mobile : 15111199204
     * balanceTime : null
     * payingStatus : 0
     * cancelTime : null
     * createTime : 2018-11-13 15:52:28
     * name : 中意
     * location : 长沙
     * reviewStatus : 0
     * payStatus : 0
     */

    private String guideSureTime;
    private String beBalancedDate;
    private String payTime;
    private int guideId;
    private int orderStatus;
    private float payPrice;
    private float orderPrice;
    private int id;
    private String sureTime;
    private float balancePrice;
    private String beforTravel;
    private String guideMobile;
    private String guideName;
    private String orderNo;
    private String specialRequire;
    private String mobile;
    private String balanceTime;
    private int payingStatus;
    private String cancelTime;
    private String createTime;
    private String name;
    private String location;
    private int reviewStatus;
    private int payStatus;
    private int planStatus;
    private int refundStatus;
    private int refundId;
    private List<OrderListChildModel> njzChildOrderListVOS;

    public int getRefundStatus() {
        return refundStatus;
    }

    public int getRefundId() {
        return refundId;
    }

    public String getGuideSureTime() {
        return guideSureTime;
    }

    public String getBeBalancedDate() {
        return beBalancedDate;
    }

    public String getPayTime() {
        return payTime;
    }

    public int getGuideId() {
        return guideId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public float getPayPrice() {
        return payPrice;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public int getId() {
        return id;
    }

    public String getSureTime() {
        return sureTime;
    }

    public float getBalancePrice() {
        return balancePrice;
    }

    public String getBeforTravel() {
        return beforTravel;
    }

    public String getGuideMobile() {
        return guideMobile;
    }

    public String getGuideName() {
        return guideName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getSpecialRequire() {
        return specialRequire;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBalanceTime() {
        return balanceTime;
    }

    public int getPayingStatus() {
        return payingStatus;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public List<OrderListChildModel> getNjzChildOrderListVOS() {
        return njzChildOrderListVOS;
    }

}

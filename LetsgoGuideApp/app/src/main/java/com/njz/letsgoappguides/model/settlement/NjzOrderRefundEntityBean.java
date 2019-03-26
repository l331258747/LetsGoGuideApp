package com.njz.letsgoappguides.model.settlement;

import android.text.TextUtils;

import com.njz.letsgoappguides.util.StringUtils;

import java.util.List;

public class NjzOrderRefundEntityBean {

    private float defaultMoney;
    private String guideSureTime;
    private int orderId;
    private String payTime;
    private int refundStatus;
    private String refundContent;
    private int mainly;
    private String refundType;
    private String payType;
    private int isDefaultMoney;
    private int childOrderId;
    private int useMoney;
    private float orderPrice;
    private int id;
    private String applyTime;
    private String sureTime;
    private String guideName;
    private String orderNo;
    private String specialRequire;
    private int coupon;
    private int balanceStatus;
    private String refundReason;
    private String refundTime;
    private String guideCheckTime;
    private String mobile;
    private float refundMoney;
    private String balanceTime;
    private String checkTime;
    private String createTime;
    private String name;
    private String location;
    private int personNum;
    private int adult;
    private int children;
    private List<NjzRefundDetailsChildVOSBean> njzRefundDetailsChildVOS;

    public float getRefundMoney() {
        return refundMoney;
    }

    private String orderNote;

    public String getOrderNote() {
        if (orderNote == null) {
            return "";
        }
        return orderNote;
    }

    public int getPersonNum() {
        return personNum;
    }

    public String getPersonNumStr1() {
        return personNum + "人";
    }

    public String getPersonNumStr2() {
        return adult + "成人" + children + "儿童";
    }

    public float getDefaultMoney() {
        return defaultMoney;
    }

    public void setDefaultMoney(float defaultMoney) {
        this.defaultMoney = defaultMoney;
    }

    public String getGuideSureTime() {
        if (guideSureTime == null) {
            return "无";
        }
        return guideSureTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPayTime() {
        return payTime;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundContent() {
        return refundContent;
    }

    public String getPayType() {
        if (TextUtils.equals(payType, "WxPay")) {
            return "微信支付";
        } else if (TextUtils.equals(payType, "AliPay")) {
            return "支付宝支付";
        }
        return payType;
    }

    public int getUseMoney() {
        return useMoney;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public String getSureTime() {
        return sureTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSpecialRequire() {
        return specialRequire;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public String getGuideCheckTime() {
        if (guideCheckTime == null) {
            return "";
        }
        return guideCheckTime;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMobilehide() {
        return StringUtils.midleReplaceStar(mobile);
    }

    public String getBalanceTime() {
        if (balanceTime == null) {
            return "";
        }
        return balanceTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getNameHide() {
        return StringUtils.nameReplaceStar(name);
    }

    public List<NjzRefundDetailsChildVOSBean> getNjzRefundDetailsChildVOS() {
        return njzRefundDetailsChildVOS;
    }
}
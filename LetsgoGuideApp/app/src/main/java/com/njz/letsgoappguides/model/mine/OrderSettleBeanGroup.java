package com.njz.letsgoappguides.model.mine;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderRefundChildModel;

/**
 * Created by Administrator on 2018/11/30.
 */

public class OrderSettleBeanGroup {

    public static final int LABEL_TAB_TITLE = 1;
    public static final int LABEL_TAB_DEFAULT = 2;
    public static final int LABEL_TAB_FOOT = 3;

    private float balanceMoney;
    private String balanceDate;
    private int status;
    private OrderSettleChildModel childOrder;

    private int labelTab;

    private String orderNo;
    private float payPrice;
    private int orderStatus;
    private String location;
    private float orderPrice;
    private float refundMoney;
    private int reviewStatus;
    private int refundStatus;
    private int id;
    private int payStatus;
    private String name;
    private String mobile;
    private OrderSettleChildModel orderChildModel;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public float getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(float refundMoney) {
        this.refundMoney = refundMoney;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getLabelTab() {
        return labelTab;
    }

    public void setLabelTab(int labelTab) {
        this.labelTab = labelTab;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public String getPayStatusStr(){
        switch (payStatus){
            case Constant.ORDER_PAY_WAIT:
                return "待付款";
            case Constant.ORDER_PAY_ALREADY:
                switch (orderStatus){
                    case Constant.ORDER_TRAVEL_WAIT:
                        return "待确认";
                    case Constant.ORDER_TRAVEL_NO_GO:
                        return "未出行";
                    case Constant.ORDER_TRAVEL_GOING:
                        return "行程中";
                    case Constant.ORDER_TRAVEL_FINISH:
                        return "行程结束";
                    case Constant.ORDER_TRAVEL_REFUSE:
                        return "导游拒绝";
                    default:
                        return "";
                }
            case Constant.ORDER_PAY_FINISH:
                switch (reviewStatus){
                    case Constant.ORDER_EVALUATE_NO:
                        return "待点评";
                    case Constant.ORDER_EVALUATE_YES:
                        return "已点评";
                    default:
                        return "";
                }
            case Constant.ORDER_PAY_REFUND:
                switch (refundStatus){
                    case Constant.ORDER_REFUND_WAIT:
                        return "退款待确认";
                    case Constant.ORDER_REFUND_PROCESS:
                        return "退款中";
                    case Constant.ORDER_REFUND_FINISH:
                        return "已退款";
                }
                return "退款";
        }
        return "";
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderSettleChildModel getOrderChildModel() {
        return orderChildModel;
    }

    public void setOrderChildModel(OrderSettleChildModel orderChildModel) {
        this.orderChildModel = orderChildModel;
    }


    public float getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(float balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public String getBalanceDate() {
        if(TextUtils.isEmpty(balanceDate)){
            return "无";
        }

        return balanceDate.split(" ")[0];
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public int getStatus() {
        return status;
    }

    public String getsettleStatus(){
        switch (getStatus()){
            case 1:
                return "已退款";
            case 2:
                return "已完成";
        }
        return "";
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderSettleChildModel getChildOrder() {
        return childOrder;
    }

    public void setChildOrder(OrderSettleChildModel childOrder) {
        this.childOrder = childOrder;
    }
}

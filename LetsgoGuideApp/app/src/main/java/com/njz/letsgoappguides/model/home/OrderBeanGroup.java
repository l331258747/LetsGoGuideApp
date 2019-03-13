package com.njz.letsgoappguides.model.home;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.model.home.OrderListChildModel;

/**
 * Created by LGQ
 * Time: 2018/8/13
 * Function:
 */

public class OrderBeanGroup {

    public static final int LABEL_TAB_TITLE = 1;
    public static final int LABEL_TAB_DEFAULT = 2;
    public static final int LABEL_TAB_FOOT = 3;

    private int labelTab;

    private String orderNo;
    private float payPrice;
    private int orderStatus;
    private String location;
    private float orderPrice;
    private int reviewStatus;
    private int id;
    private int payStatus;
    private String name;
    private String userName;
    private String userMobile;
    private String mobile;
    private int payingStatus;
    private int index;
    private int planStatus;
    private int refundStatus;
    private int refundId;
    private String sureTime;
    private OrderListChildModel orderChildModel;


    public String getSureTime() {
        return sureTime;
    }

    public void setSureTime(String sureTime) {
        this.sureTime = sureTime;
    }

    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public int getPayingStatus() {
        return payingStatus;
    }

    public void setPayingStatus(int payingStatus) {
        this.payingStatus = payingStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public float getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(float payPrice) {
        this.payPrice = payPrice;
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

    public String getOrderPriceStr(){
        if (payStatus == Constant.ORDER_PAY_WAIT &&
                planStatus == Constant.ORDER_PLAN_GUIDE_WAIT || planStatus == Constant.ORDER_PLAN_PLANING) {
            return ("报价待确定");
        } else {
            return ("￥" + orderPrice);//orderPrice通过setOrderPrice传入可能为orderprice可能为payprice
        }
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

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public String getPayStatusStr(){
        switch (payStatus){
            case Constant.ORDER_PAY_WAIT:
                switch (payingStatus){
                    case Constant.ORDER_WAIT_PAY:
                        switch (planStatus){
                            case Constant.ORDER_PLAN_GUIDE_WAIT:
                                return "待确认";
                            case Constant.ORDER_PLAN_PLANING:
                                return "方案设计中";
                        }
                        return "待付款";
                    case Constant.ORDER_WAIT_PAYING:
                        return "付款中";
                    default:
                        return "";
                }
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
                switch (refundStatus) {
                    case Constant.ORDER_REFUND_WAIT:
                        return "退款待确认";
                    case Constant.ORDER_REFUND_PROCESS:
                        return "退款中";
                    case Constant.ORDER_REFUND_FINISH:
                        return "已退款";
                    case Constant.ORDER_REFUND_CANCEL:
                    case Constant.ORDER_REFUND_PLAN_REFUSE:
                        return "已取消";
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

    public OrderListChildModel getOrderChildModel() {
        return orderChildModel;
    }

    public void setOrderChildModel(OrderListChildModel orderChildModel) {
        this.orderChildModel = orderChildModel;
    }

    public boolean isCustom(){
        if (orderChildModel.getValue().equals(Constant.SERVICE_TYPE_SHORT_CUSTOM)) {//私人订制
            return true;
        }
        return false;
    }


}

package com.njz.letsgoappguides.model.home;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderDetailModel {


    /**
     * guideSureTime : null
     * lastPayTime : 2018-11-14 15:52:28
     * beBalancedDate : 2018-11-18 00:00:00
     * endDate : 2018-11-16 00:00:00
     * payTime : null
     * guideId : 13
     * orderStatus : 0
     * payType :
     * njzChildOrderVOS : [{"defaultMoney":0,"titleImg":"http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181113/1031101177355.jpg","childOrderStatus":0,"orderId":267,"serveType":23,"unUseDay":1,"serveId":192,"refundMoney":0,"personNum":1,"title":"私人订制11.13","roomNum":0,"travelDate":"2018-11-15","payPrice":0,"price":0.1,"ticketNum":0,"dayNum":1,"orderPrice":0.1,"id":660,"payStatus":0,"value":"srdz","useDay":0}]
     * payPrice : 0
     * orderPrice : 0.1
     * id : 267
     * sureTime :
     * balancePrice : 0
     * beforTravel :
     * guideMobile : 18216151610
     * guideName : 付中意
     * orderNo : CS201811131552288463
     * specialRequire :
     * balanceStatus : 0
     * mobile : 15111199204
     * balanceTime : null
     * payingStatus : 0
     * cancelTime : null
     * createTime : 2018-11-13 15:52:28
     * name : 中意
     * location : 长沙
     * reviewStatus : 0
     * payStatus : 0
     * startDate : 2018-11-15 00:00:00
     */

    private String guideSureTime;
    private String lastPayTime;
    private String beBalancedDate;
    private String endDate;
    private String payTime;
    private int guideId;
    private int orderStatus;
    private String payType;
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
    private int balanceStatus;
    private String mobile;
    private String balanceTime;
    private int payingStatus;
    private String cancelTime;
    private String createTime;
    private String name;
    private String location;
    private int reviewStatus;
    private int payStatus;
    private String startDate;
    private int planStatus;
    private int personNum;
    private int children;
    private int adult;
    private int userId;
    private List<OrderDetailChildModel> njzChildOrderVOS;
    private String cancelReason;
    private String cancelExplain;
    private String orderNote;
    private int refundStatus;

    public int getRefundStatus() {
        return refundStatus;
    }

    public String getOrderNote() {
        if(orderNote==null){
            return "";
        }
        return orderNote;
    }

    public String getGuideSureTime() {
        return guideSureTime;
    }

    public String getLastPayTime() {
        return lastPayTime;
    }

    public String getBeBalancedDate() {
        return beBalancedDate;
    }

    public String getEndDate() {
        return endDate;
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

    public String getPayType() {
        if(TextUtils.equals(payType,"WxPay")){
            return "微信支付";
        }else if(TextUtils.equals(payType,"AliPay")){
            return "支付宝支付";
        }
        return payType;
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

    public int getBalanceStatus() {
        return balanceStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMobilehide() {
        return StringUtils.midleReplaceStar(mobile);
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
    public String getNameHide() {
        return StringUtils.nameReplaceStar(name);
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

    public String getStartDate() {
        return startDate;
    }

    public List<OrderDetailChildModel> getNjzChildOrderVOS() {
        return njzChildOrderVOS;
    }

    public String getPayStatusStr() {
        switch (payStatus) {
            case Constant.ORDER_PAY_WAIT:
                switch (payingStatus) {
                    case Constant.ORDER_WAIT_PAY:
                        switch (planStatus) {
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
                switch (orderStatus) {
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
                return "退款";
            case Constant.ORDER_PAY_CANCEL:
                return "已取消";
        }
        return "";
    }

    public String getBalanceStatusstr(){
        switch (balanceStatus) {
            case Constant.ORDER_NOSEELT_WAIT://待结算
                return "";
            case Constant.ORDER_SEELT_WAIT://已结算
        }
        return "";
    }

    public int getPersonNum() {
        return personNum;
    }

    public String getPersonNumStr1() {
        return personNum+"人";
    }

    public String getPersonNumStr2(){
        return adult+"成人"+children+"儿童";
    }


    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public int getChildren() {
        return children;
    }

    public int getAdult() {
        return adult;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelExplain() {
        return cancelExplain;
    }

    public void setCancelExplain(String cancelExplain) {
        this.cancelExplain = cancelExplain;
    }
}

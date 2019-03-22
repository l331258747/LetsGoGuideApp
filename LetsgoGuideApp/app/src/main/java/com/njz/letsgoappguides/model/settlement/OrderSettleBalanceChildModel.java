package com.njz.letsgoappguides.model.settlement;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */

public class OrderSettleBalanceChildModel {

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
    private int personNum;
    private int adult;
    private int children;
    private List<NjzChildOrderVOSBean> njzChildOrderVOS;
    private String orderNote;

    public String getOrderNote() {
        if(orderNote==null){
            return "";
        }
        return orderNote;
    }

    public boolean isCustom() {
        if(njzChildOrderVOS !=null && njzChildOrderVOS.size()==1 && njzChildOrderVOS.get(0).getServeType() == Constant.SERVER_TYPE_CUSTOM_ID){
            return true;
        }
        return false;
    }

    public String getPersonNum() {
        if(isCustom() && (adult>0 || children > 0)){
            return adult+"成人"+children+"儿童";
        }else if(isCustom() && !TextUtils.isEmpty(njzChildOrderVOS.get(0).getAdultChildren())){
            return njzChildOrderVOS.get(0).getAdultChildren();
        }
        return personNum + "";
    }

    public String getBeBalancedDate() {
        if(TextUtils.isEmpty(beBalancedDate)){
            return "无";
        }
        return beBalancedDate.split(" ")[0];
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

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayType() {
        if(TextUtils.equals(payType,"WxPay")){
            return "微信支付";
        }else if(TextUtils.equals(payType,"AliPay")){
            return "支付宝支付";
        }
        return payType;
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

    public String getSureTime() {
        return sureTime;
    }

    public float getBalancePrice() {
        return balancePrice;
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

    public String getMobile() {
        return mobile;
    }

    public String getMobilehide() {
        return StringUtils.midleReplaceStar(mobile);
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public List<NjzChildOrderVOSBean> getNjzChildOrderVOS() {
        return njzChildOrderVOS;
    }

}

package com.njz.letsgoappguides.model.home;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.glide.GlideUtil;

import java.util.ArrayList;

/**
 * Created by LGQ
 * Time: 2018/9/29
 * Function:
 */

public class OrderRefundDetailChildModel {

    /**
     * defaultMoney : 0
     * titleImg : 11111111111
     * childOrderStatus : 0
     * orderId : 100
     * serveType : 26
     * unUseDay : 0
     * refundMoney : 0
     * personNum : 0
     * title : 张家界峡谷大酒店
     * roomNum : 2
     * travelDate : 2018-10-21,2018-10-22
     * payPrice : 0
     * price : 128.0
     * ticketNum : 0
     * dayNum : 2
     * useMoney : 0
     * orderPrice : 512.0
     * id : 186
     * payStatus : 1
     * value : ddjd
     * useDay : 0
     */

    private float defaultMoney;
    private String titleImg;
    private int childOrderStatus;
    private int orderId;
    private int serveType;
    private int unUseDay;
    private float refundMoney;
    private int personNum;
    private String title;
    private int roomNum;
    private String travelDate;
    private float payPrice;
    private float price;
    private int ticketNum;
    private int dayNum;
    private float useMoney;
    private float orderPrice;
    private int id;
    private int payStatus;
    private String value;
    private int useDay;
    private int serveId;
    private int guideOrPlatform;
    private boolean isCheck;
    private float bugGet;
    private int childRefundId;
    private int serveNum;
    private String location;
    private int isDefaultMoney;
    private int refundStatus;
    private int planStatus;
    private int adultNum;
    private int childrenNum;

    public String getAdultChildren() {
        if (adultNum > 0 || childrenNum > 0) {
            return adultNum + "成人" + childrenNum + "儿童";
        }
        return "";
    }

    public boolean isPlatformCancel() {
        if (isDefaultMoney == 1 && guideOrPlatform == 0) {
            return true;
        }
        return false;
    }

    public String getLocation() {
        return location;
    }

    public String getTimeTitle() {
        switch (serveType) {
            case Constant.SERVER_TYPE_GUIDE_ID:
                return "行程时间";
            case Constant.SERVER_TYPE_HOTEL_ID:
                return "入住时间";
            case Constant.SERVER_TYPE_TICKET_ID:
                return "日期";
            case Constant.SERVER_TYPE_CAR_ID:
                return "出发日期";
            case Constant.SERVER_TYPE_FEATURE_ID:
                return "出发日期";
            case Constant.SERVER_TYPE_CUSTOM_ID:
                return "行程时间";
        }
        return "";
    }

    public String getCountContent() {
        switch (serveType) {
            case Constant.SERVER_TYPE_HOTEL_ID:
                return serveNum + "间";
            case Constant.SERVER_TYPE_TICKET_ID:
                return serveNum + "张";
            case Constant.SERVER_TYPE_CAR_ID:
                return serveNum + "次";
            case Constant.SERVER_TYPE_FEATURE_ID:
                return serveNum + "次";
        }
        return "";
    }

    public int getChildRefundId() {
        return childRefundId;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getGuideOrPlatform() {
        return guideOrPlatform;
    }

    public int getServeId() {
        return serveId;
    }

    public float getDefaultMoney() {
        return defaultMoney;
    }

    public String getTitleImg() {
        if (!TextUtils.isEmpty(titleImg)) {
            ArrayList<String> list = StringUtils.stringToList(titleImg);
            titleImg = list.get(0);
        }
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public int getChildOrderStatus() {
        return childOrderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getServeType() {
        return serveType;
    }

    public int getUnUseDay() {
        return unUseDay;
    }

    public float getRefundMoney() {
        return refundMoney;
    }

    public String getTitle() {
        return title;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public float getUseMoney() {
        return useMoney;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public String getOrderPriceStr(){
        if((planStatus == Constant.ORDER_PLAN_GUIDE_WAIT || planStatus == Constant.ORDER_PLAN_PLANING) && orderPrice == 0){
            return ("报价待确定");
        }
        return "￥" + orderPrice;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        if (value == null) {
            return "";
        }
        return value;
    }


    public String getValuestr() {
        switch (getValue()) {
            case Constant.SERVICE_TYPE_SHORT_GUIDE:
                return "向导陪游";
            case Constant.SERVICE_TYPE_SHORT_CUSTOM:
                return "私人定制";
            case Constant.SERVICE_TYPE_SHORT_JSJZ:
                return "包车接送";
            case Constant.SERVICE_TYPE_SHORT_TSTY:
                return "特色体验";
            case Constant.SERVICE_TYPE_SHORT_DDJD:
                return "代订酒店";
            case Constant.SERVICE_TYPE_SHORT_DDMP:
                return "代订门票";


        }
        return getValue();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUseDay() {
        return useDay;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public int getRefundStatus() {
        return refundStatus;
    }
}

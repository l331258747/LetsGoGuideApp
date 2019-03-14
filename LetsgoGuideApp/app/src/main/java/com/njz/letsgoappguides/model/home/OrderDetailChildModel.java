package com.njz.letsgoappguides.model.home;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.ArrayList;

/**
 * Created by LGQ
 * Time: 2018/11/13
 * Function:
 */

public class OrderDetailChildModel {


    /**
     * defaultMoney : 0
     * titleImg : http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181113/1031101177355.jpg
     * childOrderStatus : 0
     * orderId : 267
     * serveType : 23
     * unUseDay : 1
     * serveId : 192
     * refundMoney : 0
     * personNum : 1
     * title : 私人订制11.13
     * roomNum : 0
     * travelDate : 2018-11-15
     * payPrice : 0
     * price : 0.1
     * ticketNum : 0
     * dayNum : 1
     * orderPrice : 0.1
     * id : 660
     * payStatus : 0
     * value : srdz
     * useDay : 0
     */

    private float defaultMoney;
    private String titleImg;
    private int childOrderStatus;
    private int orderId;
    private int serveType;
    private int unUseDay;
    private int serveId;
    private float refundMoney;
    private int personNum;
    private String title;
    private int roomNum;
    private String travelDate;
    private float payPrice;
    private float price;
    private int ticketNum;
    private int dayNum;
    private float orderPrice;
    private int id;
    private int payStatus;
    private String value;
    private int planStatus;
    private int useDay;
    private int bugGet;
    private String location;
    private int serveNum;
    private int payingStatus;

    public void setPayingStatus(int payingStatus) {
        this.payingStatus = payingStatus;
    }

    public int getServeNum() {
        return serveNum;
    }

    public String getOrderPriceStr(){
        if (serveType == Constant.SERVER_TYPE_CUSTOM_ID
                && (planStatus == Constant.ORDER_PLAN_GUIDE_WAIT || planStatus == Constant.ORDER_PLAN_PLANING)) {
            return ("报价待确定");
        } else {
            return ("￥" + orderPrice);
        }
    }

    public String getTimeTitle(){
        switch (serveType){
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

    public String getCountContent(){
        switch (serveType){
            case Constant.SERVER_TYPE_CUSTOM_ID:
                return serveNum + "";
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

    public String getLocation() {
        return location;
    }

    public float getDefaultMoney() {
        return defaultMoney;
    }

    public String getTitleImg() {
        if(!TextUtils.isEmpty(titleImg)){
            ArrayList<String> list= StringUtils.stringToList(titleImg);
            titleImg=list.get(0);
        }
        return titleImg;
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

    public int getServeId() {
        return serveId;
    }

    public float getRefundMoney() {
        return refundMoney;
    }

    public int getPersonNum() {
        return personNum;
    }

    public String getTitle() {
        return title;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public int getDayNum() {
        return dayNum;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public int getId() {
        return id;
    }

    public int getPayStatus() {
        return payStatus;
    }
    public String getValue() {
        if(value==null)
        {
            return "";
        }
        return value;
    }

    public String getValuestr() {
        switch (value){
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
        return value;
    }

    public int getUseDay() {
        return useDay;
    }

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public int getBugGet() {
        return bugGet;
    }
}

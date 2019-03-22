package com.njz.letsgoappguides.model.settlement;

import com.njz.letsgoappguides.constant.Constant;

public class NjzRefundDetailsChildVOSBean {

    private float defaultMoney;
    private String titleImg;
    private int childOrderStatus;
    private int orderId;
    private int serveType;
    private int unUseDay;
    private int serveId;
    private float refundMoney;
    private int personNum;
    private int childRefundId;
    private String title;
    private int guideOrPlatform;
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
    private String location;
    private float bugGet;
    private int serveNum;

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

    public float getBugGet() {
        return bugGet;
    }

    public String getLocation() {
        return location;
    }

    public float getDefaultMoney() {
        return defaultMoney;
    }

    public void setDefaultMoney(float defaultMoney) {
        this.defaultMoney = defaultMoney;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getServeType() {
        return serveType;
    }

    public void setServeType(int serveType) {
        this.serveType = serveType;
    }

    public int getServeId() {
        return serveId;
    }

    public void setServeId(int serveId) {
        this.serveId = serveId;
    }

    public float getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(float refundMoney) {
        this.refundMoney = refundMoney;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGuideOrPlatform() {
        return guideOrPlatform;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public float getUseMoney() {
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

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getValue() {
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
        return "";
    }

}
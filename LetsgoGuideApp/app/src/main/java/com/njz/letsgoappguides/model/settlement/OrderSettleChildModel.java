package com.njz.letsgoappguides.model.settlement;

import com.njz.letsgoappguides.constant.Constant;

/**
 * Created by Administrator on 2018/11/30.
 */

public class OrderSettleChildModel {

    private float defaultMoney;
    private String titleImg;
    private int orderId;
    private int serveType;
    private int serveId;
    private String title;
    private int isDefaultMoney;
    private int roomNum;
    private float payPrice;
    private float price;
    private int ticketNum;
    private float orderPrice;
    private int id;
    private String value;
    private String platFormCancelReason;
    private int childOrderStatus;
    private int unUseDay;
    private String platTime;
    private int refundMoney;
    private int personNum;
    private int platformId;
    private String adminName;
    private NjzChildOrderRefundEntity njzChildOrderRefundEntity;
    private String travelDate;
    private int dayNum;
    private int payStatus;
    private int useDay;


    public String getTitleImg() {
        return titleImg;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public float getPrice() {
        return price;
    }

    public String getValuestr() {
        switch (getValue()){
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

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(int refundMoney) {
        this.refundMoney = refundMoney;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public NjzChildOrderRefundEntity getNjzChildOrderRefundEntity() {
        return njzChildOrderRefundEntity;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }


}

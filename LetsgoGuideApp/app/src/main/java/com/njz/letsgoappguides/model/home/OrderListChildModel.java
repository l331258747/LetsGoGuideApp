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

public class OrderListChildModel {

    private String titleImg;
    private int roomNum;
    private float payPrice;
    private float price;
    private int ticketNum;
    private int dayNum;
    private float orderPrice;
    private int id;
    private int personNum;
    private String title;
    private int serveType;
    private String value;
    private int payStatus;
    private int childOrderStatus;
    private int payingStatus;
    private int planStatus;


    public int getPayingStatus() {
        return payingStatus;
    }

    public void setPayingStatus(int payingStatus) {
        this.payingStatus = payingStatus;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public int getChildOrderStatus() {
        return childOrderStatus;
    }

    public void setChildOrderStatus(int childOrderStatus) {
        this.childOrderStatus = childOrderStatus;
    }

    public float getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(float payPrice) {
        this.payPrice = payPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public String getOrderPriceStr(){
        if (payStatus == Constant.ORDER_PAY_WAIT
                && payingStatus == Constant.ORDER_WAIT_PAY
                && (planStatus == Constant.ORDER_PLAN_GUIDE_WAIT || planStatus == Constant.ORDER_PLAN_PLANING)) {
            return ("报价待确定");
        } else {
            return ("￥" + orderPrice);
        }
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getServeType() {
        return serveType;
    }

    public void setServeType(int serveType) {
        this.serveType = serveType;
    }

    public String getTitleImg() {
        if(!TextUtils.isEmpty(titleImg)){
            ArrayList<String> list= StringUtils.stringToList(titleImg);
            titleImg=list.get(0);
        }
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getValue() {
        if (TextUtils.isEmpty(value)){
            return "";
        }
        return value;
    }

    public String getValuestr() {
        switch (getValue()){
            case Constant.SERVICE_TYPE_SHORT_GUIDE:
                return "向导陪游";
            case Constant.SERVICE_TYPE_SHORT_CUSTOM:
                return "私人定制";
            case Constant.SERVICE_TYPE_SHORT_JSJZ:
                return "接送机/站";
            case Constant.SERVICE_TYPE_SHORT_TSTY:
                return "特色体验";
            case Constant.SERVICE_TYPE_SHORT_DDJD:
                return "代订酒店";
            case Constant.SERVICE_TYPE_SHORT_DDMP:
                return "代订门票";
        }
        return "";
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(int planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanStatusStr(){
        switch (planStatus){
            case 0:
            case 1:
                return "报价待确定";
        }
        return "";
    }

    @Override
    public String toString() {
        return "OrderListChildModel{" +
                "titleImg='" + titleImg + '\'' +
                ", roomNum=" + roomNum +
                ", payPrice=" + payPrice +
                ", price=" + price +
                ", ticketNum=" + ticketNum +
                ", dayNum=" + dayNum +
                ", orderPrice=" + orderPrice +
                ", id=" + id +
                ", personNum=" + personNum +
                ", title='" + title + '\'' +
                ", serveType=" + serveType +
                ", value='" + value + '\'' +
                ", payStatus=" + payStatus +
                ", childOrderStatus=" + childOrderStatus +
                ", payingStatus=" + payingStatus +
                ", planStatus=" + planStatus +
                '}';
    }
}

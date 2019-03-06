package com.njz.letsgoappguides.model.home;

import android.text.TextUtils;

/**
 * Created by Administrator on 2019/1/15.
 */

public class OrderDesign2Info {


    /**
     * id : 1
     * orderId : 1
     * servePrice : 1000
     * renegePriceThree : 1,1
     * renegePriceFive : 1,1
     * offerDetail :
     * travelDesign :
     * travelDate :
     * title :
     */

    private int id;
    private int orderId;
    private float servePrice;
    private String renegePriceThree;
    private String renegePriceFive;
    private String offerDetail;
    private String travelDesign;
    private String travelDate;
    private String title;
    private String guideId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getServePrice() {
        return servePrice;
    }

    public void setServePrice(float servePrice) {
        this.servePrice = servePrice;
    }

    public String getRenegePriceThree() {
        return renegePriceThree;
    }

    public void setRenegePriceThree(String renegePriceThree) {
        this.renegePriceThree = renegePriceThree;
    }

    public String getRenegePriceFive() {
        return renegePriceFive;
    }

    public void setRenegePriceFive(String renegePriceFive) {
        this.renegePriceFive = renegePriceFive;
    }

    public String getOfferDetail() {
        return offerDetail;
    }

    public void setOfferDetail(String offerDetail) {
        this.offerDetail = offerDetail;
    }

    public String getTravelDesign() {
        return travelDesign;
    }

    public void setTravelDesign(String travelDesign) {
        this.travelDesign = travelDesign;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    @Override
    public String toString() {
        return "OrderDesign2Info{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", servePrice=" + servePrice +
                ", renegePriceThree='" + renegePriceThree + '\'' +
                ", renegePriceFive='" + renegePriceFive + '\'' +
                ", offerDetail='" + offerDetail + '\'' +
                ", travelDesign='" + travelDesign + '\'' +
                ", travelDate='" + travelDate + '\'' +
                ", title='" + title + '\'' +
                ", guideId='" + guideId + '\'' +
                '}';
    }
}

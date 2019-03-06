package com.njz.letsgoappguides.model.home;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * 私人订制方案信息
 */

public class OrderDesignInfo implements Parcelable {

    public OrderDesignInfo() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleImg);
        dest.writeFloat(servePrice);
        dest.writeString(travelDesign);
        dest.writeInt(orderId);
        dest.writeString(personNumJson);
        dest.writeInt(serveNum);
        dest.writeInt(serveType);
        dest.writeInt(guideId);
        dest.writeInt(njzGuideServeId);
        dest.writeString(title);
        dest.writeString(renegePriceThree);
        dest.writeString(offerDetail);
        dest.writeInt(doPlanStatus);
        dest.writeInt(children);
        dest.writeString(travelDate);
        dest.writeFloat(bugGet);
        dest.writeInt(id);
        dest.writeInt(adult);
        dest.writeString(value);
        dest.writeString(renegePriceFive);
    }
    protected  OrderDesignInfo(Parcel in) {
        titleImg = in.readString();
        servePrice = in.readFloat();
        travelDesign = in.readString();
        orderId = in.readInt();
        personNumJson = in.readString();
        serveNum = in.readInt();
        serveType = in.readInt();
        guideId = in.readInt();
        njzGuideServeId = in.readInt();
        title = in.readString();
        renegePriceThree = in.readString();
        offerDetail = in.readString();
        doPlanStatus = in.readInt();
        children = in.readInt();
        travelDate = in.readString();
        bugGet = in.readFloat();
        id = in.readInt();
        adult = in.readInt();
        value = in.readString();
        renegePriceFive = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderDesignInfo> CREATOR = new Creator<OrderDesignInfo>() {
        @Override
        public OrderDesignInfo createFromParcel(Parcel in) {
            return new OrderDesignInfo(in);
        }

        @Override
        public OrderDesignInfo[] newArray(int size) {
            return new OrderDesignInfo[size];
        }
    };

    /**
     * titleImg : http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181219/160747417d4a6d.jpg
     * servePrice : 0
     * travelDesign :
     * orderId : 182
     * personNumJson : {"children":0,"adult":1}
     * serveNum : 1
     * serveType : 3
     * guideId : 35
     * njzGuideServeId : 115
     * title : 凤凰g先生1人私人订制游
     * renegePriceThree : 4,7
     * offerDetail :
     * doPlanStatus : 2
     * children : 0
     * travelDate : 2018-12-22,2018-12-23,2018-12-24
     * bugGet : 20000
     * id : 66
     * adult : 1
     * value : srdz
     * renegePriceFive : 1,3
     */



    private String titleImg;
    private float servePrice;
    private String travelDesign;
    private int orderId;
    private String personNumJson;
    private int serveNum;
    private int serveType;
    private int guideId;
    private int njzGuideServeId;
    private String title;
    private String renegePriceThree;
    private String offerDetail;
    private int doPlanStatus;
    private int children;
    private String travelDate;
    private float bugGet;
    private int id;
    private int adult;
    private String value;
    private String renegePriceFive;

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public float getServePrice() {
        return servePrice;
    }

    public void setServePrice(float servePrice) {
        this.servePrice = servePrice;
    }

    public String getTravelDesign() {
        return travelDesign;
    }

    public void setTravelDesign(String travelDesign) {
        this.travelDesign = travelDesign;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPersonNumJson() {
        return personNumJson;
    }

    public void setPersonNumJson(String personNumJson) {
        this.personNumJson = personNumJson;
    }

    public int getServeNum() {
        return serveNum;
    }

    public void setServeNum(int serveNum) {
        this.serveNum = serveNum;
    }

    public int getServeType() {
        return serveType;
    }

    public void setServeType(int serveType) {
        this.serveType = serveType;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public int getNjzGuideServeId() {
        return njzGuideServeId;
    }

    public void setNjzGuideServeId(int njzGuideServeId) {
        this.njzGuideServeId = njzGuideServeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRenegePriceThree() {
        return renegePriceThree;
    }

    public void setRenegePriceThree(String renegePriceThree) {
        this.renegePriceThree = renegePriceThree;
    }

    public String getOfferDetail() {
        return offerDetail;
    }

    public void setOfferDetail(String offerDetail) {
        this.offerDetail = offerDetail;
    }

    public int getDoPlanStatus() {
        return doPlanStatus;
    }

    public void setDoPlanStatus(int doPlanStatus) {
        this.doPlanStatus = doPlanStatus;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public float getBugGet() {
        return bugGet;
    }

    public void setBugGet(int bugGet) {
        this.bugGet = bugGet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRenegePriceFive() {
        return renegePriceFive;
    }

    public void setRenegePriceFive(String renegePriceFive) {
        this.renegePriceFive = renegePriceFive;
    }

    @Override
    public String toString() {
        return "OrderDesignInfo{" +
                "titleImg='" + titleImg + '\'' +
                ", servePrice=" + servePrice +
                ", travelDesign='" + travelDesign + '\'' +
                ", orderId=" + orderId +
                ", personNumJson='" + personNumJson + '\'' +
                ", serveNum=" + serveNum +
                ", serveType=" + serveType +
                ", guideId=" + guideId +
                ", njzGuideServeId=" + njzGuideServeId +
                ", title='" + title + '\'' +
                ", renegePriceThree='" + renegePriceThree + '\'' +
                ", offerDetail='" + offerDetail + '\'' +
                ", doPlanStatus=" + doPlanStatus +
                ", children=" + children +
                ", travelDate='" + travelDate + '\'' +
                ", bugGet=" + bugGet +
                ", id=" + id +
                ", adult=" + adult +
                ", value='" + value + '\'' +
                ", renegePriceFive='" + renegePriceFive + '\'' +
                '}';
    }

    public String getTravelDate() {
       return travelDate;
    }
    public String getTravelDateText() {
        if(TextUtils.isEmpty(travelDate)){
            return "0天";
        }
        String[] dates = travelDate.split(",");
        return dates[0] + " 共" + dates.length + "天 " + dates[dates.length - 1];
    }

    public String getPersonNum(){
        return adult+"成人"+children+"儿童";
    }

}

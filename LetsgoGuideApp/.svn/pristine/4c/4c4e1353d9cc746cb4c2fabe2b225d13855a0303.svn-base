package com.njz.letsgoappguides.model.server;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Administrator on 2018/12/11.
 */

public class ServicePreviewInfo implements Parcelable{

    private int id;
    private String titleImg;
    private float servePrice;
    private String serveFeature;
    private int serveType;
    private String renegePriceThree;
    private String renegePriceFive;
    private String costExplain;
    private String title;
    private String address;
    private int dayNum;
    private int nightNum;
    private String serveTypeName;
    private int serveMinNum;
    private int serveMaxNum;
    private int addressId;
    private int saleNum;
    private int status;
    private String forceDownDate;
    private String forceDownReason;


    protected ServicePreviewInfo(Parcel in) {
        id = in.readInt();
        titleImg = in.readString();
        servePrice = in.readFloat();
        serveFeature = in.readString();
        serveType = in.readInt();
        renegePriceThree = in.readString();
        renegePriceFive = in.readString();
        costExplain = in.readString();
        title = in.readString();
        address = in.readString();
        dayNum = in.readInt();
        nightNum = in.readInt();
        serveTypeName = in.readString();
        serveMinNum = in.readInt();
        serveMaxNum = in.readInt();
        addressId = in.readInt();
        saleNum = in.readInt();
        status = in.readInt();
        forceDownDate = in.readString();
        forceDownReason = in.readString();
    }

    public static final Creator<ServicePreviewInfo> CREATOR = new Creator<ServicePreviewInfo>() {
        @Override
        public ServicePreviewInfo createFromParcel(Parcel in) {
            return new ServicePreviewInfo(in);
        }

        @Override
        public ServicePreviewInfo[] newArray(int size) {
            return new ServicePreviewInfo[size];
        }
    };

    public String getForceDownDate() {
        if(TextUtils.isEmpty(forceDownDate))
            return "";
        return forceDownDate;
    }

    public String getForceDownReason() {
        return forceDownReason;
    }

    public int getStatus() {
        return status;
    }


    public ServicePreviewInfo() {
    }



    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getServeFeature() {
        return serveFeature;
    }

    public void setServeFeature(String serveFeature) {
        this.serveFeature = serveFeature;
    }

    public int getServeType() {
        return serveType;
    }

    public void setServeType(int serveType) {
        this.serveType = serveType;
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

    public String getCostExplain() {
        return costExplain;
    }

    public void setCostExplain(String costExplain) {
        this.costExplain = costExplain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getNightNum() {
        return nightNum;
    }

    public void setNightNum(int nightNum) {
        this.nightNum = nightNum;
    }

    public String getServeTypeName() {
        return serveTypeName;
    }

    public void setServeTypeName(String serveTypeName) {
        this.serveTypeName = serveTypeName;
    }

    public int getServeMinNum() {
        return serveMinNum;
    }

    public void setServeMinNum(int serveMinNum) {
        this.serveMinNum = serveMinNum;
    }

    public int getServeMaxNum() {
        return serveMaxNum;
    }

    public void setServeMaxNum(int serveMaxNum) {
        this.serveMaxNum = serveMaxNum;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "ServicePreviewInfo{" +
                "id=" + id +
                ", titleImg='" + titleImg + '\'' +
                ", servePrice=" + servePrice +
                ", serveFeature='" + serveFeature + '\'' +
                ", serveType=" + serveType +
                ", renegePriceThree='" + renegePriceThree + '\'' +
                ", renegePriceFive='" + renegePriceFive + '\'' +
                ", costExplain='" + costExplain + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", dayNum=" + dayNum +
                ", nightNum=" + nightNum +
                ", serveTypeName='" + serveTypeName + '\'' +
                ", serveMinNum=" + serveMinNum +
                ", serveMaxNum=" + serveMaxNum +
                ", addressId=" + addressId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titleImg);
        dest.writeFloat(servePrice);
        dest.writeString(serveFeature);
        dest.writeInt(serveType);
        dest.writeString(renegePriceThree);
        dest.writeString(renegePriceFive);
        dest.writeString(costExplain);
        dest.writeString(title);
        dest.writeString(address);
        dest.writeInt(dayNum);
        dest.writeInt(nightNum);
        dest.writeString(serveTypeName);
        dest.writeInt(serveMinNum);
        dest.writeInt(serveMaxNum);
        dest.writeInt(addressId);
        dest.writeInt(saleNum);
        dest.writeInt(status);
        dest.writeString(forceDownDate);
        dest.writeString(forceDownReason);
    }
}

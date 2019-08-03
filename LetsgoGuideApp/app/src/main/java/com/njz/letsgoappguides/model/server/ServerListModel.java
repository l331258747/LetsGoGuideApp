package com.njz.letsgoappguides.model.server;

import android.text.TextUtils;

import com.njz.letsgoappguides.util.StringUtils;

import java.util.ArrayList;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerListModel {


    /**
     * id : 8
     * title : xxxx
     * status : null
     * address : 长沙
     * addressId : 1
     * serveTypeName : xxxx
     * serveType : 1
     * saleNum : null
     * servePrice : 100
     */

    private int id;
    private String title;
    private int status;
    private String address;
    private int addressId;
    private String serveTypeName;
    private int serveType;
    private int saleNum;
    private float servePrice;
    private String titleImg;
    private String cantPassReason;

    public String getStatusStr() {//1上架  0下架  -1强制下架  2审核中  3审核未通过
        switch (status){
            case -1:
                return "强制下架";
            case 0:
                return "未上架";
            case 1:
                return "已上架";
            case 2:
                return "审核中";
            case 3:
                return "审核未通过";
            default:
                return "status:" + status;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getServeTypeName() {
        return serveTypeName;
    }

    public void setServeTypeName(String serveTypeName) {
        this.serveTypeName = serveTypeName;
    }

    public int getServeType() {
        return serveType;
    }

    public void setServeType(int serveType) {
        this.serveType = serveType;
    }

    public Object getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public float getServePrice() {
        if (servePrice <= 0) servePrice = 0;
        return servePrice;
    }

    public String getServePriceStr(){
        return servePrice + "";
    }

    public void setServePrice(float servePrice) {
        this.servePrice = servePrice;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public String getTitleImgStr(){
        if (!TextUtils.isEmpty(titleImg)) {
            ArrayList<String> list = StringUtils.stringToList(titleImg);
            return  list.get(0);
        }
        return "";
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    @Override
    public String toString() {
        return "ServerListModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", addressId=" + addressId +
                ", serveTypeName='" + serveTypeName + '\'' +
                ", serveType=" + serveType +
                ", saleNum=" + saleNum +
                ", servePrice=" + servePrice +
                ", titleImg='" + titleImg + '\'' +
                '}';
    }

    public String getCantPassReason() {
        return cantPassReason;
    }
}

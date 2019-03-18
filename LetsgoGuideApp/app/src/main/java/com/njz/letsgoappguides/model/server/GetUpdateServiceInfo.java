package com.njz.letsgoappguides.model.server;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/12/7.
 */

public class GetUpdateServiceInfo {

    /**
     * njzGuideServeEntity : {"id":17,"guideId":35,"commentId":null,"titleImg":"xxxx","servePrice":100,"servePriceSeg":null,"serveMinNum":1,"serveMaxNum":1,"serveFeature":"xxxx","serveType":1,"renegePriceThree":"7,4","renegePriceFive":"3,1","title":"xxxx","status":0,"address":"长沙","addressId":1,"putDate":1544013252000,"upDate":null,"forceDownDate":null,"forceDownReason":null,"operator":null,"name":"大苏打","serveTypeName":"xxxx","isEnabled":0,"mustPlay":0,"playTime":null,"costExplain":"xxxxx"}
     * njzGuideServeFormatEntityGroupList : [[{"id":70,"njzGuideServeId":17,"njzGuideServeFormatDic":"xdpy_yy","guideServeFormatName":"向导陪游语言类型0","servePriceSelect":"","serveDefaultPrice":100,"formatUnit":null},{"id":71,"njzGuideServeId":17,"njzGuideServeFormatDic":"xdpy_yy","guideServeFormatName":"向导陪游语言类型1","servePriceSelect":"","serveDefaultPrice":100,"formatUnit":null},{"id":72,"njzGuideServeId":17,"njzGuideServeFormatDic":"xdpy_yy","guideServeFormatName":"向导陪游语言类型2","servePriceSelect":"","serveDefaultPrice":100,"formatUnit":null}],[]]
     */

    private int saleNum;
    private ServeUpdataBean njzGuideServeEntity;
    private List<List<ServeGroupListInfo>> njzGuideServeFormatEntityGroupList;
    private List<NjzGuideServeNoTimeEntities> njzGuideServeNoTimeEntities;


    public List<NjzGuideServeNoTimeEntities> getNjzGuideServeNoTimeEntities() {
        return njzGuideServeNoTimeEntities;
    }

    public void setNjzGuideServeNoTimeEntities(List<NjzGuideServeNoTimeEntities> njzGuideServeNoTimeEntities) {
        this.njzGuideServeNoTimeEntities = njzGuideServeNoTimeEntities;
    }

    public ServeUpdataBean getNjzGuideServeEntity() {
        return njzGuideServeEntity;
    }

    public void setNjzGuideServeEntity(ServeUpdataBean njzGuideServeEntity) {
        this.njzGuideServeEntity = njzGuideServeEntity;
    }

    public List<List<ServeGroupListInfo>> getserveGroupListInfo() {
        return njzGuideServeFormatEntityGroupList;
    }

    public void setserveGroupListInfo(List<List<ServeGroupListInfo>> serveGroupListInfo) {
        this.njzGuideServeFormatEntityGroupList = serveGroupListInfo;
    }

    public List<List<ServeGroupListInfo>> getNjzGuideServeFormatEntityGroupList() {
        return njzGuideServeFormatEntityGroupList;
    }

    public void setNjzGuideServeFormatEntityGroupList(List<List<ServeGroupListInfo>> njzGuideServeFormatEntityGroupList) {
        this.njzGuideServeFormatEntityGroupList = njzGuideServeFormatEntityGroupList;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public static class ServeUpdataBean {
        /**
         * id : 17
         * guideId : 35
         * commentId : null
         * titleImg : xxxx
         * servePrice : 100
         * servePriceSeg : null
         * serveMinNum : 1
         * serveMaxNum : 1
         * serveFeature : xxxx
         * serveType : 1
         * renegePriceThree : 7,4
         * renegePriceFive : 3,1
         * title : xxxx
         * status : 0
         * address : 长沙
         * addressId : 1
         * putDate : 1544013252000
         * upDate : null
         * forceDownDate : null
         * forceDownReason : null
         * operator : null
         * name : 大苏打
         * serveTypeName : xxxx
         * isEnabled : 0
         * mustPlay : 0
         * playTime : null
         * costExplain : xxxxx
         */

        private int id;
        private int guideId;
        private String commentId;
        private String titleImg;
        private float servePrice;
        private String servePriceSeg;
        private int serveMinNum;
        private int serveMaxNum;
        private String serveFeature;
        private int serveType;
        private String renegePriceThree;
        private String renegePriceFive;
        private String title;
        private int status;
        private String address;
        private int addressId;
        private String putDate;
        private String upDate;
        private String forceDownDate;
        private String forceDownReason;
        private String operator;
        private String name;
        private String serveTypeName;
        private int isEnabled;
        private int mustPlay;
        private String playTime;
        private String costExplain;
        private String purchaseRules;

        public String getPurchaseRules() {
            return purchaseRules;
        }

        public void setPurchaseRules(String purchaseRules) {
            this.purchaseRules = purchaseRules;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGuideId() {
            return guideId;
        }

        public void setGuideId(int guideId) {
            this.guideId = guideId;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
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

        public String getServePriceSeg() {
            return servePriceSeg;
        }

        public void setServePriceSeg(String servePriceSeg) {
            this.servePriceSeg = servePriceSeg;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStatus() {
            return status;
        }

        public String getStatusString() {
            switch (status){
                case 0:
                case 3:
                    return "上架";//修改为上架
                case 1:
                case 2:
                    return "下架";//修改为下架
            }
            return "";
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

        public String getPutDate() {
            return putDate;
        }

        public void setPutDate(String putDate) {
            this.putDate = putDate;
        }

        public String getUpDate() {
            return upDate;
        }

        public void setUpDate(String upDate) {
            this.upDate = upDate;
        }

        public String getForceDownDate() {
            return forceDownDate;
        }

        public void setForceDownDate(String forceDownDate) {
            this.forceDownDate = forceDownDate;
        }

        public String getForceDownReason() {
            return forceDownReason;
        }

        public void setForceDownReason(String forceDownReason) {
            this.forceDownReason = forceDownReason;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getServeTypeName() {
            return serveTypeName;
        }

        public void setServeTypeName(String serveTypeName) {
            this.serveTypeName = serveTypeName;
        }

        public int getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(int isEnabled) {
            this.isEnabled = isEnabled;
        }

        public int getMustPlay() {
            return mustPlay;
        }

        public void setMustPlay(int mustPlay) {
            this.mustPlay = mustPlay;
        }

        public String getPlayTime() {
            return playTime;
        }

        public void setPlayTime(String playTime) {
            this.playTime = playTime;
        }

        public String getCostExplain() {
            return costExplain;
        }

        public void setCostExplain(String costExplain) {
            this.costExplain = costExplain;
        }
    }

    @Override
    public String toString() {
        return "GetUpdateServiceInfo{" +
                "saleNum=" + saleNum +
                ", njzGuideServeEntity=" + njzGuideServeEntity +
                ", njzGuideServeFormatEntityGroupList=" + njzGuideServeFormatEntityGroupList +
                ", njzGuideServeNoTimeEntities=" + njzGuideServeNoTimeEntities +
                '}';
    }

    public static class ServeGroupListInfo {
        /**
         * id : 70
         * njzGuideServeId : 17
         * njzGuideServeFormatDic : xdpy_yy
         * guideServeFormatName : 向导陪游语言类型0
         * servePriceSelect : 
         * serveDefaultPrice : 100
         * formatUnit : null
         */

        private int id;
        private int njzGuideServeId;
        private String njzGuideServeFormatDic;
        private String guideServeFormatName;
        private String servePriceSelect;
        private float serveDefaultPrice;
        private String formatUnit;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNjzGuideServeId() {
            return njzGuideServeId;
        }

        public void setNjzGuideServeId(int njzGuideServeId) {
            this.njzGuideServeId = njzGuideServeId;
        }

        public String getNjzGuideServeFormatDic() {
            return njzGuideServeFormatDic;
        }

        public void setNjzGuideServeFormatDic(String njzGuideServeFormatDic) {
            this.njzGuideServeFormatDic = njzGuideServeFormatDic;
        }

        public String getGuideServeFormatName() {
            return guideServeFormatName;
        }

        public void setGuideServeFormatName(String guideServeFormatName) {
            this.guideServeFormatName = guideServeFormatName;
        }

        public String getServePriceSelect() {
            return servePriceSelect;
        }

        public void setServePriceSelect(String servePriceSelect) {
            this.servePriceSelect = servePriceSelect;
        }

        public float getServeDefaultPrice() {
            return serveDefaultPrice;
        }

        public void setServeDefaultPrice(float serveDefaultPrice) {
            this.serveDefaultPrice = serveDefaultPrice;
        }

        public String getFormatUnit() {
            return formatUnit;
        }

        public void setFormatUnit(String formatUnit) {
            this.formatUnit = formatUnit;
        }

        @Override
        public String toString() {
            return "ServeGroupListInfo{" +
                    "id=" + id +
                    ", njzGuideServeId=" + njzGuideServeId +
                    ", njzGuideServeFormatDic='" + njzGuideServeFormatDic + '\'' +
                    ", guideServeFormatName='" + guideServeFormatName + '\'' +
                    ", servePriceSelect='" + servePriceSelect + '\'' +
                    ", serveDefaultPrice=" + serveDefaultPrice +
                    ", formatUnit='" + formatUnit + '\'' +
                    '}';
        }
    }

    public static class NjzGuideServeNoTimeEntities{
        String dateValue;
        int njzGuideServeId;
        int id;

        public String getDateValue() {
            return dateValue;
        }

        public void setDateValue(String dateValue) {
            this.dateValue = dateValue;
        }

        public int getNjzGuideServeId() {
            return njzGuideServeId;
        }

        public void setNjzGuideServeId(int njzGuideServeId) {
            this.njzGuideServeId = njzGuideServeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "NjzGuideServeNoTimeEntities{" +
                    "dateValue='" + dateValue + '\'' +
                    ", njzGuideServeId=" + njzGuideServeId +
                    ", id=" + id +
                    '}';
        }
    }
}

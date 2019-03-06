package com.njz.letsgoappguides.model.mine;

import com.njz.letsgoappguides.constant.Constant;

/**
 * Created by Administrator on 2018/11/30.
 */

public class OrderSettleChildModel {


    /**
     * defaultMoney : 0
     * titleImg : http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181101/110232646989d3.jpg
     * orderId : 265
     * serveType : 23
     * serveId : 184
     * title : 测试私人定制
     * isDefaultMoney : 0
     * roomNum : 0
     * payPrice : 0.1
     * price : 0.1
     * ticketNum : 0
     * orderPrice : 0.1
     * id : 658
     * value : srdz
     * platFormCancelReason :
     * childOrderStatus : 2
     * unUseDay : 0
     * platTime : null
     * refundMoney : 0
     * personNum : 1
     * platformId : 0
     * adminName :
     * njzChildOrderRefundEntity : null
     * travelDate : 2018-11-16
     * dayNum : 2
     * payStatus : 2
     * useDay : 1
     */

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsDefaultMoney() {
        return isDefaultMoney;
    }

    public void setIsDefaultMoney(int isDefaultMoney) {
        this.isDefaultMoney = isDefaultMoney;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
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

    public String getPlatFormCancelReason() {
        return platFormCancelReason;
    }

    public void setPlatFormCancelReason(String platFormCancelReason) {
        this.platFormCancelReason = platFormCancelReason;
    }

    public int getChildOrderStatus() {
        return childOrderStatus;
    }

    public void setChildOrderStatus(int childOrderStatus) {
        this.childOrderStatus = childOrderStatus;
    }

    public int getUnUseDay() {
        return unUseDay;
    }

    public void setUnUseDay(int unUseDay) {
        this.unUseDay = unUseDay;
    }

    public String getPlatTime() {
        return platTime;
    }

    public void setPlatTime(String platTime) {
        this.platTime = platTime;
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

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public NjzChildOrderRefundEntity getNjzChildOrderRefundEntity() {
        return njzChildOrderRefundEntity;
    }

    public void setNjzChildOrderRefundEntity(NjzChildOrderRefundEntity njzChildOrderRefundEntity) {
        this.njzChildOrderRefundEntity = njzChildOrderRefundEntity;
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

    public int getUseDay() {
        return useDay;
    }

    public void setUseDay(int useDay) {
        this.useDay = useDay;
    }


    public static class NjzChildOrderRefundEntity{

        /**
         * defaultMoney : 0.5
         * coupon : 0
         * platTime : null
         * refundMoney : 0.5
         * guideOrPlatform : 0
         * mainly : 0
         * isDefaultMoney : 0
         * platform_id : 0
         * childOrderId : 179
         * useMoney : 0
         * platformCancelReason :
         * id : 30
         * refundId : 30
         */

        private float defaultMoney;
        private int coupon;
        private String platTime;
        private float refundMoney;
        private int guideOrPlatform;
        private int mainly;
        private int isDefaultMoney;
        private int platform_id;
        private int childOrderId;
        private float useMoney;
        private String platformCancelReason;
        private int id;
        private int refundId;

        public float getDefaultMoney() {
            return defaultMoney;
        }

        public void setDefaultMoney(float defaultMoney) {
            this.defaultMoney = defaultMoney;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public Object getPlatTime() {
            return platTime;
        }

        public void setPlatTime(String platTime) {
            this.platTime = platTime;
        }

        public double getRefundMoney() {
            return refundMoney;
        }

        public void setRefundMoney(float refundMoney) {
            this.refundMoney = refundMoney;
        }

        public int getGuideOrPlatform() {
            return guideOrPlatform;
        }

        public void setGuideOrPlatform(int guideOrPlatform) {
            this.guideOrPlatform = guideOrPlatform;
        }

        public int getMainly() {
            return mainly;
        }

        public void setMainly(int mainly) {
            this.mainly = mainly;
        }

        public int getIsDefaultMoney() {
            return isDefaultMoney;
        }

        public void setIsDefaultMoney(int isDefaultMoney) {
            this.isDefaultMoney = isDefaultMoney;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public int getChildOrderId() {
            return childOrderId;
        }

        public void setChildOrderId(int childOrderId) {
            this.childOrderId = childOrderId;
        }

        public float getUseMoney() {
            return useMoney;
        }

        public void setUseMoney(int useMoney) {
            this.useMoney = useMoney;
        }

        public String getPlatformCancelReason() {
            return platformCancelReason;
        }

        public void setPlatformCancelReason(String platformCancelReason) {
            this.platformCancelReason = platformCancelReason;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRefundId() {
            return refundId;
        }

        public void setRefundId(int refundId) {
            this.refundId = refundId;
        }
    }
}

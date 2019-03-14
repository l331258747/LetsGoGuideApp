package com.njz.letsgoappguides.model.mine;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */

public class OrderSettltRefundChildModel {


    /**
     * njzOrderVO : {"guideSureTime":"2018-10-26 14:04:40","lastPayTime":"2018-10-27 09:53:17","beBalancedDate":"2018-10-29 00:00:00","endDate":"2018-10-27 00:00:00","payTime":"2018-10-26 09:53:57","guideId":13,"orderStatus":2,"payType":"AliPay","njzChildOrderVOS":[{"defaultMoney":0,"titleImg":"http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181013/100032432858e4.jpg","orderId":130,"serveType":23,"serveId":140,"title":"长沙私人订制","isDefaultMoney":0,"roomNum":0,"payPrice":0.1,"price":0.1,"ticketNum":0,"orderPrice":0.1,"id":506,"value":"srdz","platFormCancelReason":"","childOrderStatus":2,"unUseDay":0,"platTime":null,"refundMoney":0,"personNum":1,"platformId":0,"adminName":"","njzChildOrderRefundEntity":{"defaultMoney":0,"coupon":0,"platTime":null,"refundMoney":0.1,"guideOrPlatform":0,"mainly":1,"isDefaultMoney":0,"platform_id":0,"childOrderId":506,"useMoney":0,"platformCancelReason":"","id":307,"refundId":311},"travelDate":"2018-10-26","dayNum":1,"payStatus":4,"useDay":1}],"payPrice":0.1,"orderPrice":0.1,"id":130,"sureTime":"4小时11分","balancePrice":0.1,"beforTravel":"","guideMobile":"18216151610","guideName":"胡玲","orderNo":"CS201810260953163686","specialRequire":"","balanceStatus":1,"mobile":"13211111111","balanceTime":"2018-10-29 09:41:15","payingStatus":1,"cancelTime":null,"createTime":"2018-10-26 09:53:17","name":"嗯我","location":"长沙","reviewStatus":1,"payStatus":4,"startDate":"2018-10-26 00:00:00"}
     * njzOrderRefundEntity : {"defaultMoney":0.05,"guideSureTime":"2018-10-26 14:04:40","orderId":130,"payTime":"2018-10-26 09:53:57","refundStatus":2,"refundContent":"游客付款后导游24小时未确认平台自动取消走退款","mainly":1,"refundType":"","payType":"AliPay","isDefaultMoney":0,"childOrderId":0,"useMoney":0,"orderPrice":0.1,"id":311,"njzRefundDetailsChildVOS":[{"defaultMoney":0,"titleImg":"http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181013/100032432858e4.jpg","childOrderStatus":2,"orderId":130,"serveType":23,"unUseDay":0,"serveId":140,"refundMoney":0.1,"personNum":1,"childRefundId":307,"title":"长沙私人订制","guideOrPlatform":0,"roomNum":0,"travelDate":"2018-10-26","payPrice":0.1,"price":0.1,"ticketNum":0,"dayNum":1,"useMoney":0.1,"orderPrice":0.1,"id":506,"payStatus":4,"value":"srdz","useDay":1}],"applyTime":"2018-11-16 23:27:07","sureTime":"4小时10分","guideName":"胡玲","orderNo":"CS201810260953163686","specialRequire":"","coupon":0,"balanceStatus":0,"refundReason":"平台取消订单走退款","refundTime":"2018-11-16 23:27:09","guideCheckTime":null,"mobile":"13211111111","refundMoney":0.05,"balanceTime":null,"checkTime":"401小时42分","createTime":"2018-10-26 09:53:17","name":"嗯我","location":"长沙"}
     * awaitBalanceMoney : 0.04
     * platformServeMoney : 0.01
     */

    private NjzOrderVOBean njzOrderVO;
    private NjzOrderRefundEntityBean njzOrderRefundEntity;
    private float awaitBalanceMoney;
    private float platformServeMoney;

    public NjzOrderVOBean getNjzOrderVO() {
        return njzOrderVO;
    }

    public void setNjzOrderVO(NjzOrderVOBean njzOrderVO) {
        this.njzOrderVO = njzOrderVO;
    }

    public NjzOrderRefundEntityBean getNjzOrderRefundEntity() {
        return njzOrderRefundEntity;
    }

    public void setNjzOrderRefundEntity(NjzOrderRefundEntityBean njzOrderRefundEntity) {
        this.njzOrderRefundEntity = njzOrderRefundEntity;
    }

    public float getAwaitBalanceMoney() {
        return awaitBalanceMoney;
    }

    public void setAwaitBalanceMoney(float awaitBalanceMoney) {
        this.awaitBalanceMoney = awaitBalanceMoney;
    }

    public float getPlatformServeMoney() {
        return platformServeMoney;
    }

    public void setPlatformServeMoney(float platformServeMoney) {
        this.platformServeMoney = platformServeMoney;
    }

    public static class NjzOrderVOBean {
        /**
         * guideSureTime : 2018-10-26 14:04:40
         * lastPayTime : 2018-10-27 09:53:17
         * beBalancedDate : 2018-10-29 00:00:00
         * endDate : 2018-10-27 00:00:00
         * payTime : 2018-10-26 09:53:57
         * guideId : 13
         * orderStatus : 2
         * payType : AliPay
         * njzChildOrderVOS : [{"defaultMoney":0,"titleImg":"http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181013/100032432858e4.jpg","orderId":130,"serveType":23,"serveId":140,"title":"长沙私人订制","isDefaultMoney":0,"roomNum":0,"payPrice":0.1,"price":0.1,"ticketNum":0,"orderPrice":0.1,"id":506,"value":"srdz","platFormCancelReason":"","childOrderStatus":2,"unUseDay":0,"platTime":null,"refundMoney":0,"personNum":1,"platformId":0,"adminName":"","njzChildOrderRefundEntity":{"defaultMoney":0,"coupon":0,"platTime":null,"refundMoney":0.1,"guideOrPlatform":0,"mainly":1,"isDefaultMoney":0,"platform_id":0,"childOrderId":506,"useMoney":0,"platformCancelReason":"","id":307,"refundId":311},"travelDate":"2018-10-26","dayNum":1,"payStatus":4,"useDay":1}]
         * payPrice : 0.1
         * orderPrice : 0.1
         * id : 130
         * sureTime : 4小时11分
         * balancePrice : 0.1
         * beforTravel : 
         * guideMobile : 18216151610
         * guideName : 胡玲
         * orderNo : CS201810260953163686
         * specialRequire : 
         * balanceStatus : 1
         * mobile : 13211111111
         * balanceTime : 2018-10-29 09:41:15
         * payingStatus : 1
         * cancelTime : null
         * createTime : 2018-10-26 09:53:17
         * name : 嗯我
         * location : 长沙
         * reviewStatus : 1
         * payStatus : 4
         * startDate : 2018-10-26 00:00:00
         */

        private String guideSureTime;
        private String lastPayTime;
        private String beBalancedDate;
        private String endDate;
        private String payTime;
        private int guideId;
        private int orderStatus;
        private String payType;
        private float payPrice;
        private float orderPrice;
        private int id;
        private String sureTime;
        private float balancePrice;
        private String beforTravel;
        private String guideMobile;
        private String guideName;
        private String orderNo;
        private String specialRequire;
        private int balanceStatus;
        private String mobile;
        private String balanceTime;
        private int payingStatus;
        private String cancelTime;
        private String createTime;
        private String name;
        private String location;
        private int reviewStatus;
        private int payStatus;
        private String startDate;
        private List<NjzChildOrderVOSBean> njzChildOrderVOS;

        public String getGuideSureTime() {
            return guideSureTime;
        }

        public void setGuideSureTime(String guideSureTime) {
            this.guideSureTime = guideSureTime;
        }

        public String getLastPayTime() {
            return lastPayTime;
        }

        public void setLastPayTime(String lastPayTime) {
            this.lastPayTime = lastPayTime;
        }

        public String getBeBalancedDate() {
            return beBalancedDate;
        }

        public void setBeBalancedDate(String beBalancedDate) {
            this.beBalancedDate = beBalancedDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getGuideId() {
            return guideId;
        }

        public void setGuideId(int guideId) {
            this.guideId = guideId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
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

        public String getSureTime() {
            return sureTime;
        }

        public void setSureTime(String sureTime) {
            this.sureTime = sureTime;
        }

        public float getBalancePrice() {
            return balancePrice;
        }

        public void setBalancePrice(float balancePrice) {
            this.balancePrice = balancePrice;
        }

        public String getBeforTravel() {
            return beforTravel;
        }

        public void setBeforTravel(String beforTravel) {
            this.beforTravel = beforTravel;
        }

        public String getGuideMobile() {
            return guideMobile;
        }

        public void setGuideMobile(String guideMobile) {
            this.guideMobile = guideMobile;
        }

        public String getGuideName() {
            return guideName;
        }

        public void setGuideName(String guideName) {
            this.guideName = guideName;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getSpecialRequire() {
            return specialRequire;
        }

        public void setSpecialRequire(String specialRequire) {
            this.specialRequire = specialRequire;
        }

        public int getBalanceStatus() {
            return balanceStatus;
        }

        public void setBalanceStatus(int balanceStatus) {
            this.balanceStatus = balanceStatus;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBalanceTime() {
            return balanceTime;
        }

        public void setBalanceTime(String balanceTime) {
            this.balanceTime = balanceTime;
        }

        public int getPayingStatus() {
            return payingStatus;
        }

        public void setPayingStatus(int payingStatus) {
            this.payingStatus = payingStatus;
        }

        public String getCancelTime() {
            return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
            this.cancelTime = cancelTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getReviewStatus() {
            return reviewStatus;
        }

        public void setReviewStatus(int reviewStatus) {
            this.reviewStatus = reviewStatus;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public List<NjzChildOrderVOSBean> getNjzChildOrderVOS() {
            return njzChildOrderVOS;
        }

        public void setNjzChildOrderVOS(List<NjzChildOrderVOSBean> njzChildOrderVOS) {
            this.njzChildOrderVOS = njzChildOrderVOS;
        }

        public static class NjzChildOrderVOSBean {
            /**
             * defaultMoney : 0
             * titleImg : http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181013/100032432858e4.jpg
             * orderId : 130
             * serveType : 23
             * serveId : 140
             * title : 长沙私人订制
             * isDefaultMoney : 0
             * roomNum : 0
             * payPrice : 0.1
             * price : 0.1
             * ticketNum : 0
             * orderPrice : 0.1
             * id : 506
             * value : srdz
             * platFormCancelReason : 
             * childOrderStatus : 2
             * unUseDay : 0
             * platTime : null
             * refundMoney : 0
             * personNum : 1
             * platformId : 0
             * adminName : 
             * njzChildOrderRefundEntity : {"defaultMoney":0,"coupon":0,"platTime":null,"refundMoney":0.1,"guideOrPlatform":0,"mainly":1,"isDefaultMoney":0,"platform_id":0,"childOrderId":506,"useMoney":0,"platformCancelReason":"","id":307,"refundId":311}
             * travelDate : 2018-10-26
             * dayNum : 1
             * payStatus : 4
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
            private NjzChildOrderRefundEntityBean njzChildOrderRefundEntity;
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

            public NjzChildOrderRefundEntityBean getNjzChildOrderRefundEntity() {
                return njzChildOrderRefundEntity;
            }

            public void setNjzChildOrderRefundEntity(NjzChildOrderRefundEntityBean njzChildOrderRefundEntity) {
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

            public static class NjzChildOrderRefundEntityBean {
                /**
                 * defaultMoney : 0
                 * coupon : 0
                 * platTime : null
                 * refundMoney : 0.1
                 * guideOrPlatform : 0
                 * mainly : 1
                 * isDefaultMoney : 0
                 * platform_id : 0
                 * childOrderId : 506
                 * useMoney : 0
                 * platformCancelReason : 
                 * id : 307
                 * refundId : 311
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
                private int useMoney;
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

                public String getPlatTime() {
                    return platTime;
                }

                public void setPlatTime(String platTime) {
                    this.platTime = platTime;
                }

                public float getRefundMoney() {
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

                public int getUseMoney() {
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
    }

    public static class NjzOrderRefundEntityBean {
        /**
         * defaultMoney : 0.05
         * guideSureTime : 2018-10-26 14:04:40
         * orderId : 130
         * payTime : 2018-10-26 09:53:57
         * refundStatus : 2
         * refundContent : 游客付款后导游24小时未确认平台自动取消走退款
         * mainly : 1
         * refundType : 
         * payType : AliPay
         * isDefaultMoney : 0
         * childOrderId : 0
         * useMoney : 0
         * orderPrice : 0.1
         * id : 311
         * njzRefundDetailsChildVOS : [{"defaultMoney":0,"titleImg":"http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181013/100032432858e4.jpg","childOrderStatus":2,"orderId":130,"serveType":23,"unUseDay":0,"serveId":140,"refundMoney":0.1,"personNum":1,"childRefundId":307,"title":"长沙私人订制","guideOrPlatform":0,"roomNum":0,"travelDate":"2018-10-26","payPrice":0.1,"price":0.1,"ticketNum":0,"dayNum":1,"useMoney":0.1,"orderPrice":0.1,"id":506,"payStatus":4,"value":"srdz","useDay":1}]
         * applyTime : 2018-11-16 23:27:07
         * sureTime : 4小时10分
         * guideName : 胡玲
         * orderNo : CS201810260953163686
         * specialRequire : 
         * coupon : 0
         * balanceStatus : 0
         * refundReason : 平台取消订单走退款
         * refundTime : 2018-11-16 23:27:09
         * guideCheckTime : null
         * mobile : 13211111111
         * refundMoney : 0.05
         * balanceTime : null
         * checkTime : 401小时42分
         * createTime : 2018-10-26 09:53:17
         * name : 嗯我
         * location : 长沙
         */

        private float defaultMoney;
        private String guideSureTime;
        private int orderId;
        private String payTime;
        private int refundStatus;
        private String refundContent;
        private int mainly;
        private String refundType;
        private String payType;
        private int isDefaultMoney;
        private int childOrderId;
        private int useMoney;
        private float orderPrice;
        private int id;
        private String applyTime;
        private String sureTime;
        private String guideName;
        private String orderNo;
        private String specialRequire;
        private int coupon;
        private int balanceStatus;
        private String refundReason;
        private String refundTime;
        private String guideCheckTime;
        private String mobile;
        private float refundMoney;
        private String balanceTime;
        private String checkTime;
        private String createTime;
        private String name;
        private String location;
        private int personNum;
        private int adult;
        private int children;
        private List<NjzRefundDetailsChildVOSBean> njzRefundDetailsChildVOS;

        private String orderNote;

        public String getOrderNote() {
            if(orderNote==null){
                return "";
            }
            return orderNote;
        }

        public int getPersonNum() {
            return personNum;
        }

        public String getPersonNumStr1() {
            return personNum+"人";
        }

        public String getPersonNumStr2(){
            return adult+"成人"+children+"儿童";
        }

        public int getAdult() {
            return adult;
        }

        public int getChildren() {
            return children;
        }

        public float getDefaultMoney() {
            return defaultMoney;
        }

        public void setDefaultMoney(float defaultMoney) {
            this.defaultMoney = defaultMoney;
        }

        public String getGuideSureTime() {
            if (guideSureTime==null){
                return "无";
            }
            return guideSureTime;
        }

        public void setGuideSureTime(String guideSureTime) {
            this.guideSureTime = guideSureTime;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(int refundStatus) {
            this.refundStatus = refundStatus;
        }

        public String getRefundContent() {
            return refundContent;
        }

        public void setRefundContent(String refundContent) {
            this.refundContent = refundContent;
        }

        public int getMainly() {
            return mainly;
        }

        public void setMainly(int mainly) {
            this.mainly = mainly;
        }

        public String getRefundType() {
            return refundType;
        }

        public void setRefundType(String refundType) {
            this.refundType = refundType;
        }

        public String getPayType() {
            if(TextUtils.equals(payType,"WxPay")){
                return "微信支付";
            }else if(TextUtils.equals(payType,"AliPay")){
                return "支付宝支付";
            }
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public int getIsDefaultMoney() {
            return isDefaultMoney;
        }

        public void setIsDefaultMoney(int isDefaultMoney) {
            this.isDefaultMoney = isDefaultMoney;
        }

        public int getChildOrderId() {
            return childOrderId;
        }

        public void setChildOrderId(int childOrderId) {
            this.childOrderId = childOrderId;
        }

        public int getUseMoney() {
            return useMoney;
        }

        public void setUseMoney(int useMoney) {
            this.useMoney = useMoney;
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

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getSureTime() {
            return sureTime;
        }

        public void setSureTime(String sureTime) {
            this.sureTime = sureTime;
        }

        public String getGuideName() {
            return guideName;
        }

        public void setGuideName(String guideName) {
            this.guideName = guideName;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getSpecialRequire() {
            return specialRequire;
        }

        public void setSpecialRequire(String specialRequire) {
            this.specialRequire = specialRequire;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public int getBalanceStatus() {
            return balanceStatus;
        }

        public void setBalanceStatus(int balanceStatus) {
            this.balanceStatus = balanceStatus;
        }

        public String getRefundReason() {
            return refundReason;
        }

        public void setRefundReason(String refundReason) {
            this.refundReason = refundReason;
        }

        public String getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(String refundTime) {
            this.refundTime = refundTime;
        }

        public String getGuideCheckTime() {
            if(guideCheckTime==null){
                return "";
            }
            return guideCheckTime;
        }

        public void setGuideCheckTime(String guideCheckTime) {
            this.guideCheckTime = guideCheckTime;
        }

        public String getMobile() {
            return mobile;
        }
        public String getMobilehide() {
            return StringUtils.midleReplaceStar(mobile);
        }


        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public float getRefundMoney() {
            return refundMoney;
        }

        public void setRefundMoney(float refundMoney) {
            this.refundMoney = refundMoney;
        }

        public String getBalanceTime() {
            if(balanceTime==null){
                return "";
            }
            return balanceTime;
        }

        public void setBalanceTime(String balanceTime) {
            this.balanceTime = balanceTime;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }
        public String getNameHide() {
            return StringUtils.nameReplaceStar(name);
        }


        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public List<NjzRefundDetailsChildVOSBean> getNjzRefundDetailsChildVOS() {
            return njzRefundDetailsChildVOS;
        }

        public void setNjzRefundDetailsChildVOS(List<NjzRefundDetailsChildVOSBean> njzRefundDetailsChildVOS) {
            this.njzRefundDetailsChildVOS = njzRefundDetailsChildVOS;
        }

        public static class NjzRefundDetailsChildVOSBean {
            /**
             * defaultMoney : 0
             * titleImg : http://najiuzou-1256768961.cos.ap-guangzhou.myqcloud.com/upload/20181013/100032432858e4.jpg
             * childOrderStatus : 2
             * orderId : 130
             * serveType : 23
             * unUseDay : 0
             * serveId : 140
             * refundMoney : 0.1
             * personNum : 1
             * childRefundId : 307
             * title : 长沙私人订制
             * guideOrPlatform : 0
             * roomNum : 0
             * travelDate : 2018-10-26
             * payPrice : 0.1
             * price : 0.1
             * ticketNum : 0
             * dayNum : 1
             * useMoney : 0.1
             * orderPrice : 0.1
             * id : 506
             * payStatus : 4
             * value : srdz
             * useDay : 1
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
            private int bugGet;
            private int serveNum;

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

            public int getBugGet() {
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

            public int getChildOrderStatus() {
                return childOrderStatus;
            }

            public void setChildOrderStatus(int childOrderStatus) {
                this.childOrderStatus = childOrderStatus;
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

            public int getUnUseDay() {
                return unUseDay;
            }

            public void setUnUseDay(int unUseDay) {
                this.unUseDay = unUseDay;
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

            public int getChildRefundId() {
                return childRefundId;
            }

            public void setChildRefundId(int childRefundId) {
                this.childRefundId = childRefundId;
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

            public void setGuideOrPlatform(int guideOrPlatform) {
                this.guideOrPlatform = guideOrPlatform;
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

            public void setUseMoney(float useMoney) {
                this.useMoney = useMoney;
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

            public void setValue(String value) {
                this.value = value;
            }

            public int getUseDay() {
                return useDay;
            }

            public void setUseDay(int useDay) {
                this.useDay = useDay;
            }
        }
    }
}
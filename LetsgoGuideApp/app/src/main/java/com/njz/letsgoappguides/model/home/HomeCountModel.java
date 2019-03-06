package com.njz.letsgoappguides.model.home;

/**
 * Created by LGQ
 * Time: 2018/11/12
 * Function:
 */

public class HomeCountModel {


    /**
     * beforePayCount : 0
     * beforeSureCount : 0
     * refundCount : 0
     * beBalancedCount : 0
     * afterSureCount : 0
     * todayCount : 0
     * totalCount : 0
     */

    private int beforePayCount;
    private int beforeSureCount;
    private int refundCount;
    private int beBalancedCount;
    private int afterSureCount;
    private int todayCount;
    private int totalCount;

    public String getBeforePayCount() {
        if(beforePayCount == 0){
            return "";
        }
        return beforePayCount+"";
    }

    public void setBeforePayCount(int beforePayCount) {
        this.beforePayCount = beforePayCount;
    }

    public String getBeforeSureCount() {
        if(beforeSureCount == 0){
            return "";
        }
        return beforeSureCount+"";
    }

    public void setBeforeSureCount(int beforeSureCount) {
        this.beforeSureCount = beforeSureCount;
    }

    public String getRefundCount() {
        if(refundCount == 0){
            return "";
        }
        return refundCount+"";
    }

    public void setRefundCount(int refundCount) {
        this.refundCount = refundCount;
    }

    public String getBeBalancedCount() {
        return beBalancedCount+"";
    }

    public void setBeBalancedCount(int beBalancedCount) {
        this.beBalancedCount = beBalancedCount;
    }

    public String getAfterSureCount() {
        if(afterSureCount == 0){
            return "";
        }
        return afterSureCount+"";
    }

    public void setAfterSureCount(int afterSureCount) {
        this.afterSureCount = afterSureCount;
    }

    public String getTodayCount() {
        return todayCount+"";
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }

    public String getTotalCount() {
        return totalCount+"";
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}

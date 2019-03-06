package com.njz.letsgoappguides.model.mine;

import android.text.TextUtils;

import com.njz.letsgoappguides.model.BasePageModel;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */

public class IncomeInfo<T> {

    /**
     * pageUtils : {"currPage":1,"totalPage":2147483647,"pageSize":0,"list":[],"totalCount":12}
     * totalYield : 3.05
     * awaitTotalMoney : 4.45
     */

    private PageUtilsBean pageUtils;
    private float totalYield;
    private float awaitTotalMoney;

    public PageUtilsBean getPageUtils() {
        return pageUtils;
    }

    public void setPageUtils(PageUtilsBean pageUtils) {
        this.pageUtils = pageUtils;
    }

    public float getTotalYield() {
        return totalYield;
    }

    public void setTotalYield(float totalYield) {
        this.totalYield = totalYield;
    }

    public float getAwaitTotalMoney() {
        return awaitTotalMoney;
    }

    public void setAwaitTotalMoney(float awaitTotalMoney) {
        this.awaitTotalMoney = awaitTotalMoney;
    }

    @Override
    public String toString() {
        return "IncomeInfo{" +
                "pageUtils=" + pageUtils +
                ", totalYield=" + totalYield +
                ", awaitTotalMoney=" + awaitTotalMoney +
                '}';
    }

public static class PageUtilsBean<T> {
        /**
         * currPage : 1
         * totalPage : 2147483647
         * pageSize : 0
         * list : []
         * totalCount : 12
         */

        private int currPage;
        private int totalPage;
        private int pageSize;
        private int totalCount;
        private List<IncomeListInfo> list;

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<IncomeListInfo> getList() {
            return list;
        }

        public void setList(List<IncomeListInfo> list) {
            this.list = list;
        }
    }
}

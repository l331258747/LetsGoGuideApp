package com.njz.letsgoappguides.model.settlement;

import com.njz.letsgoappguides.model.settlement.IncomeListInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */

public class IncomeInfo<T> {
    private PageUtilsBean pageUtils;
    private float totalYield;
    private float awaitTotalMoney;

    public PageUtilsBean getPageUtils() {
        return pageUtils;
    }

    public float getTotalYield() {
        return totalYield;
    }

    public float getAwaitTotalMoney() {
        return awaitTotalMoney;
    }

}

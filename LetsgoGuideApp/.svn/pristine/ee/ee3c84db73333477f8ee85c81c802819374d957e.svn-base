package com.njz.letsgoappguides.model.server;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/2/18.
 */

public class PurchaseRulesVo {

    private String ruleClassName;   //处理类 "路径已定" 默认传 QuantitativeRulesHandle
    private String uniqueValue;     //所要自定义的 规格 唯一区别
    private int[] entityList;//传参列表
    private boolean isDefault;      //


    public String getRuleClassName() {
        return ruleClassName;
    }

    public void setRuleClassName(String ruleClassName) {
        this.ruleClassName = ruleClassName;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public void setUniqueValue(String uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public int[] getEntityList() {
        return entityList;
    }

    public void setEntityList(int[] entityList) {
        this.entityList = entityList;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {//不能删除
        return "{" +
                "ruleClassName:'" + ruleClassName + '\'' +
                ", uniqueValue:'" + uniqueValue + '\'' +
                ", entityList:" + Arrays.toString(entityList) +
                ", isDefault:" + isDefault +
                '}';
    }
}

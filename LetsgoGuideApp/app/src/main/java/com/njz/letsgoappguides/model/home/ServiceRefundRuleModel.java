package com.njz.letsgoappguides.model.home;

import android.text.TextUtils;

/**
 * Created by LGQ
 * Time: 2018/10/19
 * Function:
 */

public class ServiceRefundRuleModel {


    /**
     * cost_explain :
     * id : 36
     * renegePriceFive : 1,6
     * renegePriceThree : 7,13
     */

    private String costExplain;
    private int id;
    private String renegePriceFive;
    private String renegePriceThree;
    private String renege_price_three;
    private String renege_price_five;

    public String getCostExplain() {
        return costExplain;
    }

    public void setCostExplain(String costExplain) {
        this.costExplain = costExplain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRenegePriceFive() {
        if(TextUtils.isEmpty(renegePriceFive))
            return renege_price_five;
        return renegePriceFive;
    }


    public String getRenegePriceThree() {
        if(TextUtils.isEmpty(renegePriceThree))
            return renege_price_three;
        return renegePriceThree;
    }

}

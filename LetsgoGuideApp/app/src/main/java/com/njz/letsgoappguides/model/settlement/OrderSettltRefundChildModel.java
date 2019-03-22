package com.njz.letsgoappguides.model.settlement;

import android.text.TextUtils;

import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */

public class OrderSettltRefundChildModel {

    private NjzOrderVOBean njzOrderVO;
    private NjzOrderRefundEntityBean njzOrderRefundEntity;
    private float awaitBalanceMoney;
    private float platformServeMoney;

    public NjzOrderVOBean getNjzOrderVO() {
        return njzOrderVO;
    }

    public NjzOrderRefundEntityBean getNjzOrderRefundEntity() {
        return njzOrderRefundEntity;
    }

    public float getAwaitBalanceMoney() {
        return awaitBalanceMoney;
    }


    public float getPlatformServeMoney() {
        return platformServeMoney;
    }





}
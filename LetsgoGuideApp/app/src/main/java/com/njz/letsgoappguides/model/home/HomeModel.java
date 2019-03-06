package com.njz.letsgoappguides.model.home;

import com.njz.letsgoappguides.model.evaluation.ReviewList;
import com.njz.letsgoappguides.model.other.BannerModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/12
 * Function:
 */

public class HomeModel {


    List<BannerModel> njzBannerList;
    HomeCountModel njzOrderCounts;
    List<ReviewList> travelOrderReview;

    public List<ReviewList> getTravelOrderReview() {
        return travelOrderReview;
    }

    public List<BannerModel> getNjzBannerList() {
        return njzBannerList;
    }

    public HomeCountModel getNjzOrderCounts() {
        return njzOrderCounts;
    }

    @Override
    public String toString() {
        return "HomeModel{" +
                "njzBannerList=" + njzBannerList +
                '}';
    }
}

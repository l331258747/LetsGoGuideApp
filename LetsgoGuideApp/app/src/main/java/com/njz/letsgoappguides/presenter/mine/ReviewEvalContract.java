package com.njz.letsgoappguides.presenter.mine;


/**
 * Created by Administrator on 2018/11/14.
 */

public interface ReviewEvalContract {

    interface Presenter {
        void getReviewEval(String guideContent, String userId,int id, int orderId);
    }

    interface View {
        void getReviewEvalSuccess(String str);
        void getReviewEvalFailed(String msg);
    }
}

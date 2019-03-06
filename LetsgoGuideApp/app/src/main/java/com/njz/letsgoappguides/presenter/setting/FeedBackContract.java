package com.njz.letsgoappguides.presenter.setting;

import com.njz.letsgoappguides.model.mine.FeedBackInfo;

/**
 * Created by Administrator on 2018/11/13.
 */

public interface FeedBackContract {

    interface Presenter {
        void saveFeedback(FeedBackInfo infos );
    }

    interface View {
        void saveFeedbackSuccess(String str);
        void saveFeedbackFailed(String msg);
    }
}

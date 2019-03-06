package com.njz.letsgoappguides.presenter.message;


import com.njz.letsgoappguides.model.message.NotifyMainModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/10/12
 * Function:
 */

public interface NotifyListContract {

    interface Presenter {
        void msgPushGetReceiveMsgList(String type, int limit, int page);
    }

    interface View{
        void msgPushGetReceiveMsgListSuccess(List<NotifyMainModel> data);
        void msgPushGetReceiveMsgListFailed(String msg);
    }
}

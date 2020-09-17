package com.njz.letsgoappguides.presenter.im;

/**
 * Created by LGQ
 * Time: 2018/10/12
 * Function:
 */

public interface IMContract {

    interface Presenter {
        void saveMessage(String fromId, String toId, String chatType, String msg);
    }

    interface View{
        void saveMessageSuccess(String model);
        void saveMessageFailed(String msg);
    }
}

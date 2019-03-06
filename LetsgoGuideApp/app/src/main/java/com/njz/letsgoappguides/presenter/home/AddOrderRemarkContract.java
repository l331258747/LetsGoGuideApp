package com.njz.letsgoappguides.presenter.home;

import com.njz.letsgoappguides.model.home.HomeModel;


public interface AddOrderRemarkContract {

    interface Presenter {
        void addOrderNote(int orderId,String orderNote);
    }

    interface View {
        void addOrderNoteSuccess(String str);
        void addOrderNoteFailed(String msg);
    }

}

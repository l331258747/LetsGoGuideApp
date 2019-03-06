package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.mine.LabelModel;
import com.njz.letsgoappguides.model.mine.MyInfoData;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public interface ModifyInfoContract {

    interface Presenter {
        void userUpdate(MyInfoData data);
    }

    interface View {
        void userUpdateSuccess(String datas);
        void userUpdateFailed(String msg);
    }
}

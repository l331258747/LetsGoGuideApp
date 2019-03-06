package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.authentication.DriveValidInfo;
import com.njz.letsgoappguides.model.login.UserVo;
import com.njz.letsgoappguides.model.mine.BinkIntoInfo;
import com.njz.letsgoappguides.model.mine.GetBackListInfo;
import com.njz.letsgoappguides.model.mine.GetBinkInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/11/16.
 */

public interface BinkSaveContract {

    interface Presenter {
        void toBinkSave(BinkIntoInfo mBinkIntoInfo);
        void toBinkUpdate(BinkIntoInfo mBinkIntoInfo);
        void getBinkinfo();
        void getBackList();
    }

    interface View {
        void toBinkSaveSuccess(String str);
        void toBinkSaveFailed(String msg);

        void toBinkUpdateSuccess(String str);
        void toBinkUpdateFailed(String msg);

        void getBinkinfoSuccess(GetBinkInfo infos);
        void getBinkinfoFailed(String msg);

        void getBackListSuccess(List<GetBackListInfo> infos);
        void getBackListFailed(String msg);
    }
}

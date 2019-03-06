package com.njz.letsgoappguides.presenter.other;

import com.njz.letsgoappguides.model.authentication.DriveTypeInfo;
import com.njz.letsgoappguides.model.authentication.DriveValidInfo;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/16
 * Function:
 */

public interface DriveTypeContract {

    interface Presenter {
        void getDriveType();
    }

    interface View {
        void getDriveTypeSuccess(List<DriveTypeInfo> list);
        void getDriveTypeFailed(String msg);
    }
}

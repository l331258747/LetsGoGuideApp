package com.njz.letsgoappguides.presenter.other;

import java.io.File;

/**
 * Created by LGQ
 * Time: 2018/11/16
 * Function:
 */

public interface UpLoadContract {

    interface Presenter {
        void upUpload(File file);
    }

    interface View {
        void upUploadSuccess(String datas);
        void upUploadFailed(String msg);
    }
}

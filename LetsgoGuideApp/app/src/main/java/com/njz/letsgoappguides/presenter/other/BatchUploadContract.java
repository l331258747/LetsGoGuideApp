package com.njz.letsgoappguides.presenter.other;

import com.njz.letsgoappguides.model.mine.BatchUploadInfo;

import java.io.File;
import java.util.List;


public interface BatchUploadContract {

    interface Presenter {
        void batchUpload(List<String> files);
    }

    interface View {
        void batchUploadSuccess(BatchUploadInfo datas);
        void batchUploadFailed(String msg);
    }
}

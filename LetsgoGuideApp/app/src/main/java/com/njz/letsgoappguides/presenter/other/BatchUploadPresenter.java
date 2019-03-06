package com.njz.letsgoappguides.presenter.other;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.mine.BatchUploadInfo;

import java.io.File;
import java.util.List;


public class BatchUploadPresenter implements BatchUploadContract.Presenter {

    Context context;
    BatchUploadContract.View iView;

    public BatchUploadPresenter(Context context, BatchUploadContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void batchUpload(List<String> files) {
        ResponseCallback listener = new ResponseCallback<BatchUploadInfo>() {
            @Override
            public void onSuccess(BatchUploadInfo datas) {
                iView.batchUploadSuccess(datas);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.batchUploadFailed(errorMsg);
            }
        };
        MethodApi.batchUpload(files,new ProgressSubscriber(listener, context ,false));
    }
}

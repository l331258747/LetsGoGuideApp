package com.njz.letsgoappguides.http;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.njz.letsgoappguides.util.HasActivity;
import com.njz.letsgoappguides.util.ProgressCancelListener;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;

/**
 * Created by Administrator on 2018/11/2.
 */

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private LoadingDialog loadingDialog;;

    private Context context;
    private boolean cancelable;
    private boolean show;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable,boolean show) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        this.show = show;
    }

    private void initProgressDialog(){
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
            loadingDialog.setCancelable(cancelable);
            if (cancelable) {
                loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!loadingDialog.isShowing()&&show) {
                loadingDialog.show();
            }
        }
    }

    private void dismissProgressDialog(){
        if(!HasActivity.isDestroy((Activity) context)){
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }


}

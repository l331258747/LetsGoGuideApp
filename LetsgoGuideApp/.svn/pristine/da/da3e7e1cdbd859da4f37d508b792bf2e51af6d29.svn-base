package com.njz.letsgoappguides.presenter.server;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.ServerTypeModel;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerTypePresenter implements ServerTypeContract.Presenter {

    Context context;
    ServerTypeContract.View iView;

    public ServerTypePresenter(Context context, ServerTypeContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void serveGetServe() {
        ResponseCallback listener = new ResponseCallback<List<ServerTypeModel>>() {
            @Override
            public void onSuccess(List<ServerTypeModel> datainfo) {
                iView.serveGetServeSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.serveGetServeFailed(errorMsg);
            }
        };
        MethodApi.serveGetServe(new ProgressSubscriber(listener, context,false));
    }

    @Override
    public void getServeDicList(boolean isShow) {
        ResponseCallback listener = new ResponseCallback<GetServeDicListModel>() {
            @Override
            public void onSuccess(GetServeDicListModel datainfo) {
                iView.getServeDicListSuccess(datainfo);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getServeDicListFailed(errorMsg);
            }
        };
        MethodApi.getServeDicList(new ProgressSubscriber(listener, context, isShow));
    }
}

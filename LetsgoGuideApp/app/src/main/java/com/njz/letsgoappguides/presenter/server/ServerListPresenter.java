package com.njz.letsgoappguides.presenter.server;

import android.content.Context;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.BasePageModel;
import com.njz.letsgoappguides.model.server.PageModel;
import com.njz.letsgoappguides.model.server.ServerListModel;


/**
 * Created by LGQ
 * Time: 2018/11/23
 * Function:
 */

public class ServerListPresenter implements ServerListContract.Presenter {

    Context context;
    ServerListContract.View iView;

    public ServerListPresenter(Context context, ServerListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void serveList(int page, int limit, String address, String serveType, String status, String key) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<ServerListModel>>() {
            @Override
            public void onSuccess(BasePageModel<ServerListModel> datainfo) {
                iView.serveListSuccess(datainfo.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.serveListFailed(errorMsg);
            }
        };
        MethodApi.serveList(page,limit,address,serveType,status,key,new ProgressSubscriber(listener, context,false));
    }
}

package com.njz.letsgoappguides.presenter.mine;

import android.content.Context;
import android.util.Log;

import com.njz.letsgoappguides.http.MethodApi;
import com.njz.letsgoappguides.http.ProgressSubscriber;
import com.njz.letsgoappguides.http.ResponseCallback;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public class GetLanguagePresenter implements GetLanguageContract.Presenter {

    Context context;
    GetLanguageContract.View iView;

    public GetLanguagePresenter(Context context, GetLanguageContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void userGetLanguage() {
        ResponseCallback listener = new ResponseCallback<List<GuideMacroEntityList>>() {
            @Override
            public void onSuccess(List<GuideMacroEntityList> str) {
                iView.userGetLanguageSuccess(str);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.userGetLanguageFailed(errorMsg);
            }
        };
        MethodApi.userGetLanguage(new ProgressSubscriber(listener, context));

    }
    @Override
    public void getCheckLanguage() {
        ResponseCallback listener = new ResponseCallback<List<GuideMacroEntityList>>() {
            @Override
            public void onSuccess(List<GuideMacroEntityList> str) {
                iView.getCheckLanguageSuccess(str);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getCheckLanguageFailed(errorMsg);
            }
        };
        MethodApi.getCheckLanguage(new ProgressSubscriber(listener, context));

    }
}

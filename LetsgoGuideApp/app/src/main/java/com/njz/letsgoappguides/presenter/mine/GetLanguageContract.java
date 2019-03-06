package com.njz.letsgoappguides.presenter.mine;

import com.njz.letsgoappguides.model.login.GuideMacroEntityList;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/11/20
 * Function:
 */

public interface GetLanguageContract {

    interface Presenter {
        void userGetLanguage();
        void getCheckLanguage();
    }

    interface View {
        void userGetLanguageSuccess(List<GuideMacroEntityList> str);
        void userGetLanguageFailed(String msg);
        void getCheckLanguageSuccess(List<GuideMacroEntityList> str);
        void getCheckLanguageFailed(String msg);
    }
}

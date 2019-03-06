package com.njz.letsgoappguides.ui.activites.mysetting;

import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.model.mine.OperationGuideModel;
import com.njz.letsgoappguides.presenter.mine.OperationGuideContract;
import com.njz.letsgoappguides.presenter.mine.OperationGuidePresenter;
import com.njz.letsgoappguides.util.webview.LWebView;

import butterknife.BindView;
import butterknife.OnClick;


public class OperationGuideActivity extends BaseActivity implements OperationGuideContract.View{

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.webview)
    LWebView webview;
//    OperationGuidePresenter mOperationGuidePresenter;

    @Override
    public void getIntentData() {
        super.getIntentData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide_operation;
    }

    @OnClick(R.id.left_iv)
    public void leftBack() {
//        finish();
        if(webview.canGoBack()){
            webview.goBack();
        }else{
            finish();
        }
    }


    @Override
    protected void initView() {
        titleTv.setText("操作指南");

//        mOperationGuidePresenter=new OperationGuidePresenter(this,context);
//        mOperationGuidePresenter.getOperationGuide();
        webview.loadUrl("https://mp.weixin.qq.com/mp/homepage?__biz=MzU5MzY2OTI5MQ==&hid=1&sn=c9ee18cd9ae252e08acf0951a7c4d077");
    }

    @Override
    public void getOperationGuideSuccess(OperationGuideModel str) {
//        webview.loadDataWithBaseURL(null, str.getContent(), "text/html", "utf-8", null);
    }

    @Override
    public void getOperationGuideFailed(String msg) {

    }
}

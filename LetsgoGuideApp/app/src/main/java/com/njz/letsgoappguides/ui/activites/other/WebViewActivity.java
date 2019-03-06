package com.njz.letsgoappguides.ui.activites.other;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.util.webview.LWebView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Class Name: WebViewActivity.java
 * <p>
 * Function: 通用的 WebView 界面
 * <p>
 * version 1.0
 * since  2017/02/20 17:49
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    private ProgressBar progressBar;
    private LWebView webView;
    private String url = "";
    private String title = "";

    public LWebView getWebView() {
        return webView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.webview_layout;
    }

    public void getIntentData() {
        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.EXTRA_URL);
        title = intent.getStringExtra(Constant.EXTRA_TITLE);
        if (TextUtils.isEmpty(url)) {
            url = "https://jingyan.baidu.com/article/6525d4b179af49ac7d2e94a1.html";
        }
    }

    @Override
    public void initView() {
        titleTv.setText(title);
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoBack()){
                    webView.goBack();
                }else{
                    WebViewActivity.super.onBackPressed();
                }
            }
        });

        progressBar = $(R.id.progressbar);

        webView = $(R.id.webview);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){//WebView默认不支持同时加载Https和Http混合模式
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String titles) {
                super.onReceivedTitle(view, titles);
//                if(TextUtils.isEmpty(titles))return;
//                titleTv.setText(titles);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                progressBar.setVisibility(View.VISIBLE);

                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http") || url.startsWith("https"))
                webView.loadUrl(url);
            else {
                url = "http://" + url;
                webView.loadUrl(url);
            }

            Log.e("li", "url:" + url);
        } else {
//			webView.loadUrl("file:///android_asset/error.html");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // webview 需要加载空界面来释放资源
        webView.loadUrl("about:blank");
        webView.clearCache(false);
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        // 搜索可以返回
//		if (webView.canGoBack()) {
//			webView.goBack();
//		} else {
//			super.onBackPressed();
//		}
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

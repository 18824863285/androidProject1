package com.gangxiang.aiDaiOrder.ui.activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.base.BaseActivity;
import com.gangxiang.aiDaiOrder.widght.ColorProgressBar;
import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/28.
 */

public class WebviewActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebView;

    @BindView(R.id.pb)
    ColorProgressBar mColorProgressBar;

    private String mUrl;

    public static final String URL = "url";

    @Override
    public int attachLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    public void init() {

        mUrl = getIntent().getStringExtra(URL);

        System.out.println("====>mUrl:"+mUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitleBar(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mColorProgressBar.setProgress(newProgress);
                if(newProgress == 100){
                    mColorProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                System.out.println("====>webUrl2:"+url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }
        });

        mWebView.loadUrl(mUrl);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }



    @Override
    public void requestCallBack(String response, int id) {

    }
}

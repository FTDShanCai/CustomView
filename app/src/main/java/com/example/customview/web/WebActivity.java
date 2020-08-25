package com.example.customview.web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.customview.R;

public class WebActivity extends AppCompatActivity {

    WebView web_view;
    boolean isLoad = false;
    boolean isLoadFinish = false;

    String targetUrl = "http://www.chinalink.tv/content/56541.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web_view = findViewById(R.id.web_view);

        web_view.setWebChromeClient(new WebChromeClient());
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("ftd", "onPageStarted url:" + url);
                isLoad = true;
                targetUrl = url;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d("ftd", "onPageFinished url:" + url);
                if (url.equals(targetUrl)) {
                    String js = getClearAdDivJs();
                    view.loadUrl(js); //加载js方法代码
                    view.loadUrl("javascript:hideAd();"); //调用js方法
                    isLoadFinish = true;
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("ftd", "shouldOverrideUrlLoading url:" + url);
                if (isLoadFinish) {
                    return true;
                }
//                if (isLoad && !url.equals(targetUrl)) {
//                    return true;
//                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        web_view.loadUrl(targetUrl);
    }

    public String getClearAdDivJs() {
        String js = "javascript:function hideAd() {";
        String[] adDivs = {"float-bottom-bar"};
        for (int i = 0; i < adDivs.length; i++) {
            //通过div的id属性删除div元素
            //js += "var adDiv"+i+"= document.getElementById('"+adDivs[i]+"');if(adDiv"+i+" != null)adDiv"+i+".parentNode.removeChild(adDiv"+i+");";
            //通过div的class属性隐藏div元素
            js += "var adDiv" + i + "= document.getElementsByClassName('" + adDivs[i] + "');if(adDiv" + i + " != null)" +
                    "{var x; for (x = 0; x < adDiv" + i + ".length; x++) {adDiv" + i + "[x].style.display='none';}}";
        }
        js += "}";
        return js;
    }

    @Override
    public void onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
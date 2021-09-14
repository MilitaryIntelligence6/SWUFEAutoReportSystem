package cn.misection.autoreport.report.controller;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.xuexiang.xui.XUI;

import java.util.List;

import cn.misection.autoreport.BuildConfig;
import cn.misection.autoreport.R;
import cn.misection.autoreport.common.constant.SwufePage;
import cn.misection.autoreport.databinding.ActivityReportWebBinding;
import cn.misection.autoreport.report.entity.ReportInfo;
import cn.misection.autoreport.common.util.jsutil.InfoToJsCompiler;
import cn.misection.util.oututil.system.AppSystem;

/**
 * @author Administrator
 */
public class ReportWebActivity extends AppCompatActivity {

    private ActivityReportWebBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_web);
        init();
    }

    private void init() {
        initBinding();
        initWebViewSetting();
        initPage();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_report_web);
    }

    private void initWebViewSetting() {
        //支持javascript
        mBinding.reportWebView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mBinding.reportWebView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mBinding.reportWebView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        mBinding.reportWebView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        mBinding.reportWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mBinding.reportWebView.getSettings().setLoadWithOverviewMode(true);

        //如果不设置WebViewClient，请求会跳转系统浏览器
        mBinding.reportWebView.setWebViewClient(new WebViewClient() {

            private int enterLoginCount = 0;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view.getUrl().equals(SwufePage.REPORT.getUrl())) {
                    if (BuildConfig.DEBUG) {
                        AppSystem.out.printt(ReportWebActivity.this, "report branch");
                    }
                    evalSelectJs();
                } else if (view.getUrl().contains(ReportWebActivity.this.getString(R.string.swufe_auth_keyword))) {
                    ++enterLoginCount;
                    switch (enterLoginCount) {
                        case 1:
                            alertDefault(ReportWebActivity.this.getString(R.string.swufe_login_page_prompt));
                            break;
                        default:
                            alertDefault(ReportWebActivity.this.getString(R.string.swufe_reenter_login_page_prompt));
                            break;
                    }
                } else {
                    if (BuildConfig.DEBUG) {
                        AppSystem.out.printt(ReportWebActivity.this, "else branch");
                        AppSystem.out.printt(ReportWebActivity.this, view.getUrl());
                    }
                    Log.e(getClass().getName(), view.getUrl());
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.contains("sina.cn")) {
                    view.loadUrl("http://ask.csdn.net/questions/178242");
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")) {
                        view.loadUrl("http://ask.csdn.net/questions/178242");
                        return true;
                    }
                }
                return false;
            }
        });

        mBinding.reportWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });
    }

    private void initPage() {
        mBinding.reportWebView.loadUrl(SwufePage.REPORT.getUrl());
    }

    private void alertDefault(String msg) {
        new AlertDialog.Builder(ReportWebActivity.this)
                .setTitle(getString(R.string.tips))
                .setMessage(msg)
                .setIcon(R.mipmap.ic_my_launcher_round)
                .setPositiveButton(getString(R.string.sure),
                        (DialogInterface dialog, int which) -> AppSystem.out.prints(mBinding.reportWebView,
                                getString(R.string.swufe_login_snackbar_prompt)))
                .create()
                .show();
    }

    private void evalSelectJs() {
        ReportInfo reportInfo = (ReportInfo) getIntent().getExtras().getSerializable(getString(R.string.activity_param_key));
        List<String> scriptList = new InfoToJsCompiler(reportInfo).getScriptList();
        for (String script : scriptList) {
            mBinding.reportWebView.evaluateJavascript(script, null);
        }
    }
}
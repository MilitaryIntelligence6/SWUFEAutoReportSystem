package cn.misection.autoreport.ui.activity;

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

import java.io.Serializable;

import cn.misection.autoreport.BuildConfig;
import cn.misection.autoreport.R;
import cn.misection.autoreport.constant.JavaScriptPool;
import cn.misection.autoreport.databinding.ActivityWebViewBinding;
import cn.misection.autoreport.entity.ReportInfo;
import cn.misection.autoreport.util.jsutil.InfoToJsCompiler;
import cn.misection.util.oututil.system.AppSystem;

/**
 * @author Administrator
 */
public class WebViewActivity extends AppCompatActivity {

    private ActivityWebViewBinding mBinding;

    private String mScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();
    }

    private void init() {
        initBinding();
        initScript();
        initWebViewSetting();
        initPage();
    }

    private void initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
    }

    private void initScript() {
        ReportInfo reportInfo = (ReportInfo) getIntent().getExtras().getSerializable(getString(R.string.activity_param_key));
        mScript = new InfoToJsCompiler(reportInfo).getScript();
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

            private int enterCount = 0;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ++enterCount;
                if (view.getUrl().equals(WebViewActivity.this.getString(R.string.report_submit_url))) {
                    if (BuildConfig.DEBUG) {
                        AppSystem.out.printt(WebViewActivity.this, "report branch");
                    }
                    evalSelectJs();
                } else if (view.getUrl().contains(WebViewActivity.this.getString(R.string.swufe_auth_keyword))) {
                    switch (enterCount) {
                        case 1:
                            alertDefault(WebViewActivity.this.getString(R.string.swufe_login_page_prompt));
                            break;
                        default:
                            alertDefault(WebViewActivity.this.getString(R.string.swufe_reenter_login_page_prompt));
                            break;
                    }
                } else {
                    if (BuildConfig.DEBUG) {
                        AppSystem.out.printt(WebViewActivity.this, "else branch");
                        AppSystem.out.printt(WebViewActivity.this, view.getUrl());
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
        });
    }

    private void initPage() {
        mBinding.reportWebView.loadUrl(getString(R.string.report_submit_url));
    }

    private void alertDefault(String msg) {
        new AlertDialog.Builder(WebViewActivity.this)
                .setTitle(getString(R.string.tips))
                .setMessage(msg)
                .setIcon(R.mipmap.ic_my_launcher_round)
                .setPositiveButton(getString(R.string.sure),
                        (dialog, which) -> AppSystem.out.prints(mBinding.reportWebView,
                                getString(R.string.swufe_login_snackbar_prompt)))
                .create()
                .show();
    }

    private void evalSelectJs() {
        mBinding.reportWebView.evaluateJavascript(
                mScript,
                value -> {
                    if (BuildConfig.DEBUG) {
                        AppSystem.out.printt(WebViewActivity.this, value);
                    }
                }
        );
    }
}
package kaola.zhanchengguo.com.kaola.other.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.JumpManager;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * 浏览器页面
 * Created by Administrator on 2016/6/15.
 */
public class WebActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private WebView webView;

    private TextView tvTitle;

    private WebViewClient viewClient = new WebViewClient()
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            LogUtil.w("url = " + url);
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            //页面加载完成
            //设置页面标题
            String title = view.getTitle();

            tvTitle.setText(title);
        }
    };

    private WebChromeClient chromeClient = new WebChromeClient()
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if(newProgress == 100)
            {
                progressBar.setVisibility(View.INVISIBLE);
            }else
            {
                progressBar.setProgress(newProgress);
            }

        }
    };

    @SuppressLint("AddJavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);

        progressBar = (ProgressBar) findViewById(R.id.web_pb);
        webView = (WebView) findViewById(R.id.web_content_wv);
        tvTitle = (TextView) findViewById(R.id.web_title_tv);

        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(viewClient);

        WebSettings settings = webView.getSettings();
        //允许webView运行脚本语言
        settings.setJavaScriptEnabled(true);

        //添加javaScript回调java代码接口
        webView.addJavascriptInterface(new KaolaWebInterface(),"kaola");

        //获取从其他页面传过来的url
        String url = getIntent().getStringExtra(JumpManager.TAG_URL);
        LogUtil.w("url = " + url);
        webView.loadUrl(url);
    }


    /**
     * javascript要访问的java类
     */
    class KaolaWebInterface
    {
        public void setTitle(String title)
        {
            if(tvTitle != null)
            {
                tvTitle.setText(title);
            }
        }
    }
}

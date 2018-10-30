package com.shanghai.agentwebjs.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.shanghai.agentwebjs.AndroidInterface;
import com.shanghai.agentwebjs.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main3Activity extends AppCompatActivity {
    AgentWeb mAgentWeb;
    LinearLayout mLlWeb;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   if(Build.VERSION.SDK_INT>20){
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }*/
        setContentView(R.layout.activity_main);

   // }
        mLlWeb=findViewById(R.id.ll_brand);
        textView=findViewById(R.id.tv_onclick);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLlWeb, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator(ContextCompat.getColor(this,R.color.black),2)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .createAgentWeb()
                .ready()
                .go("file:///android_asset/javascripts.html");

        if(mAgentWeb!=null){
            //注入对象
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidInterface(mAgentWeb,this));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgentWeb.getJsAccessEntrace().quickCallJs("callAndroid");

            }
        });
        //mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidInterface(mAgentWeb,this));

    }
    private WebViewClient mWebViewClient=new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 步骤2：根据协议的参数，判断是否是所需要的url
            // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
            //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
            Uri uri = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                uri = Uri.parse(url);
            }
            Log.i("fengle", "1");
            // 如果url的协议 = 预先约定的 js 协议
            // 就解析往下解析参数
            if (uri.getScheme().equals("js")) {
                Log.i("fengle", "2");
                // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                // 所以拦截url,下面JS开始调用Android需要的方法
                if (uri.getAuthority().equals("webview")) {
                    Toast.makeText(Main3Activity.this, "HTML open Android native App success!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setData(uri);
                    startActivity(intent);
                    //uri = Uri.parse(intent.getData().toString());
                    Log.i("fengle", uri.toString());
                    //  步骤3：
                    // 执行JS所需要调用的逻辑

                    // 可以在协议上带有参数并传递到Android上
                    HashMap<String, String> params = new HashMap<>();
                    Set<String> collection = uri.getQueryParameterNames();
                    List list = new ArrayList(collection);
                    for (int i = 0; i < list.size(); i++) {
                        String value = uri.getQueryParameter(list.get(i).toString());
                        Log.i("fengle", value);
                    }

                    //  String value = uri.getQueryParameter()

                }

                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest url) {
            // 步骤2：根据协议的参数，判断是否是所需要的url
            // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
            //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
            Uri uri = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                uri = Uri.parse(url.getUrl().toString());
            }
            Log.i("fengle", "1");
            // 如果url的协议 = 预先约定的 js 协议
            // 就解析往下解析参数
            if (uri.getScheme().equals("js")) {
                Log.i("fengle", "2");
                // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                // 所以拦截url,下面JS开始调用Android需要的方法
                if (uri.getAuthority().equals("webview")) {
                    Toast.makeText(Main3Activity.this, "HTML open Android native App success!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setData(uri);
                    startActivity(intent);
                    //uri = Uri.parse(intent.getData().toString());
                    Log.i("fengle", uri.toString());
                    //  步骤3：
                    // 执行JS所需要调用的逻辑

                    // 可以在协议上带有参数并传递到Android上
                    HashMap<String, String> params = new HashMap<>();
                    Set<String> collection = uri.getQueryParameterNames();
                    List list = new ArrayList(collection);
                    for (int i = 0; i < list.size(); i++) {
                        String value = uri.getQueryParameter(list.get(i).toString());
                        Log.i("fengle", value);
                    }

                    //  String value = uri.getQueryParameter()

                }

                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }
    };
    private WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
        }
    };
}

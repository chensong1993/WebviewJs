package com.shanghai.agentwebjs.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanghai.agentwebjs.R;
import com.shanghai.agentwebjs.click.SingleClick;
import com.shanghai.agentwebjs.log.AutoLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main2Activity extends AppCompatActivity {
    WebView mWebView;
    TextView mTextView;
    int i, j = 0;
    ImageView imageView;
    private boolean isTwitterChecked = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // imageView = findViewById(R.id.img_anim);
        Drawable drawable = imageView.getDrawable();
        ((Animatable) drawable).start();
       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                if (Build.VERSION.SDK_INT > 20) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Main2Activity.this,
                            new Pair[]{Pair.create(imageView, "fab")})
                            .toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });*/
        //  onTwitterClick(imageView);

    }

    public void startAnim() {

    }

   /* public void onTwitterClick(View view) {
        isTwitterChecked = !isTwitterChecked;
        final int[] stateSet = {android.R.attr.state_checked * (isTwitterChecked ? 1 : -1)};
        imageView.setImageState(stateSet, true);
    }*/

        /*mWebView = findViewById(R.id.wv_view);
        mTextView = findViewById(R.id.tv_onclick);
        ssss();
        mTextView.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
                Log.i("singleclick","普通"+i);
            }
        });

     //   Log.i("singleclick","普通"+i);
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 步骤1：加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/hx.html");
        mWebView.addJavascriptInterface(new com.shanghai.agentwebjs.JavascriptInterface(this), "imagelistener");

// 复写WebViewClient类的shouldOverrideUrlLoading方法
        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest url) {
                                          // 步骤2：根据协议的参数，判断是否是所需要的url
                                          // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                                          //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                                          Uri uri = null;
                                          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                              uri = Uri.parse(url.getUrl().toString());
                                          }
                                          // 如果url的协议 = 预先约定的 js 协议
                                          // 就解析往下解析参数
                                          if (uri.getScheme().equals("xp")) {

                                              // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                                              // 所以拦截url,下面JS开始调用Android需要的方法
                                              if (uri.getAuthority().equals("detail")) {
                                                  Toast.makeText(Main2Activity.this, "HTML open Android native App success!", Toast.LENGTH_LONG).show();
                                                  Intent intent = new Intent();
                                                  intent.setData(uri);
                                                  startActivity(intent);
                                                 *//* //  步骤3：
                                                  // 执行JS所需要调用的逻辑

                                                  // 可以在协议上带有参数并传递到Android上
                                                  mWebView.loadUrl("javascript:callAndroid("+"12132132132132132"+")");
                                                  HashMap<String, String> params = new HashMap<>();
                                                  Set<String> collection = uri.getQueryParameterNames();
                                                  List list = set2List(collection);
                                                  for (int i = 0; i < list.size(); i++) {
                                                      String value = uri.getQueryParameter(list.get(i).toString());
                                                      Log.i("fengle", value);
                                                  }*//*

                                                  //  String value = uri.getQueryParameter()

                                              }

                                              return true;
                                          }
                                          return super.shouldOverrideUrlLoading(view, url);
                                      }
            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                // html加载完成之后，添加监听图片的点击js函数
                addImageClickListener(view);
            }
                                  }
        );

    }

    private void addImageClickListener(WebView webView) {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistener.openImage(this.src);  " +//通过js代码找到标签为img的代码块，设置点击的监听方法与本地的openImage方法进行连接
                "    }  " +
                "}" +
                "})()");
    }

    @AutoLog(declaredPermission="android.permission.READ_PHONE_STATE")
    public void ssss(){
       // ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

        mTextView.setText("1231345");
    }
    public static List<String> set2List(Set<String> stringSet) {
        if (stringSet == null) {
            return null;
        }
        return new ArrayList<>(stringSet);
    }*/


}

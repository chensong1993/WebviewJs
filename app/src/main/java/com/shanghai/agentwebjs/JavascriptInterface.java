package com.shanghai.agentwebjs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * @author chensong
 * @date 2018/10/19 14:54
 */
public class JavascriptInterface {
    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        //
        /*Intent intent = new Intent();
        intent.putExtra("image", img);
        intent.setClass(context, NewsPhotoActivity.class);
        context.startActivity(intent);*/
        Log.i("image",img);
    }

}

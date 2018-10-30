package com.shanghai.agentwebjs.log;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.shanghai.agentwebjs.app.App;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author chensong
 * @date 2018/10/16 10:01
 */
@Aspect
public class LogAspect {

    static final String TAG = "logAspect";

        @Pointcut("execution(@AutoLog public * *..*.*(..)) && @annotation(ann)")
        public void checkPermssion(AutoLog ann){};

    /*
    接下来是advice，advice的真正功能由check函数来实现，这个check函数第二个参数就是我们想要
    的注解。在实际运行过程中，AspectJ会把这个信息从JPoint中提出出来并传递给check函数。
    */
        @Before("checkPermssion(securityCheckAnnotation)")
        public void check(JoinPoint joinPoint,AutoLog securityCheckAnnotation) {

            //从注解信息中获取声明的权限。
            String neededPermission = securityCheckAnnotation.declaredPermission();
            //使用兼容库就无需判断系统版本
            int hasWriteStoragePermission = ContextCompat.checkSelfPermission(App.getInstance(), neededPermission);
            if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
                //拥有权限，执行操作
                Log.i(TAG, joinPoint.toShortString());
            }else{
                Activity activity = (Activity) joinPoint.getTarget();
                Log.i(TAG, "needed permission is " + neededPermission);
                //没有权限，向用户请求权限
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);

            }

    }



}

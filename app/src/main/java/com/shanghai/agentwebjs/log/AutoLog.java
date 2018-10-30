package com.shanghai.agentwebjs.log;

import android.app.Activity;
import android.util.Log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chensong
 * @date 2018/10/16 10:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoLog {

     String declaredPermission();  //declarePermssion是一个函数，其实代表了注解里的参数
//为checkPhoneState使用SecurityCheckAnnotation注解，并指明调用该函数的人需要声明的权限
}

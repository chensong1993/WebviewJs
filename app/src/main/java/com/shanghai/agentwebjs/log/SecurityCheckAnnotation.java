package com.shanghai.agentwebjs.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chensong
 * @date 2018/10/16 11:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecurityCheckAnnotation {
    public String declaredPermission();  //declarePermssion是一个函数，其实代表了注解里的参数

}

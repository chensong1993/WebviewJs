package com.shanghai.agentwebjs.click;

import android.util.Log;
import android.view.View;

import com.shanghai.agentwebjs.log.AutoLog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author chensong
 * @date 2018/10/15 17:30
 */
@Aspect
public class SingleClickAspect {
    private static final long DEFAULT_TIME_INTERVAL = 5000;
    int i,j=0;
    /**
     * 定义切点，标记切点为所有被@SingleClick注解的方法
     * 注意：这里me.baron.test.annotation.SingleClick需要替换成
     * 你自己项目中SingleClick这个类的全路径哦
     */
    @Pointcut("execution(@com.shanghai.agentwebjs.click.SingleClick * *(..))&& @annotation(ann)")
    public void methodAnnotated(SingleClick ann) {}

    /**
     * 定义一个切面方法，包裹切点方法
     */
    @Around("methodAnnotated(singleClick)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint,SingleClick singleClick) throws Throwable {
        // 取出方法的参数
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }
        }
        if (view == null) {
            return;
        }
      /*  // 取出方法的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(SingleClick.class)) {
            return;
        }*/
        //SingleClick singleClick = method.getAnnotation(SingleClick.class);
        // 判断是否快速点击
        if (!XClickUtil.isFastDoubleClick(view, singleClick.value())) {
            // 不是快速点击，执行原方法
            joinPoint.proceed();
            Log.i("clicksingle",i+++"");
        }else {
            Log.i("clicksingle","  "+j++);
        }
    }

}

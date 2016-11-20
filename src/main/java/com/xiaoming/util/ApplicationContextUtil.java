package com.xiaoming.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaoming on 19/11/2016.
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static final Map currentContextPerThread = new ConcurrentHashMap(1);
    private static ApplicationContext applicationContext;

    public ApplicationContextUtil() {
    }

    public static synchronized ApplicationContext getApplicationContext() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(currentContextPerThread.containsKey(classLoader)) {
            return (ApplicationContext)currentContextPerThread.get(classLoader);
        } else {
            putApplicationContext(applicationContext);
            return applicationContext;
        }
    }

    public static synchronized void putApplicationContext(ApplicationContext context) {
        if(context != null) {
            currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), context);
        }

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    private static final void setContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T)getApplicationContext().getBean(beanName);
    }
}

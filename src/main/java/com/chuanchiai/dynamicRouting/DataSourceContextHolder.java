package com.chuanchiai.dynamicRouting;

/**
 * Created by chuanchiai on 2020/7/18
 */
public class DataSourceContextHolder {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();
    public static void set(String sourceType){
        threadLocal.set(sourceType);
    }
    public static String get(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}

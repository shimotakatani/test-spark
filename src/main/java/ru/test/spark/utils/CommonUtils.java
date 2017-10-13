package ru.test.spark.utils;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class CommonUtils {
    private CommonUtils(){}

    public static boolean isNotNull(Object obj){
        return obj != null;
    }

    public static boolean isNotNullOrEmpty(String s){
        return s != null && s.length() > 0;
    }
}

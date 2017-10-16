package ru.test.spark.utils;

import java.util.Collection;

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

    public static boolean isNotNullOrEmpty(Collection s){
        return s != null && s.size() > 0;
    }
}

package ru.test.spark.utils;


import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * Для работы с фильтрами
 * create time 16.10.2017
 *
 * @author nponosov
 */
public class FilterUtils {

    public static String getAndOption(String entityName, String fieldName, Object field){

        if (isNotNull(field)){
            if ( field instanceof Long || field instanceof Integer){
                return " AND entity." + fieldName + " = " + field.toString() + " ";
            }
            if ( field instanceof String){
                return " AND entity." + fieldName + " like '%" + field + "%' ";
            }
            if ( field instanceof UUID && "id".equals(fieldName)){
                return " AND entity." + fieldName + " = '" + field.toString() + "' ";
            }
            if ( field instanceof UUID){
                return " AND entity." + fieldName + ".id = '" + field.toString() + "' ";
            }



            //default
            if ( field instanceof Object){
                return " AND " + entityName + "." + fieldName + " = :" + fieldName + " ";
            }
        }

        return "";
    }

}

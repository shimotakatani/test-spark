package ru.test.spark.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.test.spark.filters.AbstractFilter;

/**
 * create time 16.10.2017
 *
 * @author nponosov
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static final String getFilteredResponse(String data, AbstractFilter filter){
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("data:[")
                .append(data)
                .append("],limit:")
                .append(filter.getLimit())
                .append(",page:")
                .append(filter.getPage())
                .append(",start:")
                .append(filter.getStart())
                .append("}");

        return sb.toString();
    }

}

package ru.test.spark.dto;

import java.util.List;

/**
 * create time 16.10.2017
 *
 * @author nponosov
 */
public class ListDto {

    private List data;

    private Long limit;

    private Long page;

    private Long start;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }
}

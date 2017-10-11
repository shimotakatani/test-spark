package ru.test.spark.filters;

import ru.test.spark.enums.EntityStatusEnum;

/**
 * Абстрактный фильтр для запросов
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class AbstractFilter {

    private Long limit;
    private Long page;
    private Long start;
    private Long countOnPage;
    private Long id;
    private Long updateTime;
    private Long createTime;
    private EntityStatusEnum status;

    protected AbstractFilter(){

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

    public Long getCountOnPage() {
        return countOnPage;
    }

    public void setCountOnPage(Long countOnPage) {
        this.countOnPage = countOnPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public EntityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EntityStatusEnum status) {
        this.status = status;
    }
}

package ru.test.spark.filters;

import ru.test.spark.enums.EntityStatusEnum;

import javax.persistence.Query;
import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * Абстрактный фильтр для запросов
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class AbstractFilter {

    protected String entityName;

    private Long limit;
    private Long page;
    private Long start;
    private Long countOnPage;
    private UUID id;
    private Long updateTime;
    private Long createTime;
    private EntityStatusEnum status;

    protected AbstractFilter(){

    }

    public String getEntityName() {
        return entityName;
    }

    protected void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getWhereLimitOrderString(){
        return ""; //todo пока не понятно, какую часть построител запроса можно будет сюда достать, возможно придётся разделить метод
    }

    public void normalizeLimits(){
        if ( ! isNotNull(this.getLimit())){ this.setLimit(100L); }
        if ( ! isNotNull(this.getPage())){ this.setPage(1L); }
        if ( ! isNotNull(this.getStart())){ this.setStart(0L); }
    }

    public void setLimitOption(Query query){

        if (isNotNull(this.getLimit())){
            query.setMaxResults( this.getLimit().intValue());
        }
        if (isNotNull(this.getStart())){
            query.setFirstResult(this.getStart().intValue());
        }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

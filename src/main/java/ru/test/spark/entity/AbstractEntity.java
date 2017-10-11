package ru.test.spark.entity;

import org.bson.types.ObjectId;
import ru.test.spark.enums.EntityStatusEnum;

/**
 * Абстрактная сущность
 * create time 11.10.2017
 *
 * @author nponosov
 */
public abstract class AbstractEntity {

    private ObjectId id;
    private Long createTime;
    private Long updateTime;
    private EntityStatusEnum status;
    protected static String collectionName;

    protected AbstractEntity(){

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public EntityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EntityStatusEnum status) {
        this.status = status;
    }
}

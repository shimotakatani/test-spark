package ru.test.spark.dto;

import org.bson.types.ObjectId;
import ru.test.spark.enums.EntityStatusEnum;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public abstract class AbstractDto {
    private Long id;
    private ObjectId objectId;
    private Long createTime;
    private Long updateTime;
    private EntityStatusEnum status;

    protected AbstractDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
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

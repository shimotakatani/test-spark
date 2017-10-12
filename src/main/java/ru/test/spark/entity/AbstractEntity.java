package ru.test.spark.entity;

import org.bson.types.ObjectId;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.enums.EntityStatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Абстрактная сущность
 * create time 11.10.2017
 *
 * @author nponosov
 */
@Entity
public abstract class AbstractEntity {

    @Id
    @Column(name = CollectionsConst.Collections.Abstract.ID)
    private UUID id;

    @Column(name = CollectionsConst.Collections.Abstract.CREATE_TIME)
    private Long createTime;

    @Column(name = CollectionsConst.Collections.Abstract.UPDATE_TIME)
    private Long updateTime;

    @Column(name = CollectionsConst.Collections.Abstract.STATUS)
    private EntityStatusEnum status;

    protected AbstractEntity(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

package ru.test.spark.dto;

import org.bson.types.ObjectId;
import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.enums.EntityStatusEnum;

import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * Абстрактное DTO
 * create time 11.10.2017
 *
 * @author nponosov
 */
public abstract class AbstractDto {
    private UUID id;
    private Long createTime;
    private Long updateTime;
    private EntityStatusEnum status;
    //сюда бы ещё юзера логировать, но так как мы без авторизации....

    protected AbstractDto(){}

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

    public void generateDtoFromEntity(AbstractEntity sourceEntity){
        if (isNotNull(sourceEntity)) {
            this.setId(sourceEntity.getId());
            this.setCreateTime(sourceEntity.getCreateTime());
            this.setUpdateTime(sourceEntity.getUpdateTime());
            this.setStatus(sourceEntity.getStatus());
        }
    }

    public void generateEntityFromDto(AbstractEntity destinationEntity){
        if (isNotNull(destinationEntity)){
            if(isNotNull(this.getId())) destinationEntity.setId(this.getId());
            if(isNotNull(this.getCreateTime())) destinationEntity.setCreateTime(this.getCreateTime());
            if(isNotNull(this.getUpdateTime())) destinationEntity.setUpdateTime(this.getUpdateTime());
            if(isNotNull(this.getStatus())) destinationEntity.setStatus(this.getStatus());
        }
    }
}

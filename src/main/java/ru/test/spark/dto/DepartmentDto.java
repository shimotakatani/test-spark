package ru.test.spark.dto;

import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.entity.DepartmentEntity;

import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DepartmentDto extends AbstractDto{

    private String name;

    @Override
    public void generateDtoFromEntity(AbstractEntity sourceEntity){
        super.generateDtoFromEntity(sourceEntity);
        if (isNotNull(sourceEntity) && sourceEntity instanceof DepartmentEntity){
            DepartmentEntity departmentEntity = (DepartmentEntity) sourceEntity;
            this.setName(departmentEntity.getName());
        }
    }

    @Override
    public void generateEntityFromDto(AbstractEntity destinationEntity) {
        super.generateEntityFromDto(destinationEntity);
        if (isNotNull(destinationEntity) && destinationEntity instanceof DepartmentEntity){
            DepartmentEntity departmentEntity = (DepartmentEntity) destinationEntity;
            if (isNotNull(this.getName())) departmentEntity.setName(this.getName());
        }
    }

    public DepartmentDto(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

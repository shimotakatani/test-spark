package ru.test.spark.enums;

import ru.test.spark.utils.CommonUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Перечисление для статуса сущности
 * create time 11.10.2017
 *
 * @author nponosov
 */
public enum EntityStatusEnum {

    ACTIVE(1,"Активная запись"),
    DELETED(2, "Удалённая запись");

    private Integer id;
    private String name;

    EntityStatusEnum(Integer id, String name){
        this.id = id;
        this.name = name;
    }



    @Converter
    public static class EntityStatusEnumConverter implements AttributeConverter<EntityStatusEnum, String> {

        @Override
        public String convertToDatabaseColumn(EntityStatusEnum entityStatusEnum) {
            return entityStatusEnum == null ? null : entityStatusEnum.toString();
        }

        @Override
        public EntityStatusEnum convertToEntityAttribute(String caption) {

            return CommonUtils.isNotNullOrEmpty(caption) ? EntityStatusEnum.valueOf(caption) : EntityStatusEnum.ACTIVE;
        }
    }
}

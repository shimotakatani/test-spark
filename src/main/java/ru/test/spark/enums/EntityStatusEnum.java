package ru.test.spark.enums;

/**
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
}

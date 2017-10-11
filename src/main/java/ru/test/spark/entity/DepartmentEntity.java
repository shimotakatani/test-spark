package ru.test.spark.entity;

/**
 * Сущность Отдел
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DepartmentEntity extends AbstractEntity {

    private String name;

    public DepartmentEntity(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

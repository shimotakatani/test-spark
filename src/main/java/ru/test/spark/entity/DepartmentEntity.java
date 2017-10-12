package ru.test.spark.entity;

import ru.test.spark.consts.CollectionsConst;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Сущность Отдел
 * create time 11.10.2017
 *
 * @author nponosov
 */
@Entity
@Table(name = CollectionsConst.Collections.Department.COLLECTION_NAME)
public class DepartmentEntity extends AbstractEntity {

    @Column(name = CollectionsConst.Collections.Department.NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

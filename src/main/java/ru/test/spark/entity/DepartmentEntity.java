package ru.test.spark.entity;

import ru.test.spark.consts.CollectionsConst;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "department", cascade= CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<UserEntity> worker;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

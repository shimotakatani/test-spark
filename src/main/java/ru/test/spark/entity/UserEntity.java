package ru.test.spark.entity;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.dto.UserDto;

import javax.persistence.*;

/**
 * Сущность Пользователь
 * create time 11.10.2017
 *
 * @author nponosov
 */
@Entity
@Table(name = CollectionsConst.Collections.Users.COLLECTION_NAME)
public class UserEntity extends AbstractEntity {

    @Column(name = CollectionsConst.Collections.Users.FIO)
    private String fio;

    @Column(name = CollectionsConst.Collections.Users.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = CollectionsConst.Collections.Users.EMAIL)
    private String email;

    @ManyToOne
    @JoinColumn(name = CollectionsConst.Collections.Users.CHEEF)
    private UserEntity cheef;

    @ManyToOne
    @JoinColumn(name = CollectionsConst.Collections.Users.DEPARTMENT)
    private DepartmentEntity department;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity getCheef() {
        return cheef;
    }

    public void setCheef(UserEntity cheef) {
        this.cheef = cheef;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}

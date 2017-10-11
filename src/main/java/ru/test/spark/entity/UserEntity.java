package ru.test.spark.entity;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.dto.UserDto;

/**
 * Сущность Пользователь
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class UserEntity extends AbstractEntity {

    private String fio;
    private String phoneNumber;
    private String email;
    private UserEntity cheef;
    private DepartmentEntity department;

    public UserEntity(){
        UserEntity.collectionName = CollectionsConst.Collections.Users.COLLECTION_NAME;
    }

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

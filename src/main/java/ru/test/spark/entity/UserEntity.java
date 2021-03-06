package ru.test.spark.entity;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.dto.UserDto;
import ru.test.spark.utils.CommonUtils;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = CollectionsConst.Collections.Users.CHEEF)
    private UserEntity cheef;

    @OneToMany(mappedBy = "cheef", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<UserEntity> worker;

    @ManyToOne(cascade = CascadeType.ALL)
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("{ id:").append(this.getId());
        if (CommonUtils.isNotNull(this.getCreateTime()))  sb.append(", createTime:").append(this.getCreateTime());
        if (CommonUtils.isNotNull(this.getUpdateTime()))  sb.append(", updateTime").append(this.getUpdateTime());
        if (CommonUtils.isNotNull(this.getStatus()))  sb.append(", status:").append(this.getStatus().toString());
        if (CommonUtils.isNotNull(this.getFio()))    sb.append(", fio:").append(this.getFio());
        if (CommonUtils.isNotNull(this.getPhoneNumber()))    sb.append(", phoneNumber:").append(this.getPhoneNumber());
        if (CommonUtils.isNotNull(this.getEmail()))    sb.append(", email:").append(this.getEmail());
        sb.append("}");

        return sb.toString();
    }
}

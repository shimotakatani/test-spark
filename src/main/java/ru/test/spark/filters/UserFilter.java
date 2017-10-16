package ru.test.spark.filters;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dto.UserDto;

import java.lang.reflect.Field;
import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;
import static ru.test.spark.utils.FilterUtils.getAndOption;

/**
 * Фильрт для сущности Пользователь
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class UserFilter extends AbstractFilter{

    private String fio;
    private String phoneNumber;
    private String email;
    private UUID cheefId;
    private UUID departmentId;

    public UserFilter(){
        this.setEntityName(CollectionsConst.Entity.Users.ENTITY_NAME);
    }

    @Override
    public String getWhereLimitOrderString() {

        StringBuilder resultString = new StringBuilder();

//        UserDto newFilter = new UserDto();
//        newFilter.setFio("Fcgfg");
//        Field[] fields = newFilter.getClass().getFields();
//        for (Field field : fields) {
//            try {
//                resultString.append(field.getName()).append(": ").append(field.get(this));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }

        if(isNotNull(this.getId())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Abstract.ID, this.getId()));
        }
        if(isNotNull(this.getCreateTime())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Abstract.CREATE_TIME, this.getCreateTime()));
        }
        if(isNotNull(this.getUpdateTime())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Abstract.UPDATE_TIME, this.getUpdateTime()));
        }
        if(isNotNull(this.getStatus())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Abstract.STATUS, this.getStatus()));
        }

        if(isNotNull(this.getFio())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Users.FIO, this.getFio()));
        }
        if(isNotNull(this.getEmail())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Users.EMAIL, this.getEmail()));
        }
        if(isNotNull(this.getPhoneNumber())){
            resultString.append(getAndOption(CollectionsConst.Entity.Users.ENTITY_NAME, CollectionsConst.Entity.Users.PHONE_NUMBER, this.getPhoneNumber()));
        }
        //todo пока с вложенными объектами не решено

        return resultString.toString();
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

    public UUID getCheefId() {
        return cheefId;
    }

    public void setCheefId(UUID cheefId) {
        this.cheefId = cheefId;
    }

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }
}

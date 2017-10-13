package ru.test.spark.dto;

import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.utils.CommonUtils;

import static ru.test.spark.utils.CommonUtils.isNotNull;

/**
 * Dto для сущности пользователя
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class UserDto extends AbstractDto {

    private String fio;
    private String phoneNumber;
    private String email;
    private UserDto cheef;
    private DepartmentDto department;

    public UserDto(){
       super();
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

    public UserDto getCheef() {
        return cheef;
    }

    public void setCheef(UserDto cheef) {
        this.cheef = cheef;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    @Override
    public void generateDtoFromEntity(AbstractEntity sourceEntity){
        super.generateDtoFromEntity(sourceEntity);
        if (isNotNull(sourceEntity) && sourceEntity instanceof UserEntity){
            UserEntity userEntity = (UserEntity) sourceEntity;
            this.setPhoneNumber(userEntity.getPhoneNumber());
            this.setFio(userEntity.getFio());
            this.setEmail(userEntity.getEmail());

            //TODO потом дописать вложенные сущности(как их передавать?)
        }
    }

    @Override
    public void generateEntityFromDto(AbstractEntity destinationEntity) {
        super.generateEntityFromDto(destinationEntity);
        if (isNotNull(destinationEntity) && destinationEntity instanceof UserEntity){
            UserEntity userEntity = (UserEntity) destinationEntity;
            if (isNotNull(this.getFio())) userEntity.setFio(this.getFio());
            if (isNotNull(this.getEmail())) userEntity.setEmail(this.getEmail());
            if (isNotNull(this.getPhoneNumber())) userEntity.setPhoneNumber(this.getPhoneNumber());
        }
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

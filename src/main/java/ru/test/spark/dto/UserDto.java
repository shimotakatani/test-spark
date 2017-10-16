package ru.test.spark.dto;

import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.utils.CommonUtils;

import java.util.UUID;

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
    private UUID cheefId;
    private String cheefFio;
    private UUID departmentId;
    private String departmentName;

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

    public String getCheefFio() {
        return cheefFio;
    }

    public void setCheefFio(String cheefFio) {
        this.cheefFio = cheefFio;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public void generateDtoFromEntity(AbstractEntity sourceEntity){
        super.generateDtoFromEntity(sourceEntity);
        if (isNotNull(sourceEntity) && sourceEntity instanceof UserEntity){
            UserEntity userEntity = (UserEntity) sourceEntity;
            this.setPhoneNumber(userEntity.getPhoneNumber());
            this.setFio(userEntity.getFio());
            this.setEmail(userEntity.getEmail());

            if (isNotNull(userEntity.getCheef())) {
                this.setCheefId(userEntity.getCheef().getId());
                this.setCheefFio(userEntity.getCheef().getFio());
                //тут потом можно добавить поля от вложенного объекта
            }

            if (isNotNull(userEntity.getDepartment())) {
                this.setDepartmentId(userEntity.getDepartment().getId());
                this.setDepartmentName(userEntity.getDepartment().getName());
                //тут потом можно добавить поля от вложенного объекта
            }


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

            //тут не получится привязать к сущности вложенные объекты
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

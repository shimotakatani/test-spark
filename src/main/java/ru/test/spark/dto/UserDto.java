package ru.test.spark.dto;

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
}

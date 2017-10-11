package ru.test.spark.dto;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DepartmentDto extends AbstractDto{

    private String name;

    public DepartmentDto(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

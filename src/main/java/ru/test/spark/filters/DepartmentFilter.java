package ru.test.spark.filters;

import ru.test.spark.dto.DepartmentDto;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DepartmentFilter {

    private String name;

    public DepartmentFilter(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

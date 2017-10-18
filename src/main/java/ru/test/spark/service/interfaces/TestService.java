package ru.test.spark.service.interfaces;

import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.dto.UserDto;
import ru.test.spark.entity.UserEntity;

/**
 * create time 12.10.2017
 *
 * @author nponosov
 */
public interface TestService {

    UserEntity newRandomUser();

    UserDto newRandomUserDto();

    DepartmentDto newRandomDepartmentDto();

    void generateManyUsersAndDepartments();
}

package ru.test.spark.service.impl;

import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.UserDto;
import ru.test.spark.filters.UserFilter;
import ru.test.spark.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Сервис пользователей
 * create time 13.10.2017
 *
 * @author nponosov
 */
@Stateless
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoSessionImpl();

    private DepartmentDao departmentDao = new DepartmentDaoImpl();


    @Override
    public UserDto getUserById(UUID id) {
        UserDto userDto = new UserDto();
        userDto.generateDtoFromEntity(userDao.getById(id));
        return userDto;
    }

    @Override
    public void deleteUserById(UUID id) {

    }

    @Override
    public List<UserDto> getUserDtoList(UserFilter filter) {
        return null;
    }

    @Override
    public List<UserDto> getUserDtoList() {
        return null;
    }

    @Override
    public Long getUserDtoListCount(UserFilter filter) {
        return null;
    }

    @Override
    public Long getUserDtoListCount() {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto updateUserDto) {
        return null;
    }

    @Override
    public UserDto insertUser(UserDto newUserDto) {
        return null;
    }
}

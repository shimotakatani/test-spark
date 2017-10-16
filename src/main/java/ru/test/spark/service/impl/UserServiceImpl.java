package ru.test.spark.service.impl;

import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.UserDto;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.filters.UserFilter;
import ru.test.spark.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;
import static ru.test.spark.utils.CommonUtils.isNotNullOrEmpty;

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
        userDao.deleteById(id);
    }

    @Override
    public List<UserDto> getUserDtoList(UserFilter filter) {
        return convertEntitiesToDtos(userDao.getAllActive(filter));
    }

    @Override
    public List<UserDto> getUserDtoList(){ return convertEntitiesToDtos(userDao.getAllActive()); }

    @Override
    public Long getUserDtoListCount(UserFilter filter) {
        return userDao.getActiveCount(filter);
    }

    @Override
    public Long getUserDtoListCount() {
        return userDao.getActiveCount();
    }

    @Override
    public UserDto updateUser(UserDto updateUserDto) {

        UserDto userDto = null;
        UserEntity userEntity = userDao.getById(updateUserDto.getId());
        if (isNotNull(userEntity)){
            updateUserDto.generateEntityFromDto(userEntity);
            userDao.update(userEntity);
            userDto = new UserDto();
            userDto.generateDtoFromEntity(userEntity);
        }

        return userDto;
    }

    @Override
    public UserDto insertUser(UserDto newUserDto) {
        UserEntity newUserEntity = new UserEntity();
        if (isNotNull(newUserDto)){
            newUserDto.generateEntityFromDto(newUserEntity);
            newUserEntity = userDao.insert(newUserEntity);
            newUserDto.generateDtoFromEntity(newUserEntity);
            return newUserDto;
        }
        return null;
    }

    private List<UserDto> convertEntitiesToDtos(List<UserEntity> entityList){
        List<UserDto> returnUserList = new ArrayList<>();

        if (isNotNullOrEmpty(entityList)) {
            entityList.forEach(userEntity -> {
                UserDto userDto = new UserDto();
                userDto.generateDtoFromEntity(userEntity);
                returnUserList.add(userDto);
            });
        }

        return returnUserList;
    }
}

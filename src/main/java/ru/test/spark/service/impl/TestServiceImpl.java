package ru.test.spark.service.impl;

import ru.test.spark.consts.AppConst;
import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.dto.UserDto;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.service.interfaces.TestService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
@Stateless
@Local(TestService.class)
public class TestServiceImpl implements TestService {

    public UserDao userDao = new UserDaoSessionImpl();
    public DepartmentDao departmentDao = new DepartmentDaoImpl();

    public UserEntity newRandomUser(){
        UserEntity newUser = new UserEntity();
        newUser.setFio("FIO" + generateRandomString());
        newUser.setEmail(generateRandomString());
        newUser.setPhoneNumber("+7" + generateRandomString());
        Calendar cal = Calendar.getInstance();
        Long currentTime = cal.getTimeInMillis();
        newUser.setCreateTime(currentTime);
        newUser.setUpdateTime(currentTime);
        newUser.setStatus(EntityStatusEnum.ACTIVE);
        return newUser;
    }

    private String generateRandomString(){
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        Random randomNumber = new Random(cal.getTimeInMillis());
        for(int i = 0; i < 10; i++){
            sb.append(randomNumber.nextInt() % 10);
        }

        return sb.toString();
    }

    public UserDto newRandomUserDto(){
        UserDto newUser = new UserDto();
        Calendar cal = Calendar.getInstance();
        Long currentTime = cal.getTimeInMillis();
        newUser.setCreateTime(currentTime);
        newUser.setUpdateTime(currentTime);
        newUser.setStatus(EntityStatusEnum.ACTIVE);
        newUser.setFio("FIO" + generateRandomString());
        newUser.setEmail(generateRandomString());
        newUser.setPhoneNumber("+7" + generateRandomString());
        return newUser;
    }

    public DepartmentDto newRandomDepartmentDto(){
        DepartmentDto newUser = new DepartmentDto();
        Calendar cal = Calendar.getInstance();
        Long currentTime = cal.getTimeInMillis();
        newUser.setCreateTime(currentTime);
        newUser.setUpdateTime(currentTime);
        newUser.setStatus(EntityStatusEnum.ACTIVE);
        newUser.setName("Name" + generateRandomString());
        return newUser;
    }

    private DepartmentEntity newRandomDepartmentEntity(){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        Calendar cal = Calendar.getInstance();
        Long currentTime = cal.getTimeInMillis();
        departmentEntity.setCreateTime(currentTime);
        departmentEntity.setUpdateTime(currentTime);
        departmentEntity.setStatus(EntityStatusEnum.ACTIVE);
        departmentEntity.setName("Name" + generateRandomString());
        return departmentEntity;
    }

    @Override
    public void generateManyUsersAndDepartments() {

        for (int i = 0; i < AppConst.Test.DEPARTMENT_GENERATE_COUNT; i++){
            departmentDao.insert(newRandomDepartmentEntity());
        }
        List<DepartmentEntity> departmentEntityList = departmentDao.getAllActive();
        int departmentEntityListSize = departmentEntityList.size();
        Calendar cal = Calendar.getInstance();
        Random randomNumber = new Random(cal.getTimeInMillis());
        if (departmentEntityListSize > 0) {
            for (int j = 0; j < 1000; j++){
                UserEntity newUser = newRandomUser();
                newUser.setDepartment(departmentEntityList.get(Math.abs(randomNumber.nextInt()) % departmentEntityListSize));
                newUser.setCheef(null); //Генерим тех, у кого нет начальников
                userDao.insert(newUser);
            }

            List<UserEntity> userCheefEntityList = userDao.getAllActive();
            int userCheefEntityListSize = userCheefEntityList.size();
            if (userCheefEntityListSize > 0) {

                for (int j = 0; j < AppConst.Test.USER_GENERATE_COUNT; j++) {
                    UserEntity newUser = newRandomUser();
                    newUser.setDepartment(departmentEntityList.get(Math.abs(randomNumber.nextInt()) % departmentEntityListSize));
                    newUser.setCheef(userCheefEntityList.get(Math.abs(randomNumber.nextInt()) % userCheefEntityListSize));
                    userDao.insert(newUser);
                }
            }


        }
    }
}

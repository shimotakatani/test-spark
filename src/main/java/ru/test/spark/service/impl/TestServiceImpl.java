package ru.test.spark.service.impl;

import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.UserDto;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.service.interfaces.TestService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Random;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
@Stateless
@Local(TestService.class)
public class TestServiceImpl implements TestService {



    public UserEntity newRandomUser(){
        UserEntity newUser = new UserEntity();
        Random randomNumber = new Random(4);
        newUser.setFio("FIO" + generateRandomString());
        newUser.setEmail(generateRandomString());
        newUser.setPhoneNumber("+7" + generateRandomString());
        return newUser;
    }

    private String generateRandomString(){
        StringBuilder sb = new StringBuilder();
        Random randomNumber = new Random(4);
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
}

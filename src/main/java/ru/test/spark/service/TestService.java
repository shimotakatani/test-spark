package ru.test.spark.service;

import ru.test.spark.entity.UserEntity;

import java.util.Random;

/**
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class TestService {

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
            sb.append(randomNumber.nextInt());
        }

        return sb.toString();
    }
}

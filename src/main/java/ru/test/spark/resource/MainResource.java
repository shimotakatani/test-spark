package ru.test.spark.resource;

/**
 * Основной ресурс(пока тут спарк инициализируется)
 * create time 11.10.2017
 *
 * @author nponosov
 */

import org.apache.log4j.BasicConfigurator;
import ru.test.spark.dao.impl.UserDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.logger.SparkUtils;
import ru.test.spark.service.impl.TestServiceImpl;
import ru.test.spark.service.interfaces.TestService;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static spark.Spark.*;


public class MainResource {


    private static UserDao userDao = new UserDaoSessionImpl();
    private static TestService testService = new TestServiceImpl();
    public static Logger logger = Logger.getLogger(MainResource.class);

    public static void main(String[] args) {


        BasicConfigurator.configure();

        SparkUtils.createServerWithRequestLog(logger);
        port(4567);
        get("/hello", (req, res) -> createHelloMassage());
        get("/getAll", (req, res) -> getAllUsers());
        post("/insert", (req, res) -> generateData());
        post("/delete", (req, res) -> deleteUser());
        post("/update", (req, res) -> updateUser());

    }

    private static String createHelloMassage(){

        UserEntity userEntity = userDao.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-7bb9bd380a16"));
        String userPrint = userEntity == null ? "" : userEntity.toString();
        return "Hello World! We have " + userPrint + " users!!!\n Users is " + userDao.getAll().toString();
    }

    private static String generateData(){
        UserEntity newUser = testService.newRandomUser();
        //newUser.setId(UUID.randomUUID());
        userDao.insert(newUser);

        return newUser.toString();
    }

    private static String deleteUser(){
        String id = "8b1616c1-edcf-4450-aa44-19f26ef82b65";
        userDao.deleteById(UUID.fromString(id));
        return "Delete user by id " + id;
    }

    private static String getAllUsers(){
        StringBuilder sb = new StringBuilder();
        List<UserEntity> users = userDao.getAllActive();
        users.forEach(user -> sb.append(user.toString()).append("\n"));
        return sb.toString();
    }

    private static String updateUser(){
        List<UserEntity> users = userDao.getAllActive();
        Calendar cal = Calendar.getInstance();
        users.get(0).setCreateTime(cal.getTimeInMillis());
        UserEntity user = userDao.update(users.get(0));
        return "Update user with id = " + user.getId().toString() + " at " + user.getCreateTime();
    }


}


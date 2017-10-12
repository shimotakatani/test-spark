package ru.test.spark.resource;

/**
 * Основной ресурс(пока тут спарк инициализируется)
 * create time 11.10.2017
 *
 * @author nponosov
 */

import org.apache.log4j.BasicConfigurator;
import ru.test.spark.dao.impl.UserDaoImpl;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.logger.SparkUtils;
import ru.test.spark.service.impl.TestServiceImpl;
import ru.test.spark.service.interfaces.TestService;

import org.apache.log4j.Logger;

import java.util.UUID;

import static spark.Spark.*;


public class MainResource {


    private static UserDao userDao = new UserDaoImpl();
    private static TestService testService = new TestServiceImpl();
    public static Logger logger = Logger.getLogger(MainResource.class);

    public static void main(String[] args) {


        BasicConfigurator.configure();

        SparkUtils.createServerWithRequestLog(logger);
        port(4567);
        get("/hello", (req, res) -> createHelloMassage());
        post("/insert", (req, res) -> generateData());

    }

    private static String createHelloMassage(){

        UserEntity userEntity = userDao.getById(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-7bb9bd380a16"));
        String userPhone = userEntity == null ? "" : userEntity.getPhoneNumber();
        return "Hello World! We have " + userPhone + " users!!!\n Users is " + userDao.getAll().toString();
    }

    private static String generateData(){
        UserEntity newUser = testService.newRandomUser();
        userDao.insert(newUser);

        return newUser.toString();
    }


}


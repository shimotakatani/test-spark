package ru.test.spark.resource;


import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.UserDto;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.service.impl.DepartmentServiceImpl;
import ru.test.spark.service.impl.TestServiceImpl;
import ru.test.spark.service.impl.UserServiceImpl;
import ru.test.spark.service.interfaces.DepartmentService;
import ru.test.spark.service.interfaces.TestService;
import ru.test.spark.service.interfaces.UserService;

import javax.ejb.EJB;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static spark.Spark.*;

/**
 * Ресурс пользователей
 * create time 13.10.2017
 *
 * @author nponosov
 */
public class UserResource {

    private static UserDao userDao = new UserDaoSessionImpl();
    private static TestService testService = new TestServiceImpl();
    private static DepartmentDao departmentDao = new DepartmentDaoImpl();
    private static UserService userService = new UserServiceImpl();
    private static DepartmentService departmentService = new DepartmentServiceImpl();

    public static void publicResource(){
        //get(CollectionsConst.Collections.Users.COLLECTION_NAME + "/hello", (req, res) -> createHelloMassage());
        get("/" + CollectionsConst.Collections.Users.COLLECTION_NAME + "/getAll", (req, res) -> getAllUsers());
        get("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/getById", (req, res) -> getUser());
        get("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/filter", (req, res) -> getAllUsers());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/insert", (req, res) -> generateData());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/delete", (req, res) -> deleteUser());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/edit", (req, res) -> updateUser());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/setChef", (req, res) -> setChef());
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

    private static String getUser(){
        return userService.getUserById(UUID.fromString("bdffdc1a-583e-4b3f-94a0-227e3bb50a7f")).toString();
    }

    private static String updateUser(){
        List<UserEntity> users = userDao.getAllActive();
        Calendar cal = Calendar.getInstance();
        users.get(0).setCreateTime(cal.getTimeInMillis());
        UserEntity user = userDao.update(users.get(0));
        return "Update user with id = " + user.getId().toString() + " at " + user.getCreateTime();
    }

    private static String setChef(){
        List<UserEntity> users = userDao.getAllActive();
        List<DepartmentEntity> departments = departmentDao.getAllActive();
        Calendar cal = Calendar.getInstance();
        Long currentTime = cal.getTimeInMillis();
        users.get(0).setUpdateTime(currentTime);
        users.get(1).setUpdateTime(currentTime);
        users.get(0).setCheef(users.get(1));
        users.get(0).setDepartment(departments.get(0));
        UserEntity user = userDao.update(users.get(0));
        userDao.update(users.get(1));
        return "Update user with id = " + user.getId().toString() + " at " + user.getCreateTime();
    }
}

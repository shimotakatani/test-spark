package ru.test.spark.resource;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
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
 * Ресурс отделов
 * create time 13.10.2017
 *
 * @author nponosov
 */
public class DepartmentResource {

    private static UserDao userDao = new UserDaoSessionImpl();
    private static TestService testService = new TestServiceImpl();
    private static DepartmentDao departmentDao = new DepartmentDaoImpl();
    private static UserService userService = new UserServiceImpl();
    private static DepartmentService departmentService = new DepartmentServiceImpl();

    public static void publicResource(){

        get("/" + CollectionsConst.Collections.Department.COLLECTION_NAME + "/getAll", (req, res) -> getAllDepartments());
        get("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/getById", (req, res) -> getAllDepartments());
        get("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/filter", (req, res) -> getAllDepartments());
        post("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/insert", (req, res) -> generateData());
        post("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/delete", (req, res) -> deleteDepartment());
        post("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/edit", (req, res) -> updateDepartment());

    }


    private static String generateData(){
        UserEntity newUser = testService.newRandomUser();
        //newUser.setId(UUID.randomUUID());
        userDao.insert(newUser);

        return newUser.toString();
    }

    private static String deleteDepartment(){
        String id = "8b1616c1-edcf-4450-aa44-19f26ef82b65";
        userDao.deleteById(UUID.fromString(id));
        return "Delete user by id " + id;
    }

    private static String getAllDepartments(){
        StringBuilder sb = new StringBuilder();
        List<UserEntity> users = userDao.getAllActive();
        users.forEach(user -> sb.append(user.toString()).append("\n"));
        return sb.toString();
    }

    private static String updateDepartment(){
        List<UserEntity> users = userDao.getAllActive();
        Calendar cal = Calendar.getInstance();
        users.get(0).setCreateTime(cal.getTimeInMillis());
        UserEntity user = userDao.update(users.get(0));
        return "Update user with id = " + user.getId().toString() + " at " + user.getCreateTime();
    }

}

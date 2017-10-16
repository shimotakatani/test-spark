package ru.test.spark.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.consts.MessageConst;
import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.ListDto;
import ru.test.spark.dto.UserDto;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.filters.UserFilter;
import ru.test.spark.service.impl.DepartmentServiceImpl;
import ru.test.spark.service.impl.TestServiceImpl;
import ru.test.spark.service.impl.UserServiceImpl;
import ru.test.spark.service.interfaces.DepartmentService;
import ru.test.spark.service.interfaces.TestService;
import ru.test.spark.service.interfaces.UserService;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.test.spark.utils.JsonUtils;

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
    private static ObjectMapper mapper = new ObjectMapper();

    public static void publicResource(){
        get("/" + CollectionsConst.Collections.Users.COLLECTION_NAME + "/getAll", (req, res) -> getAllUsers());
        get("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/getById", (req, res) -> getUser(req.queryParams("id")));
        get("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/filter", (req, res) -> getFilteredUsers(req.queryParams("user")));
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/insert", (req, res) -> generateData());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/delete", (req, res) -> deleteUser());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/edit", (req, res) -> updateUser());
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/setChef", (req, res) -> setChef());
    }

    private static String generateData(){
        UserDto newUser = testService.newRandomUserDto();
        userService.insertUser(newUser);

        return newUser.toString();
    }

    private static String deleteUser(){
        String id = "8b1616c1-edcf-4450-aa44-19f26ef82b65";
        userDao.deleteById(UUID.fromString(id));
        return "Delete user by id " + id;
    }

    private static String getAllUsers(){
        StringBuilder sb = new StringBuilder();
        List<UserDto> users = userService.getUserDtoList(null);
        users.forEach(user -> sb.append(user.toString()).append("\n"));
        return sb.toString();
    }

    private static String getFilteredUsers(String filterString){

        UserFilter filter = null;
        try {
            filter = mapper.readValue(filterString, UserFilter.class);
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }
        filter.normalizeLimits();
        filter.setFio("FIO-2-2-9-9-11-3245");
        filter.setCreateTime(1507906534037L);

        ListDto resultDto = new ListDto();

        StringBuilder sb = new StringBuilder();
        List<UserDto> users = userService.getUserDtoList(filter);
        resultDto.setData(users);
        resultDto.setLimit(filter.getLimit());
        resultDto.setPage(filter.getPage());
        resultDto.setStart(filter.getStart());

        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(resultDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String getUser(String id){
        return userService.getUserById(UUID.fromString(id)).toString();
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

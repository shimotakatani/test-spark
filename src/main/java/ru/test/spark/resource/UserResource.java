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

import static ru.test.spark.utils.CommonUtils.isNotNull;
import static ru.test.spark.utils.CommonUtils.isNotNullOrEmpty;
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
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/insert", (req, res) -> insertUser(req.body()));
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/delete", (req, res) -> deleteUser(req.queryParams("id")));
        post("/" +CollectionsConst.Collections.Users.COLLECTION_NAME + "/edit", (req, res) -> updateUser(req.body()));

        //test resource
        get("/generateResource", (req, res) -> generateData());
    }

    private static String generateData(){
        testService.generateManyUsersAndDepartments();


        return "generate many entities";
    }

    private static String deleteUser(String id){
        if(isNotNullOrEmpty(id)) {
            userDao.deleteById(UUID.fromString(id));
            return "Delete user by id " + id;
        }
        return MessageConst.ErrorMessage.errorRequestParam;
    }

    private static String getAllUsers(){
        UserFilter filter = new UserFilter();
        filter.normalizeLimits();

        ListDto resultDto = new ListDto();

        List<UserDto> users = userService.getUserDtoList();
        resultDto.setData(users);
        resultDto.setLimit(filter.getLimit());
        resultDto.setPage(filter.getPage());
        resultDto.setStart(filter.getStart());
        resultDto.setCount(userService.getUserDtoListCount());

        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(resultDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String getFilteredUsers(String filterString){

        UserFilter filter = null;
        try {
            if (isNotNull(filterString)) {
                filter = mapper.readValue(filterString, UserFilter.class);
            }
            else {
                filter = new UserFilter();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }
        filter.normalizeLimits();

        ListDto resultDto = new ListDto();

        List<UserDto> users = userService.getUserDtoList(filter);
        resultDto.setData(users);
        resultDto.setLimit(filter.getLimit());
        resultDto.setPage(filter.getPage());
        resultDto.setStart(filter.getStart());
        resultDto.setCount(userService.getUserDtoListCount(filter));

        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(resultDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String getUser(String id){
        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(userService.getUserById(UUID.fromString(id)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String updateUser(String userString){

        UserDto userDto = null;
        try {
            if (isNotNull(userString)) {
                userDto = mapper.readValue(userString, UserDto.class);
            }
            else {
                userDto = new UserDto();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }

        if ( ! isNotNull(userDto.getId())){
            return MessageConst.ErrorMessage.missIdParam;
        }

        UserDto user = userService.updateUser(userDto);
        if ( ! isNotNull(user)) {
            return MessageConst.ErrorMessage.errorUpdate;
        }
        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Update user: " + resultString;
    }

    private static String insertUser(String userString){

        UserDto userDto = null;
        try {
            if (isNotNull(userString)) {
                userDto = mapper.readValue(userString, UserDto.class);
            }
            else {
                userDto = new UserDto();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }


        UserDto user = userService.insertUser(userDto);
        if ( ! isNotNull(user)) {
            return MessageConst.ErrorMessage.errorUpdate;
        }
        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Insert user: " + resultString;
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

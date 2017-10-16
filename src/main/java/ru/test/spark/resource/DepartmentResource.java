package ru.test.spark.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.consts.MessageConst;
import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.dto.ListDto;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.filters.DepartmentFilter;
import ru.test.spark.service.impl.DepartmentServiceImpl;
import ru.test.spark.service.impl.TestServiceImpl;
import ru.test.spark.service.impl.UserServiceImpl;
import ru.test.spark.service.interfaces.DepartmentService;
import ru.test.spark.service.interfaces.TestService;
import ru.test.spark.service.interfaces.UserService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;
import static ru.test.spark.utils.CommonUtils.isNotNullOrEmpty;
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
    private static ObjectMapper mapper = new ObjectMapper();

    public static void publicResource(){

        get("/" + CollectionsConst.Collections.Department.COLLECTION_NAME + "/getAll", (req, res) -> getAllDepartments());
        get("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/getById", (req, res) -> getDepartment(req.queryParams("id")));
        get("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/filter", (req, res) -> getFilteredDepartments(req.queryParams("department")));
        post("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/insert", (req, res) -> insertDepartment(req.body()));
        post("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/delete", (req, res) -> deleteDepartment(req.queryParams("id")));
        post("/" +CollectionsConst.Collections.Department.COLLECTION_NAME + "/edit", (req, res) -> updateDepartment(req.body()));

    }

    private static String generateData(){
        DepartmentDto newRandomDepartmentDto = testService.newRandomDepartmentDto();
        DepartmentEntity departmentEntity = new DepartmentEntity();
        newRandomDepartmentDto.generateEntityFromDto(departmentEntity);
        departmentDao.insert(departmentEntity);

        return newRandomDepartmentDto.toString();
    }

    private static String deleteDepartment(String id){
        if(isNotNullOrEmpty(id)) {
            departmentDao.deleteById(UUID.fromString(id));
            return "Delete department by id " + id;
        }
        return MessageConst.ErrorMessage.errorRequestParam;
    }

    private static String getAllDepartments(){
        DepartmentFilter filter = new DepartmentFilter();
        filter.normalizeLimits();

        ListDto resultDto = new ListDto();

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentDtoList();
        resultDto.setData(departmentDtos);
        resultDto.setLimit(filter.getLimit());
        resultDto.setPage(filter.getPage());
        resultDto.setStart(filter.getStart());
        resultDto.setCount(departmentService.getDepartmentDtoListCount());

        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(resultDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String getFilteredDepartments(String filterString){

        DepartmentFilter filter = null;
        try {
            if (isNotNull(filterString)) {
                filter = mapper.readValue(filterString, DepartmentFilter.class);
            }
            else {
                filter = new DepartmentFilter();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }
        filter.normalizeLimits();

        ListDto resultDto = new ListDto();

        List<DepartmentDto> departmentDtos = departmentService.getDepartmentDtoList(filter);
        resultDto.setData(departmentDtos);
        resultDto.setLimit(filter.getLimit());
        resultDto.setPage(filter.getPage());
        resultDto.setStart(filter.getStart());
        resultDto.setCount(departmentService.getDepartmentDtoListCount(filter));

        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(resultDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String getDepartment(String id){
        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(departmentService.getDepartmentById(UUID.fromString(id)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static String updateDepartment(String userString){

        DepartmentDto departmentDto = null;
        try {
            if (isNotNull(userString)) {
                departmentDto = mapper.readValue(userString, DepartmentDto.class);
            }
            else {
                departmentDto = new DepartmentDto();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }

        if ( ! isNotNull(departmentDto.getId())){
            return MessageConst.ErrorMessage.missIdParam;
        }

        DepartmentDto user = departmentService.updateDepartment(departmentDto);
        if ( ! isNotNull(user)) {
            return MessageConst.ErrorMessage.errorUpdate;
        }
        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Update department: " + resultString;
    }

    private static String insertDepartment(String userString){

        DepartmentDto departmentDto = null;
        try {
            if (isNotNull(userString)) {
                departmentDto = mapper.readValue(userString, DepartmentDto.class);
            }
            else {
                departmentDto = new DepartmentDto();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return MessageConst.ErrorMessage.errorRequestParam;
        }


        DepartmentDto user = departmentService.insertDepartment(departmentDto);
        if ( ! isNotNull(user)) {
            return MessageConst.ErrorMessage.errorUpdate;
        }
        String resultString = null;
        try {
            resultString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Insert department: " + resultString;
    }

}

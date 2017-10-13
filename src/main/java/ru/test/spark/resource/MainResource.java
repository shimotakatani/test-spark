package ru.test.spark.resource;

/**
 * Основной ресурс(пока тут спарк инициализируется)
 * create time 11.10.2017
 *
 * @author nponosov
 */

import org.apache.log4j.BasicConfigurator;
import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.impl.UserDaoImpl;
import ru.test.spark.dao.impl.UserDaoSessionImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.DepartmentEntity;
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



    public static Logger logger = Logger.getLogger(MainResource.class);

    public static void main(String[] args) {


        BasicConfigurator.configure();

        SparkUtils.createServerWithRequestLog(logger);
        port(4567);

        UserResource.publicResource();
        DepartmentResource.publicResource();

    }




}


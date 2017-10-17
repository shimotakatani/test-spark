package ru.test.spark.resource;

/**
 * Основной ресурс(пока тут спарк инициализируется)
 * create time 11.10.2017
 *
 * @author nponosov
 */

import org.apache.log4j.BasicConfigurator;
import ru.test.spark.logger.SparkUtils;
import org.apache.log4j.Logger;
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


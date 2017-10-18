package ru.test.spark.consts;

/**
 * Константы для приложения
 * create time 11.10.2017
 *
 * @author nponosov
 */
public final class AppConst {
    public static final class Default{
        public static final String DB_NAME = "test";
        public static final String HOST_URL = "localhost";
        public static final Integer HOST_PORT = 27017;

    }

    public static final class Test{
        public static final Long DEPARTMENT_GENERATE_COUNT = 2000L;
        public static final Long USER_GENERATE_COUNT = 25000L;
    }
}

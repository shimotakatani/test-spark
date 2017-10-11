package ru.test.spark.consts;

/**
 * Константы для коллеций(так в монго таблицы обзываются)
 * create time 11.10.2017
 *
 * @author nponosov
 */
public final class CollectionsConst {
    private CollectionsConst(){
    }

    public static final CollectionsConst instance = new CollectionsConst();

    public static final class Collections{
        public static final class Users{
            public static final String COLLECTION_NAME = "users";
            public static final String FIO = "fio";
            public static final String PHONE_NUMBER = "phoneNumber";
            public static final String EMAIL = "email";
            public static final String CHEEF = "cheef_id";
            public static final String DEPARTMENT = "department_id";
        }

        public static final class Department{
            public static final String COLLECTION_NAME = "departments";
            public static final String NAME = "name";
        }

        public static final class Abstract{
            public static final String ID = "_id";
            public static final String CREATE_TIME = "create_time";
            public static final String UPDATE_TIME = "update_time";
            public static final String STATUS = "status";
        }
    }
}

package ru.test.spark.dao.impl;

import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.orm.DBCleint;

/**
 * DAO для сущности пользователь
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    public UserDaoImpl(){
        this.collection = DBCleint.getUserCollection();
    }
}

package ru.test.spark.dao.impl;

import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;

import javax.ejb.Local;

/**
 * create time 13.10.2017
 *
 * @author nponosov
 */
@Local(UserDao.class)
public class UserDaoSessionImpl extends GenericDaoImpl<UserEntity> implements UserDao {

}

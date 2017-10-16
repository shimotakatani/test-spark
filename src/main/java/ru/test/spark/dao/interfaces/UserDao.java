package ru.test.spark.dao.interfaces;

import ru.test.spark.entity.UserEntity;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.filters.UserFilter;

import java.util.List;

/**
 * Интерфейс DAO для сущности Пользователь
 * create time 11.10.2017
 *
 * @author nponosov
 */
public interface UserDao extends GenericDao<UserEntity> {

    List<UserEntity> getAllActive(UserFilter filter);
}

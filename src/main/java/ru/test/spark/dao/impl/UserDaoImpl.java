package ru.test.spark.dao.impl;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.orm.DBCleint;
import ru.test.spark.orm.PostgreClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static ru.test.spark.resource.MainResource.logger;

/**
 * DAO для сущности пользователь
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    @Override
    public UserEntity getById(UUID id) {

        UserEntity userEntity = null;
        PreparedStatement findStatament = null;
        try {
            String selectById = "SELECT " + CollectionsConst.Collections.Users.PHONE_NUMBER + " FROM " + CollectionsConst.Collections.Users.COLLECTION_NAME
                    + " where cast(" + CollectionsConst.Collections.Abstract.ID +" as text) = ? ";
            findStatament = connection.prepareStatement(selectById);
            findStatament.setString(1, id.toString());
            logger.info(selectById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
        ResultSet resultSet = findStatament.executeQuery();

        resultSet.next();
        userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setPhoneNumber(resultSet.getString(CollectionsConst.Collections.Users.PHONE_NUMBER));

            findStatament.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                findStatament.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userEntity;
    }

    @Override
    public void deleteById(UUID id) {
        super.deleteById(id);
    }

    @Override
    public List<UserEntity> getAllActive() {
        return super.getAllActive();
    }

    @Override
    public List<UserEntity> getAll() {
        return super.getAll();
    }

    @Override
    public List<UserEntity> getAllActive(AbstractFilter filter) {
        return super.getAllActive(filter);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return super.update(entity);
    }

    @Override
    public UserEntity insert(UserEntity entity) {
        return super.insert(entity);
    }

    @Override
    public Long getActiveCount() {
        return super.getActiveCount();
    }
}

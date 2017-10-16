package ru.test.spark.dao.impl;

import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.filters.UserFilter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.test.spark.resource.MainResource.logger;
import static ru.test.spark.utils.CommonUtils.isNotNullOrEmpty;

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
        try {   //TODO убрать такое перечисление полей(только на что нормальное поменять? пока могу только на константы, но лучше бы аналог звёздочки)
            String selectById = "SELECT cast(id as text) as id, create_time, update_time, status, fio, phone_number, email FROM "
                    + CollectionsConst.Collections.Users.COLLECTION_NAME
                    + " where cast(" + CollectionsConst.Collections.Abstract.ID +" as text) = ? ";
            findStatament = connection.prepareStatement(selectById);
            findStatament.setString(1, id.toString());
            logger.info(selectById);

        ResultSet resultSet = findStatament.executeQuery();

        resultSet.next();
        userEntity = convertFromResultSet(resultSet);

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
        PreparedStatement deleteStatament = null;
        // в коментарии запрос для настоящего удаления
        //String deleteByIdQuery = "DELETE FROM " + CollectionsConst.Collections.Users.COLLECTION_NAME + " WHERE cast(id as text) = ?";
        String deleteByIdQuery = "UPDATE " + CollectionsConst.Collections.Users.COLLECTION_NAME + " SET status = 'DELETED' WHERE cast(id as text) = ?";
        try {
            deleteStatament = connection.prepareStatement(deleteByIdQuery);
            deleteStatament.setString(1, id.toString());
            deleteStatament.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                deleteStatament.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public List<UserEntity> getAllActive() {

        List<UserEntity> userEntityList = new ArrayList<>();
        PreparedStatement findStatament = null;
        try {   //TODO убрать такое перечисление полей(только на что нормальное поменять? пока могу только на константы, но лучше бы аналог звёздочки)
            String selectById = "SELECT cast(id as text) as id, create_time, update_time, status, fio, phone_number, email FROM "
                    + CollectionsConst.Collections.Users.COLLECTION_NAME
                    + " where " + CollectionsConst.Collections.Abstract.STATUS +" = ? ";
            findStatament = connection.prepareStatement(selectById);
            findStatament.setString(1, EntityStatusEnum.ACTIVE.toString());
            logger.info(selectById);

            ResultSet resultSet = findStatament.executeQuery();

            while(resultSet.next()){
                userEntityList.add(convertFromResultSet(resultSet));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                findStatament.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userEntityList;

    }

    @Override
    public List<UserEntity> getAll() {
        List<UserEntity> userEntityList = new ArrayList<>();
        PreparedStatement findStatament = null;
        try {   //TODO убрать такое перечисление полей(только на что нормальное поменять? пока могу только на константы, но лучше бы аналог звёздочки)
            String selectById = "SELECT cast(id as text) as id, create_time, update_time, status, fio, phone_number, email FROM "
                    + CollectionsConst.Collections.Users.COLLECTION_NAME;
            findStatament = connection.prepareStatement(selectById);

            logger.info(selectById);

            ResultSet resultSet = findStatament.executeQuery();

            while(resultSet.next()){
                userEntityList.add(convertFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                findStatament.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userEntityList;
    }

    //TODO отложить до реализации построителя запросов
    @Override
    public List<UserEntity> getAllActive(UserFilter filter) {
        return super.getAllActive(filter);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        PreparedStatement updateStatament = null;

        String deleteByIdQuery = "UPDATE " + CollectionsConst.Collections.Users.COLLECTION_NAME
                + " SET (create_time, update_time, status, fio, phone_number, email) = (?, ?, ?, ?, ?, ?) " +
                " WHERE cast(id as text) = ?";
        try {
            updateStatament = connection.prepareStatement(deleteByIdQuery);
            //updateStatament.set(1, entity.getId());
            updateStatament.setLong(1, entity.getCreateTime());
            updateStatament.setLong(2, entity.getUpdateTime());
            updateStatament.setString(3, entity.getStatus().toString());
            updateStatament.setString(4, entity.getFio());
            updateStatament.setString(5, entity.getPhoneNumber());
            updateStatament.setString(6, entity.getEmail());
            updateStatament.setString(7, entity.getId().toString());

            updateStatament.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                updateStatament.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return entity;
    }

    @Override
    public UserEntity insert(UserEntity entity) {
        return super.insert(entity);
    }

    @Override
    public Long getActiveCount() {
        return super.getActiveCount();
    }

    private UserEntity convertFromResultSet(ResultSet sourceResultSet){
        UserEntity entity = new UserEntity();


        try {
            String id = null;
            id = sourceResultSet.getString(CollectionsConst.Collections.Abstract.ID);

            if (isNotNullOrEmpty(id)) entity.setId(UUID.fromString(id));
            entity.setCreateTime(sourceResultSet.getLong(CollectionsConst.Collections.Abstract.CREATE_TIME));
            entity.setUpdateTime(sourceResultSet.getLong(CollectionsConst.Collections.Abstract.UPDATE_TIME));
            entity.setStatus(EntityStatusEnum.valueOf(sourceResultSet.getString(CollectionsConst.Collections.Abstract.STATUS)));
            entity.setFio(sourceResultSet.getString(CollectionsConst.Collections.Users.FIO));
            entity.setPhoneNumber(sourceResultSet.getString(CollectionsConst.Collections.Users.PHONE_NUMBER));
            entity.setEmail(sourceResultSet.getString(CollectionsConst.Collections.Users.EMAIL));
            //TODO поля связи
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }
}

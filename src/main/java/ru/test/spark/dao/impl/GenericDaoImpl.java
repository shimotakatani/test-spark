package ru.test.spark.dao.impl;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.jetty.util.log.Log;
import ru.test.spark.consts.CollectionsConst;
import ru.test.spark.dao.interfaces.GenericDao;
import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.orm.PostgreClient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Абстрактное DAO
 * create time 11.10.2017
 *
 * @author nponosov
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T>{

    //Коллекция с которой работает DAO
    protected Connection connection = PostgreClient.getPostgresConnection();

    @PersistenceUnit(name = "test")
    EntityManager em;

    private Class<T> entityClass;

    private Class<T> getEntityClass() {
        if (entityClass == null) {
            ParameterizedType genericSuperClass;
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
            } else {
                genericSuperClass = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
            }
            entityClass = (Class) genericSuperClass.getActualTypeArguments()[0];
        }
        return entityClass;
    }

    @Override
    public T getById(UUID id) {

        Object o = em.find(getEntityClass(), id);
        return o != null ? (T) o : null;
    }

    @Override
    public void deleteById(UUID id) {
        Object ref = null;
        try {
            ref = em.getReference(getEntityClass(), id);
        } catch (Exception e) {
            System.out.println("Сущность с id = :id не найдена для удаления".replace(":id", id.toString()));
        }
        if ( (ref != null) && (ref instanceof AbstractEntity) ) {
            ((AbstractEntity) ref).setStatus(EntityStatusEnum.DELETED);
        }
    }

    @Override
    public List<T> getAllActive() {
        System.out.print(em == null);
        return em.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity where entity.status = :" + EntityStatusEnum.ACTIVE).getResultList();
    }

    @Override
    public List<T> getAll() {
        System.out.print(em == null);
        return em == null ? Collections.EMPTY_LIST : em.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity").getResultList();
    }

    @Override
    public List<T> getAllActive(AbstractFilter filter) {
        return null; //TODO реализуется только после реализации построителя запросов
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public T insert(T entity) {
        return em.merge(entity);
    }

    @Override
    public Long getActiveCount() {
        return 0L; //TODO реализуется только после реализации построителя запросов
    }
}

package ru.test.spark.dao.impl;


import org.hibernate.Session;
import ru.test.spark.dao.interfaces.GenericDao;
import ru.test.spark.em.LocalEntityMangerFactory;
import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.orm.PostgreClient;
import ru.test.spark.utils.HibernateUtils;

import javax.persistence.*;

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


    protected EntityManager em;

    Session session;

    protected GenericDaoImpl(){
        //LocalEntityMangerFactory factory = new LocalEntityMangerFactory();
        //EntityManagerFactory entityManagerFactory = factory.entityManagerFactory(factory.dataSource(), factory.hibernateProperties());
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        this.em = entityManagerFactory.createEntityManager();
        //this.session = HibernateUtils.getSessionFactory().getCurrentSession();
    }

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

        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object o = session.find(getEntityClass(), id);
        session.getTransaction().commit();
        return o != null ? (T) o : null;
    }

    @Override
    public void deleteById(UUID id) {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object ref = null;
        try {
            ref = session.getReference(getEntityClass(), id);
        } catch (Exception e) {
            System.out.println("Сущность с id = :id не найдена для удаления".replace(":id", id.toString()));
        }
        if ( (ref != null) && (ref instanceof AbstractEntity) ) {
            ((AbstractEntity) ref).setStatus(EntityStatusEnum.DELETED);
        }
        session.getTransaction().commit();
    }

    @Override
    public List<T> getAllActive() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query getQuery = session.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity where entity.status = :status");
        getQuery.setParameter("status", EntityStatusEnum.ACTIVE);
        List<T> entityList = getQuery.getResultList();
        session.getTransaction().commit();
        return entityList;
    }

    @Override
    public List<T> getAll() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<T> entityList = session.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity").getResultList();
        session.getTransaction().commit();
        return entityList;
    }

    @Override
    public List<T> getAllActive(AbstractFilter filter) {
        return null; //TODO реализуется только после реализации построителя запросов
    }

    @Override
    public T update(T entity){
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public T insert(T entity) {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public Long getActiveCount() {
        return 0L; //TODO реализуется только после реализации построителя запросов
    }
}

package ru.test.spark.dao.impl;


import org.hibernate.Session;
import ru.test.spark.dao.interfaces.GenericDao;
import ru.test.spark.entity.AbstractEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.orm.PostgreClient;
import ru.test.spark.utils.CommonUtils;
import ru.test.spark.utils.HibernateUtils;

import javax.persistence.*;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
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

    protected Class<T> getEntityClass() {
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
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query getQuery = session.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity where entity.status = :status ");
        getQuery.setParameter("status", EntityStatusEnum.ACTIVE);
        List<T> entityList = getQuery.getResultList();
        session.getTransaction().commit();
        return entityList;
    }

    @Override
    public T update(T entity){
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //entity = (T)
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public T insert(T entity) {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        entity = (T) session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public Long getActiveCount() {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("Select count(entity) FROM " + getEntityClass().getSimpleName() + " entity where entity.status = :status");
        query.setParameter("status", EntityStatusEnum.ACTIVE);
        List<Long> entityList = query.getResultList();
        session.getTransaction().commit();
        return CommonUtils.isNotNullOrEmpty(entityList) ? entityList.get(0) : 0L;
    }

    public Long getActiveCount(AbstractFilter filter) {
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        StringBuilder sb = new StringBuilder();
        sb.append("Select count(entity) FROM ").append(getEntityClass().getSimpleName()).append(" entity where entity.status = :status ");
        sb.append(filter.getWhereLimitOrderString());
        Query getQuery = session.createQuery(sb.toString());
        filter.setLimitOption(getQuery);
        getQuery.setParameter("status", EntityStatusEnum.ACTIVE);
        List<Long> entityList = getQuery.getResultList();
        session.getTransaction().commit();
        return CommonUtils.isNotNullOrEmpty(entityList) ? entityList.get(0) : 0L;
    }
}

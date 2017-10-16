package ru.test.spark.dao.impl;

import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.filters.UserFilter;
import ru.test.spark.utils.CommonUtils;
import ru.test.spark.utils.HibernateUtils;

import static ru.test.spark.resource.MainResource.logger;

import javax.ejb.Local;
import javax.persistence.Query;
import java.util.List;

/**
 * create time 13.10.2017
 *
 * @author nponosov
 */
@Local(UserDao.class)
public class UserDaoSessionImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    @Override
    public List<UserEntity> getAllActive(UserFilter filter) {

        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        StringBuilder sb = new StringBuilder();
        sb.append("Select entity FROM ").append(getEntityClass().getSimpleName()).append(" entity where entity.status = :status ");
        sb.append(filter.getWhereLimitOrderString());
        Query getQuery = session.createQuery(sb.toString());
        filter.setLimitOption(getQuery);
        getQuery.setParameter("status", EntityStatusEnum.ACTIVE);
        List<UserEntity> entityList = getQuery.getResultList();
        session.getTransaction().commit();
        return entityList;
    }

//    @Override
//    public Long getActiveCount(AbstractFilter filter) {
//        session = HibernateUtils.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        StringBuilder sb = new StringBuilder();
//        sb.append("Select count(entity) FROM ").append(getEntityClass().getSimpleName()).append(" entity where entity.status = :status ");
//        sb.append(filter.getWhereLimitOrderString());
//        Query getQuery = session.createQuery(sb.toString());
//        filter.setLimitOption(getQuery);
//        getQuery.setParameter("status", EntityStatusEnum.ACTIVE);
//        List<Long> entityList = getQuery.getResultList();
//        session.getTransaction().commit();
//        return CommonUtils.isNotNullOrEmpty(entityList) ? entityList.get(0) : 0L;
//    }
}

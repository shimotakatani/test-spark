package ru.test.spark.dao.impl;

import ru.test.spark.dao.interfaces.UserDao;
import ru.test.spark.entity.UserEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.filters.UserFilter;
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
        logger.info(filter.getCountOnPage());
        logger.info(filter.getCreateTime());
        logger.info(filter.getId());
        logger.info(filter.getLimit());
        logger.info(filter.getPage());
        logger.info(filter.getStart());
        logger.info(filter.getUpdateTime());
        logger.info(filter.getEmail());
        logger.info(filter.getFio());
        logger.info(filter.getPhoneNumber());
        logger.info(filter.getCheefId());
        logger.info(filter.getDepartmentId());

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
}

package ru.test.spark.dao.impl;

import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.enums.EntityStatusEnum;
import ru.test.spark.filters.AbstractFilter;
import ru.test.spark.filters.DepartmentFilter;
import ru.test.spark.utils.CommonUtils;
import ru.test.spark.utils.HibernateUtils;

import javax.persistence.Query;
import java.util.List;

/**
 * DAO для сущности Отдел
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DepartmentDaoImpl extends GenericDaoImpl<DepartmentEntity> implements DepartmentDao {
    @Override
    public List<DepartmentEntity> getAllActive(DepartmentFilter filter) {

        session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        StringBuilder sb = new StringBuilder();
        sb.append("Select entity FROM ").append(getEntityClass().getSimpleName()).append(" entity where entity.status = :status ");
        sb.append(filter.getWhereLimitOrderString());
        Query getQuery = session.createQuery(sb.toString());
        filter.setLimitOption(getQuery);
        getQuery.setParameter("status", EntityStatusEnum.ACTIVE);
        List<DepartmentEntity> entityList = getQuery.getResultList();
        session.getTransaction().commit();
        return entityList;
    }


}

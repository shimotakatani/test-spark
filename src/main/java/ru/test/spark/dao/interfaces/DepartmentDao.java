package ru.test.spark.dao.interfaces;

import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.filters.DepartmentFilter;

import java.util.List;

/**
 * Интерфейс DAO для сущности Отдел
 * create time 11.10.2017
 *
 * @author nponosov
 */
public interface DepartmentDao extends GenericDao<DepartmentEntity> {

    List<DepartmentEntity> getAllActive(DepartmentFilter filter);
}

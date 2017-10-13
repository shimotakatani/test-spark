package ru.test.spark.service.interfaces;

import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.filters.DepartmentFilter;

import java.util.List;
import java.util.UUID;

/**
 * Сервис отделов
 * create time 13.10.2017
 *
 * @author nponosov
 */
public interface DepartmentService {

    /**
     * Получить DTO отдела по id
     * @param id - идентификатор отдела
     * @return DTO отдела
     * @author nponosov
     */
    DepartmentDto getDepartmentById(UUID id);

    /**
     * Удаление отдела по его id
     * @param id - идентификатор отдела
     * @author nponosov
     */
    void deleteDepartmentById(UUID id);

    /**
     * Получить список отделов (с фильтрацией)
     * @param filter - фильтр с параметрами для запроса
     * @return список отдела
     * @author nponosov
     */
    List<DepartmentDto> getDepartmentDtoList(DepartmentFilter filter);

    /**
     * Получить список отделов(просто всех)
     * @return список пользователей
     * @author nponosov
     */
    List<DepartmentDto> getDepartmentDtoList();

    /**
     * Получить размер выборки отделов по фильтру
     * @param filter - фильтр с параметрами для запроса
     * @return количество записей в выборке
     * @author nponosov
     */
    Long getDepartmentDtoListCount(DepartmentFilter filter);

    /**
     * Получить размер выборки отделов (просто всех)
     * @return количество записей в выборке
     * @author nponosov
     */
    Long getDepartmentDtoListCount();

    /**
     * Обновить отдел
     * @param updateUserDto - DTO отдела с новыми данными
     * @return обновлённый отдел
     */
    DepartmentDto updateDepartment(DepartmentDto updateUserDto);

    /**
     * Вставить отдел
     * @param newUserDto - DTO вставляемого отдела с данными
     * @return вставленный отдел
     */
    DepartmentDto insertDepartment(DepartmentDto newUserDto);
}

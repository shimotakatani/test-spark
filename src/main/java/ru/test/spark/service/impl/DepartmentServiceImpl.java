package ru.test.spark.service.impl;

import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.filters.DepartmentFilter;
import ru.test.spark.service.interfaces.DepartmentService;

import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Сервис отделов
 * create time 13.10.2017
 *
 * @author nponosov
 */
@Stateless
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public DepartmentDto getDepartmentById(UUID id) {
        return null;
    }

    @Override
    public void deleteDepartmentById(UUID id) {

    }

    @Override
    public List<DepartmentDto> getDepartmentDtoList(DepartmentFilter filter) {
        return null;
    }

    @Override
    public List<DepartmentDto> getDepartmentDtoList() {
        return null;
    }

    @Override
    public Long getDepartmentDtoListCount(DepartmentFilter filter) {
        return null;
    }

    @Override
    public Long getDepartmentDtoListCount() {
        return null;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto updateUserDto) {
        return null;
    }

    @Override
    public DepartmentDto insertDepartment(DepartmentDto newUserDto) {
        return null;
    }
}

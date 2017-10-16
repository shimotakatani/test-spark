package ru.test.spark.service.impl;

import ru.test.spark.dao.impl.DepartmentDaoImpl;
import ru.test.spark.dao.interfaces.DepartmentDao;
import ru.test.spark.dto.DepartmentDto;
import ru.test.spark.entity.DepartmentEntity;
import ru.test.spark.filters.DepartmentFilter;
import ru.test.spark.service.interfaces.DepartmentService;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.test.spark.utils.CommonUtils.isNotNull;
import static ru.test.spark.utils.CommonUtils.isNotNullOrEmpty;

/**
 * Сервис отделов
 * create time 13.10.2017
 *
 * @author nponosov
 */
@Stateless
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public DepartmentDto getDepartmentById(UUID id) {
        DepartmentDto userDto = new DepartmentDto();
        userDto.generateDtoFromEntity(departmentDao.getById(id));
        return userDto;
    }

    @Override
    public void deleteDepartmentById(UUID id) {
        departmentDao.deleteById(id);
    }

    @Override
    public List<DepartmentDto> getDepartmentDtoList(DepartmentFilter filter) {
        return convertEntitiesToDtos(departmentDao.getAllActive(filter));
    }

    @Override
    public List<DepartmentDto> getDepartmentDtoList() {
        return convertEntitiesToDtos(departmentDao.getAllActive());
    }

    @Override
    public Long getDepartmentDtoListCount(DepartmentFilter filter) {
        return departmentDao.getActiveCount(filter);
    }

    @Override
    public Long getDepartmentDtoListCount() {
        return departmentDao.getActiveCount();
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto updateDepartmentDto) {
        DepartmentDto departmentDto = null;
        DepartmentEntity departmentEntity = departmentDao.getById(updateDepartmentDto.getId());
        if (isNotNull(departmentEntity)){
            updateDepartmentDto.generateEntityFromDto(departmentEntity);
            departmentDao.update(departmentEntity);
            departmentDto = new DepartmentDto();
            departmentDto.generateDtoFromEntity(departmentEntity);
        }

        return departmentDto;
    }

    @Override
    public DepartmentDto insertDepartment(DepartmentDto newDepartmentDto) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        if (isNotNull(newDepartmentDto)){
            newDepartmentDto.generateEntityFromDto(departmentEntity);
            departmentEntity = departmentDao.insert(departmentEntity);
            newDepartmentDto.generateDtoFromEntity(departmentEntity);
            return newDepartmentDto;
        }
        return null;
    }

    private List<DepartmentDto> convertEntitiesToDtos(List<DepartmentEntity> entityList){
        List<DepartmentDto> returnUserList = new ArrayList<>();

        if (isNotNullOrEmpty(entityList)) {
            entityList.forEach(departmentEntity -> {
                DepartmentDto departmentDto = new DepartmentDto();
                departmentDto.generateDtoFromEntity(departmentEntity);
                returnUserList.add(departmentDto);
            });
        }

        return returnUserList;
    }
}

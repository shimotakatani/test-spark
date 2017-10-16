package ru.test.spark.filters;


import ru.test.spark.consts.CollectionsConst;

import static ru.test.spark.utils.CommonUtils.isNotNull;
import static ru.test.spark.utils.FilterUtils.getAndOption;

/**
 * Фильтр для сущности Отдел
 * create time 11.10.2017
 *
 * @author nponosov
 */
public class DepartmentFilter extends AbstractFilter{

    private String name;

    @Override
    public String getWhereLimitOrderString() {

        StringBuilder resultString = new StringBuilder();

//        UserDto newFilter = new UserDto();
//        newFilter.setFio("Fcgfg");
//        Field[] fields = newFilter.getClass().getFields();
//        for (Field field : fields) {
//            try {
//                resultString.append(field.getName()).append(": ").append(field.get(this));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }

        if(isNotNull(this.getId())){
            resultString.append(getAndOption(CollectionsConst.Entity.Department.ENTITY_NAME, CollectionsConst.Entity.Abstract.ID, this.getId()));
        }
        if(isNotNull(this.getCreateTime())){
            resultString.append(getAndOption(CollectionsConst.Entity.Department.ENTITY_NAME, CollectionsConst.Entity.Abstract.CREATE_TIME, this.getCreateTime()));
        }
        if(isNotNull(this.getUpdateTime())){
            resultString.append(getAndOption(CollectionsConst.Entity.Department.ENTITY_NAME, CollectionsConst.Entity.Abstract.UPDATE_TIME, this.getUpdateTime()));
        }
        if(isNotNull(this.getStatus())){
            resultString.append(getAndOption(CollectionsConst.Entity.Department.ENTITY_NAME, CollectionsConst.Entity.Abstract.STATUS, this.getStatus()));
        }

        if(isNotNull(this.getName())){
            resultString.append(getAndOption(CollectionsConst.Entity.Department.ENTITY_NAME, CollectionsConst.Entity.Department.NAME, this.getName()));
        }


        return resultString.toString();
    }

    public DepartmentFilter(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

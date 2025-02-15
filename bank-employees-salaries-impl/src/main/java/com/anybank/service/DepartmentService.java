package com.anybank.service;


import com.anybank.dto.DepartmentDto;
import com.anybank.model.Department;

import java.util.List;

public interface DepartmentService {

    /**
     * Добавление департамента в БД
     */
    DepartmentDto addDepartment(Department department);

    /**
     * Обновление департамента в БД
     */
    DepartmentDto updateDepartment(Department department, Integer id);

    /**
     * Удаление всех департаментов из БД
     */
    void deleteDepartment();

    /**
     * Удаление департамента по id из БД
     */
    void deleteDepartmentById(Integer id);

    /**
     * Получение списка департаментов из БД
     */
    List<DepartmentDto> getDepartment();

    /**
     * Получение департамента по id
     */
    DepartmentDto findDepartmentById(Integer id);
}

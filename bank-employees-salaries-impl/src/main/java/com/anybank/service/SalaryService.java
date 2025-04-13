package com.anybank.service;


import com.anybank.dto.SalaryDto;
import com.anybank.model.Salary;

import java.util.List;

public interface SalaryService {
    /**
     * Сохранить данные о зарплате
     */
    SalaryDto addSalary(Salary salary);

    /**
     * Обновление данные о зарплате
     */
    SalaryDto updateSalary(Salary salary, Long id);

    /**
     * Удаление всех зарплат из БД
     */
    void deleteSalary();

    /**
     * Удаление зарплат по id из БД
     */
    void deleteSalaryById(Long id);

    /**
     * получить данные о зарплате по id
     */
    SalaryDto getSalaryById(Long id);

    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    SalaryDto getSalaryByMonthForEmployee(Long employeeId, String month, String year);

    /**
     * получить данные о зарплате по сотруднику за год
     */
    List<SalaryDto> getSalaryByYearForEmployee(Long employeeId, String year);

    /**
     * получить данные о зарплате по отделу за месяц
     */
    List<SalaryDto> getSalaryByMonthForDepartment(Long departmentId, String month, String year);

    /**
     * получить данные о зарплате по отделу за год
     */
    List<SalaryDto> getSalaryByYearForDepartment(Long departmentId, String year);

    /**
     * получить данные о зарплате по компании за месяц
     */
    List<SalaryDto> getSalaryByMonth(String month, String year);

    /**
     * получить данные о зарплате по компании за год
     */
    List<SalaryDto> getSalaryByYear(String year);

    /**
     * Рассчитать данные о зарплате по сотруднику за месяц
     */
    public Salary calculateSalaryByMonthForEmployee(Long employeeId,
                                                    String month,
                                                    String year,
                                                    Integer countWorkDays,
                                                    Integer countMedDays);
}

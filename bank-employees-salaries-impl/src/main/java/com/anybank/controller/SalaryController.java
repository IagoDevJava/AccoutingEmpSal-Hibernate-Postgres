package com.anybank.controller;

import com.anybank.service.SalaryService;
import com.egorov.api.SalariesApi;
import com.egorov.model.DateTimePeriod;
import com.egorov.model.Salary;
import com.egorov.model.SalaryDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с зарплатами сотрудников. Обеспечивает REST API для добавления, обновления,
 * удаления и получения информации о зарплатах. Реализует интерфейс {@link SalariesApi} и использует
 * сервис {@link SalaryService} для выполнения бизнес-логики.
 *
 * <p>Все методы возвращают {@link ResponseEntity} с соответствующим HTTP статусом
 * и данными в теле ответа (если применимо).</p>
 *
 * @see SalariesApi
 * @see SalaryService
 * @see Salary
 * @see SalaryDto
 */
@RestController
@RequiredArgsConstructor
public class SalaryController implements SalariesApi {

  private final SalaryService salaryService;

  /**
   * Рассчитывает зарплаты сотрудников отдела за указанный период
   *
   * @param departmentId   идентификатор отдела
   * @param dateTimePeriod период времени для расчета зарплат
   * @return {@link ResponseEntity} со списком {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<SalaryDto>> calculateDepartmentSalariesByPeriod(Long departmentId,
      DateTimePeriod dateTimePeriod) {
    return ResponseEntity.ok(
        salaryService.calculateDepartmentSalariesByPeriod(departmentId, dateTimePeriod));
  }

  /**
   * Рассчитывает зарплату сотрудника за указанный период
   *
   * @param employeeId     идентификатор сотрудника
   * @param dateTimePeriod период времени для расчета зарплаты
   * @return {@link ResponseEntity} с {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<SalaryDto> calculateEmployeeSalaryByPeriod(Long employeeId,
      DateTimePeriod dateTimePeriod) {
    return ResponseEntity.ok(
        salaryService.calculateEmployeeSalaryByPeriod(employeeId, dateTimePeriod));
  }

  /**
   * Удаляет все записи о зарплатах
   *
   * @return {@link ResponseEntity} с HTTP статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteAllSalaries() {
    salaryService.deleteAllSalaries();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет запись о зарплате по идентификатору
   *
   * @param id идентификатор зарплаты для удаления
   * @return {@link ResponseEntity} с HTTP статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteSalaryById(Long id) {
    salaryService.deleteSalaryById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Получает зарплаты всех сотрудников компании за указанный период
   *
   * @param dateTimePeriod период времени для фильтрации
   * @return {@link ResponseEntity} со списком {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<SalaryDto>> getCompanySalariesByPeriod(DateTimePeriod dateTimePeriod) {
    return ResponseEntity.ok(salaryService.getCompanySalariesByPeriod(dateTimePeriod));
  }

  /**
   * Получает зарплаты сотрудников отдела за указанный период
   *
   * @param departmentId   идентификатор отдела
   * @param dateTimePeriod период времени для фильтрации
   * @return {@link ResponseEntity} со списком {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<SalaryDto>> getDepartmentSalariesByPeriod(Long departmentId,
      DateTimePeriod dateTimePeriod) {
    return ResponseEntity.ok(
        salaryService.getDepartmentSalariesByPeriod(departmentId, dateTimePeriod));
  }

  /**
   * Получает зарплату сотрудника за указанный период
   *
   * @param employeeId     идентификатор сотрудника
   * @param dateTimePeriod период времени для фильтрации
   * @return {@link ResponseEntity} с {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<SalaryDto> getEmployeeSalaryByPeriod(Long employeeId,
      DateTimePeriod dateTimePeriod) {
    return ResponseEntity.ok(salaryService.getEmployeeSalaryByPeriod(employeeId, dateTimePeriod));
  }

  /**
   * Получает запись о зарплате по идентификатору
   *
   * @param id идентификатор зарплаты
   * @return {@link ResponseEntity} с {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  public ResponseEntity<SalaryDto> getSalaryById(Long id) {
    return ResponseEntity.ok(salaryService.getSalaryById(id));
  }

  /**
   * Обновляет запись о зарплате
   *
   * @param id     идентификатор зарплаты для обновления
   * @param salary новые данные зарплаты
   * @return {@link ResponseEntity} с обновлённым {@link SalaryDto} и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<SalaryDto> updateSalary(Long id, Salary salary) {
    return ResponseEntity.ok(salaryService.updateSalary(salary, id));
  }
}
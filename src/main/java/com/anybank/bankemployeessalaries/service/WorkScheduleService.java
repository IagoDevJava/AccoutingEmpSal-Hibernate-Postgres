package com.anybank.bankemployeessalaries.service;

import com.anybank.bankemployeessalaries.dto.WorkScheduleDto;
import com.anybank.bankemployeessalaries.model.WorkSchedule;

import java.util.List;


public interface WorkScheduleService {
    /**
     * Добавить график в БД
     */
    WorkScheduleDto addSchedule(WorkSchedule workSchedule);


    /**
     * Изменить график в БД
     */
    WorkScheduleDto updateSchedule(WorkSchedule workSchedule, Integer scheduleId);

    /**
     * Удалить графики из БД
     */
    void deleteSchedules();

    /**
     * Удалить график в БД по id
     */
    void deleteScheduleById(Integer scheduleId);

    /**
     * Получить все графики в БД
     */
    List<WorkScheduleDto> getSchedules();

    /**
     * Получить график в БД по id
     */
    WorkScheduleDto getScheduleById(Integer scheduleId);
}

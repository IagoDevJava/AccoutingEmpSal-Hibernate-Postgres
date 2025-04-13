package com.anybank.service;

import com.anybank.dto.SalariesDataDto;
import com.anybank.model.SalariesData;

import java.util.List;

public interface SalariesDataService {
    /**
     * Добавить данные о зарплате в БД
     */
    SalariesDataDto addSalariesData(SalariesData salariesData);

    /**
     * Заменить данные о зарплате в БД
     */
    SalariesDataDto updateSalariesData(SalariesData salariesData, Long id);

    /**
     * Удалить все данные зарплат из БД
     */
    void deleteSalariesData();

    /**
     * Удалить данные зарплаты в БД по id
     */
    void deleteSalariesDataById(Long id);

    /**
     * Получить все данные зарплат в БД
     */
    List<SalariesDataDto> getSalariesData();

    /**
     * Получить данные зарплаты в БД по id
     */
    SalariesDataDto getSalariesDataById(Long id);
}

package com.anybank.service;


import com.egorov.model.Grade;
import com.egorov.model.GradeDto;
import java.util.List;

public interface GradeService {

  /**
   * Добавление грейда в БД
   */
  GradeDto createGrade(Grade grade);

  /**
   * Обновление грейда в БД
   */
  GradeDto updateGrade(Grade grade, Long id);

  /**
   * Удаление всех грейдов из БД
   */
  void deleteAllGrades();

  /**
   * Удаление грейда по id из БД
   */
  void deleteGradeById(Long id);

  /**
   * Получение списка грейдов из БД
   */
  List<GradeDto> getAllGrades();

  /**
   * Получение грейда по id
   */
  GradeDto getGradeById(Long id);
}

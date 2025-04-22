package com.egorov.service.impl;

import com.egorov.exception.GradeNotFoundException;
import com.egorov.mapper.GradeMapper;
import com.egorov.repository.GradeRepository;
import com.egorov.service.GradeService;
import com.egorov.model.Grade;
import com.egorov.model.GradeDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {

  private final GradeRepository gradeRepository;

  @Override
  public GradeDto createGrade(Grade grade) {
    return GradeMapper.toGradeDto(gradeRepository.save(grade));
  }

  @Override
  public GradeDto updateGrade(Grade grade, Long id) {
    Grade gradeById = gradeRepository.findById(id)
        .orElseThrow(() -> new GradeNotFoundException("Grade not found"));

    gradeById.setId(grade.getId());
    gradeById.setNameGrade(grade.getNameGrade());

    return GradeMapper.toGradeDto(gradeRepository.save(gradeById));
  }

  @Override
  public void deleteAllGrades() {
    gradeRepository.deleteAll();
  }

  @Override
  public void deleteGradeById(Long id) {
    gradeRepository.deleteById(id);
  }

  @Override
  public List<GradeDto> getAllGrades() {
    return GradeMapper.toGradeDtoList(gradeRepository.findAll());
  }

  @Override
  public GradeDto getGradeById(Long id) {
    return GradeMapper.toGradeDto(
        gradeRepository.findById(id)
            .orElseThrow(() -> new GradeNotFoundException("Grade not found")));
  }
}

package com.egorov.mapper;

import com.egorov.model.Grade;
import com.egorov.model.GradeDto;
import java.util.ArrayList;
import java.util.List;

public class GradeMapper {

  //Grade to GradeDto
  public static GradeDto toGradeDto(Grade grade) {
    return new GradeDto()
        .id(grade.getId())
        .nameGrade(grade.getNameGrade());
  }

  //GradeList to GradeDtoList
  public static List<GradeDto> toGradeDtoList(List<Grade> grades) {
    List<GradeDto> result = new ArrayList<>();
    for (Grade grade : grades) {
      result.add(toGradeDto(grade));
    }
    return result;
  }
}

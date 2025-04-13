package com.anybank.mapper;

import com.anybank.dto.PositionDto;
import com.anybank.model.Position;
import java.util.ArrayList;
import java.util.List;

public class PositionMapper {

  //Position to PositionDto
  public static PositionDto toPositionDto(Position position) {
    return PositionDto.builder()
        .id(position.getId())
        .name(position.getName())
        .departmentId(position.getDepartment().getId())
        .gradeId(position.getGrade().getId())
        .build();
  }

  //PositionList to PositionDtoList
  public static List<PositionDto> toPositionDtoList(List<Position> positions) {
    List<PositionDto> result = new ArrayList<>();
    for (Position position : positions) {
      result.add(toPositionDto(position));
    }
    return result;
  }
}

package com.anybank.mapper;

import com.egorov.model.Position;
import com.egorov.model.PositionDto;
import java.util.ArrayList;
import java.util.List;

public class PositionMapper {

  //Position to PositionDto
  public static PositionDto toPositionDto(Position position) {
    return new PositionDto(
        position.getName(),
        position.getDepartment().getId())
        .id(position.getId())
        .gradeId(position.getGrade().getId());
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

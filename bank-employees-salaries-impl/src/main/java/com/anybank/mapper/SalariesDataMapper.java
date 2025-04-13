package com.anybank.mapper;


import com.egorov.model.SalariesData;
import com.egorov.model.SalariesDataDto;
import java.util.ArrayList;
import java.util.List;

public class SalariesDataMapper {

  //SalariesData to SalariesDataDto
  public static SalariesDataDto toSalariesDataDto(SalariesData salariesData) {
    return new SalariesDataDto(
        salariesData.getWage(),
        salariesData.getBonus(),
        salariesData.getPosition().getId())
        .id(salariesData.getId());
  }

  //SalariesDataList to SalariesDataDtoList
  public static List<SalariesDataDto> toGSalariesDataDtoList(List<SalariesData> salariesDataList) {
    List<SalariesDataDto> result = new ArrayList<>();
    for (SalariesData salariesData : salariesDataList) {
      result.add(toSalariesDataDto(salariesData));
    }
    return result;

  }
}

package com.egorov.mapper;


import com.egorov.model.WorkSchedule;
import com.egorov.model.WorkScheduleDto;
import java.util.ArrayList;
import java.util.List;

public class WorkScheduleMapper {

  //WorkSchedule to WorkScheduleDto
  public static WorkScheduleDto toWorkScheduleDto(WorkSchedule workSchedule) {
    return new WorkScheduleDto(
        workSchedule.getWorkDay(),
        workSchedule.getWeekDay(),
        workSchedule.getWorkHour())
        .id(workSchedule.getId());
  }

  //EmployeeList to EmployeeDtoList
  public static List<WorkScheduleDto> toWorkScheduleDtoList(List<WorkSchedule> workSchedules) {
    List<WorkScheduleDto> result = new ArrayList<>();
    for (WorkSchedule workSchedule : workSchedules) {
      result.add(toWorkScheduleDto(workSchedule));
    }
    return result;
  }
}

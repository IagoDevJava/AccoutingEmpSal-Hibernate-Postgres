package com.anybank.service.impl;

import com.anybank.exception.ScheduleNotFoundException;
import com.anybank.mapper.WorkScheduleMapper;
import com.anybank.repository.WorkScheduleRepository;
import com.anybank.service.WorkScheduleService;
import com.egorov.model.WorkSchedule;
import com.egorov.model.WorkScheduleDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {

  private final WorkScheduleRepository workScheduleRepository;

  @Override
  public WorkScheduleDto addSchedule(WorkSchedule workSchedule) {
    return WorkScheduleMapper.toWorkScheduleDto(workScheduleRepository.save(workSchedule));
  }

  @Override
  public WorkScheduleDto updateSchedule(WorkSchedule workSchedule, Integer scheduleId) {
    WorkSchedule scheduleById = workScheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));

    scheduleById.setId(workSchedule.getId());
    scheduleById.setWorkDay(workSchedule.getWorkDay());
    scheduleById.setWeekDay(workSchedule.getWeekDay());
    scheduleById.setWorkHour(workSchedule.getWorkHour());

    return WorkScheduleMapper.toWorkScheduleDto(scheduleById);
  }

  @Override
  public void deleteSchedules() {
    workScheduleRepository.deleteAll();
  }

  @Override
  public void deleteScheduleById(Integer scheduleId) {
    workScheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
    workScheduleRepository.deleteById(scheduleId);
  }

  @Override
  public List<WorkScheduleDto> getSchedules() {
    return WorkScheduleMapper.toWorkScheduleDtoList(workScheduleRepository.findAll());
  }

  @Override
  public WorkScheduleDto getScheduleById(Integer scheduleId) {
    return WorkScheduleMapper.toWorkScheduleDto(workScheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found")));
  }
}

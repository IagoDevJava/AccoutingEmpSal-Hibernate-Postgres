package com.egorov.service.impl;

import com.egorov.exception.SalariesDataNotFoundException;
import com.egorov.mapper.SalariesDataMapper;
import com.egorov.repository.SalariesDateRepository;
import com.egorov.service.SalariesDataService;
import com.egorov.model.SalariesData;
import com.egorov.model.SalariesDataDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalariesDataServiceImpl implements SalariesDataService {

  private final SalariesDateRepository salariesDateRepository;

  @Override
  public SalariesDataDto addSalariesData(SalariesData salariesData) {
    return SalariesDataMapper.toSalariesDataDto(salariesDateRepository.save(salariesData));
  }

  @Override
  public SalariesDataDto updateSalariesData(SalariesData salariesData, Long id) {
    SalariesData salariesDataById = salariesDateRepository.findById(id)
        .orElseThrow(() -> new SalariesDataNotFoundException("SalariesData not found"));

    salariesDataById.setId(id);
    salariesDataById.setWage(salariesData.getWage());
    salariesDataById.setBonus(salariesData.getBonus());
    salariesDataById.setPosition(salariesData.getPosition());

    return SalariesDataMapper.toSalariesDataDto(salariesDateRepository.save(salariesDataById));
  }

  @Override
  public void deleteSalariesData() {
    salariesDateRepository.deleteAll();
  }

  @Override
  public void deleteSalariesDataById(Long id) {
    salariesDateRepository.deleteById(id);
  }

  @Override
  public List<SalariesDataDto> getSalariesData() {
    return SalariesDataMapper.toGSalariesDataDtoList(salariesDateRepository.findAll());
  }

  @Override
  public SalariesDataDto getSalariesDataById(Long id) {
    return SalariesDataMapper.toSalariesDataDto(salariesDateRepository.findById(id)
        .orElseThrow(() -> new SalariesDataNotFoundException("SalariesData not found")));
  }
}

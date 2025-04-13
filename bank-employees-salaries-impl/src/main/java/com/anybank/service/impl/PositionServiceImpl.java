package com.anybank.service.impl;

import com.anybank.dto.PositionDto;
import com.anybank.exception.PositionNotFoundException;
import com.anybank.mapper.PositionMapper;
import com.anybank.model.Position;
import com.anybank.repository.PositionRepository;
import com.anybank.service.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    /**
     * Добавление должности в БД
     */
    @Override
    public PositionDto addPosition(Position position) {
        return PositionMapper.toPositionDto(positionRepository.save(position));
    }

    /**
     * Обновление должности в БД
     */
    @Override
    public PositionDto updatePosition(Position position, Long id) {
        Position positionById = positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException("Position not found"));

        positionById.setId(id);
        positionById.setName(position.getName());
        positionById.setDepartment(position.getDepartment());
        positionById.setGrade(position.getGrade());

        return PositionMapper.toPositionDto(positionRepository.save(positionById));
    }

    /**
     * Удаление всех должностей из БД
     */
    @Override
    public void deletePositions() {
        positionRepository.deleteAll();
    }

    /**
     * Удаление должности по id из БД
     */
    @Override
    public void deletePositionById(Long id) {
        positionRepository.deleteById(id);
    }

    /**
     * Получение списка должностей из БД
     */
    @Override
    public List<PositionDto> getPosition() {
        return PositionMapper.toPositionDtoList(positionRepository.findAll());
    }

    /**
     * Получение должности по id
     */
    @Override
    public PositionDto getPositionById(Long id) {
        return PositionMapper.toPositionDto(positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException("Position not found")));
    }
}

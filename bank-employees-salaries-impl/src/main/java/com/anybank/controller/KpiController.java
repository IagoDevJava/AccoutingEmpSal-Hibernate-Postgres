package com.anybank.controller;

import com.anybank.dto.KpiDto;
import com.anybank.model.Kpi;
import com.anybank.service.KpiService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional(isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
@RequestMapping("/kpis")
public class KpiController {
    private final KpiService kpiService;

    /**
     * Добавление kpi в БД
     */
    @Transactional
    @PostMapping
    public ResponseEntity<KpiDto> addKpi(Kpi kpi) {
        return ResponseEntity.ok(kpiService.addKpi(kpi));
    }

    /**
     * Обновление kpi в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<KpiDto> updateKpi(@Valid @RequestBody Kpi kpi,
                                            @PathVariable @PositiveOrZero Long id) {
        return ResponseEntity.ok(kpiService.updateKpi(kpi, id));
    }

    /**
     * Удаление всех kpi из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteKpis() {
        kpiService.deleteKpis();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление kpi по id из БД
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKpiById(@PositiveOrZero @PathVariable Long id) {
        kpiService.deleteKpiById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка kpi из БД
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<KpiDto>> getKpis() {
        return ResponseEntity.ok(kpiService.getKpis());
    }

    /**
     * Получение kpi по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<KpiDto> getKpiById(@PositiveOrZero @PathVariable Long id) {
        return ResponseEntity.ok(kpiService.getKpiById(id));
    }
}

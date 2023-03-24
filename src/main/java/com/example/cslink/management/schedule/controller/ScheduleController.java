package com.example.cslink.management.schedule.controller;

import com.example.cslink.management.schedule.controller.service.ScheduleService;
import com.example.cslink.management.schedule.model.ds.CosmetologistSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @GetMapping("/cosmetologistsSchedule")
    public ResponseEntity<List<CosmetologistSchedule>> getCosmetologistsSchedule(
            @RequestParam(required = false) Long cosmetologistId,
            @RequestParam(required = false) Long procedureId,
            @RequestParam(required = false) Long customerId) {

        List<CosmetologistSchedule> schedule = scheduleService.getSchedule(cosmetologistId, procedureId, customerId);

        if (schedule.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(schedule);
    }
}


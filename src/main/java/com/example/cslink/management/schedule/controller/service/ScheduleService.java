package com.example.cslink.management.schedule.controller.service;

import com.example.cslink.management.schedule.model.ds.CosmetologistSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    List<CosmetologistSchedule> getSchedule(Long cosmetologistId, Long procedureId, Long customerId);
}

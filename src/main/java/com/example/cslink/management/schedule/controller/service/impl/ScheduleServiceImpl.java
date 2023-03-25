package com.example.cslink.management.schedule.controller.service.impl;

import com.example.cslink.management.schedule.controller.service.ScheduleService;
import com.example.cslink.management.schedule.model.valueobject.CosmetologistSchedule;
import com.example.cslink.user.model.dataccess.persistence.CosmetologistPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final CosmetologistPersistence cosmetologistPersistence;

    public ScheduleServiceImpl(CosmetologistPersistence cosmetologistPersistence) {
        this.cosmetologistPersistence = cosmetologistPersistence;
    }

    @Override
    public List<CosmetologistSchedule> getSchedule(Long cosmetologistId, Long procedureId, Long customerId) {
        List<CosmetologistSchedule> schedules = cosmetologistPersistence.callCosmetologistReservationsStoredProcedure(cosmetologistId, procedureId, customerId);
        return schedules;
    }
}

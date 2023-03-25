package com.example.cslink.user.model.dataccess.persistence;

import com.example.cslink.management.schedule.model.builder.CosmetologistScheduleBuilder;
import com.example.cslink.management.schedule.model.valueobject.CosmetologistSchedule;
import com.example.cslink.management.schedule.model.entity.Availability;
import com.example.cslink.user.model.entity.Cosmetologist;
import com.example.cslink.user.model.entity.Customer;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class CosmetologistPersistence {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> getCustomersByCosmetologistId(Long cosmetologistId) {
        Cosmetologist cosmetologist=entityManager.find(Cosmetologist.class, cosmetologistId);
        if (cosmetologist == null) {
            throw new IllegalArgumentException("Invalid cosmetologist ID");
        }
        return cosmetologist.getCustomers();
    }

    public List<Availability> getAvailability(Long cosmetologistId) {
        Cosmetologist cosmetologist=entityManager.find(Cosmetologist.class, cosmetologistId);
        if (cosmetologist == null) {
            throw new IllegalArgumentException("Cosmetologist not found for id: " + cosmetologistId);
        }
        return cosmetologist.getAvailability();
    }

    @Transactional
    public void updateAvailability(Long cosmetologistId, List<Availability> updatedAvailability) {
        Cosmetologist cosmetologist=entityManager.find(Cosmetologist.class, cosmetologistId);
        if (cosmetologist != null) {
            cosmetologist.setAvailability(updatedAvailability);
            entityManager.merge(cosmetologist);
        } else {
            throw new EntityNotFoundException("Could not find cosmetologist with id: " + cosmetologistId);
        }
    }

    public void addAvailability(Long cosmetologistId, Availability availability) {
        Cosmetologist cosmo=entityManager.find(Cosmetologist.class, cosmetologistId);
        cosmo.getAvailability().add(availability);
        entityManager.merge(cosmo);
    }

    public void removeAvailability(Long cosmetologistId, Long availabilityId) {
        Cosmetologist cosmo=entityManager.find(Cosmetologist.class, cosmetologistId);
        cosmo.getAvailability().removeIf(a -> Objects.equals(a.getId(), availabilityId));
        entityManager.merge(cosmo);
    }


    public List<CosmetologistSchedule> callCosmetologistReservationsStoredProcedure(Long cosmetologistId, Long procedureId, Long customerId) {
        StoredProcedureQuery query=entityManager.createStoredProcedureQuery("cosmetologist_reservations");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);

        query.setParameter(1, cosmetologistId);
        query.setParameter(2, procedureId);
        query.setParameter(3, customerId);

        List<Object[]> resultList=query.getResultList();
        List<CosmetologistSchedule> cosmetologistSchedules=new LinkedList<>();

        for (Object[] result : resultList) {
            cosmetologistSchedules.add(
                    new CosmetologistScheduleBuilder()
                            .withCosmetologistName((String) result[0])
                            .withAppointmentTime((LocalDateTime) result[1])
                            .withProcedureName((String) result[2])
                            .withDuration((Duration) result[3])
                            .withPrice((BigDecimal) result[4])
                            .withCustomerName((String) result[5])
                            .withCustomerPhone((String) result[6])
                            .build());
        }
        return cosmetologistSchedules;
    }

}

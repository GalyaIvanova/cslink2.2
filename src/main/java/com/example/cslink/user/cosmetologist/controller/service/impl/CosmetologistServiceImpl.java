package com.example.cslink.user.cosmetologist.controller.service.impl;

import com.example.cslink.user.cosmetologist.controller.mappers.impl.CosmetologistMapperImpl;
import com.example.cslink.user.cosmetologist.controller.service.CosmetologistService;
import com.example.cslink.user.cosmetologist.model.dataccess.dao.CosmetologistDao;
import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDTO;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import com.example.cslink.user.customer.controller.mappers.CustomerAssembler;
import com.example.cslink.user.customer.model.dataccess.dao.CustomerDao;
import com.example.cslink.user.customer.model.entity.Customer;
import com.example.cslink.exceptions.CustomBadRequestException;
import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.management.reservation.model.dao.ReservationDao;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.procedure.model.ProcedureRepository;
import com.example.cslink.procedure.model.dto.ProcedureDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CosmetologistServiceImpl implements CosmetologistService {

    @Autowired
    private CosmetologistDao cosmetologistRepository;
    @Autowired
    private ReservationDao reservationRepository;
    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private CustomerDao customerRepository;

    @Autowired
    private CosmetologistMapperImpl cosmetologistAssembler;
    @Autowired
    private CustomerAssembler customerAssembler;

//    @Autowired
//    private ProcedureAssembler procedureAssembler;


//    @Autowired
//    public CosmetologistServiceImpl(CosmetologistRepository cosmetologistRepository, ReservationRepository reservationRepository, ProcedureRepository procedureRepository) {
//        this.cosmetologistRepository = cosmetologistRepository;
//        this.reservationRepository = reservationRepository;
//        this.procedureRepository = procedureRepository;
//    }

    @Override
    public List<CosmetologistDTO> getAllCosmetologists() {
        List<Cosmetologist> cosmetologists=cosmetologistRepository.findAll();
        return cosmetologists.stream()
                .map(cosmetologistAssembler::toCosmetologistDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Cosmetologist getCosmetologistById(Long id) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", id));
//        return convertToDto(cosmetologist);
        return cosmetologist;
    }

    @Override
    public CosmetologistDTO createCosmetologist(CosmetologistDTO cosmetologistDTO) {
        Cosmetologist cosmetologist=cosmetologistAssembler.toCosmetologistEntity(cosmetologistDTO);
        Cosmetologist savedCosmetologist=cosmetologistRepository.save(cosmetologist);
        return cosmetologistAssembler.toCosmetologistDTO(savedCosmetologist);
    }

    @Override
    public CosmetologistDTO updateCosmetologist(Long id, CosmetologistDTO cosmetologistDTO) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", id));

        Cosmetologist updatedCosmetologist=cosmetologistRepository.save(cosmetologist);
        return cosmetologistAssembler.toCosmetologistDTO(updatedCosmetologist);
    }

    @Override
    public void deleteCosmetologist(Long id) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", id));

        List<Reservation> reservations=reservationRepository.findByCosmetologist(cosmetologist);
        for (Reservation reservation : reservations) {
            reservation.setCosmetologist(null);
            reservationRepository.save(reservation);
        }

        cosmetologistRepository.delete(cosmetologist);
    }

//    @Override
//    public List<CustomerDTO> getCustomersForCosmetologist(Long cosmetologistId) {
//        List<Customer> customers=cosmetologistRepository.getCustomers(cosmetologistId);
//        return customers.stream()
//                .map(customerAssembler::toDtoModel)
//                .collect(Collectors.toList());
//    }

    @Override
    public void addCustomerToCosmetologist(Long cosmetologistId, Long customerId) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(cosmetologistId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));

        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomResourceNotFoundException("customer", customerId));

        cosmetologist.addCustomer(customer);
        cosmetologistRepository.save(cosmetologist);
    }

    @Override
    public void removeCustomerFromCosmetologist(Long cosmetologistId, Long customerId) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(cosmetologistId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));

        customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomResourceNotFoundException("customer", customerId));

        cosmetologist.removeCustomer(customerId);
        cosmetologistRepository.save(cosmetologist);
    }

    @Override
    public Cosmetologist removeProcedureFromCosmetologist(Long cosmetologistId, Long procedureId) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(cosmetologistId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));

        procedureRepository.findById(procedureId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure", procedureId));

        cosmetologist.removeProcedure(procedureId);
        return cosmetologistRepository.save(cosmetologist);
    }

    //todo
    @Override
    public List<ProcedureDTO> getProceduresForCosmetologist(Long cosmetologistId) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(cosmetologistId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));
        return procedureRepository.findByCosmetologists(cosmetologist);
    }

    @Override
    public Cosmetologist addProcedureToCosmetologist(Long cosmetologistId, Long procedureId) {
        Cosmetologist cosmetologist=cosmetologistRepository.findById(cosmetologistId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));

        Procedure procedure=procedureRepository.findById(procedureId)
                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure", procedureId));

        if (cosmetologist.getProcedures().contains(procedure)) {
            throw new CustomBadRequestException("Cosmetologist already has this procedure");
        }

        cosmetologist.addProcedure(procedure);
        return cosmetologistRepository.save(cosmetologist);
    }

//    @Override
//    public Cosmetologist saveCosmetologist(Cosmetologist cosmetologist) {
//        return cosmetologistRepository.save(cosmetologist);
//    }
//
//    @Override
//    public List<Cosmetologist> getAllCosmetologists() {
//        return cosmetologistRepository.findAll();
//    }
//
//    @Override
//    public Cosmetologist getCosmetologistById(Long id) {
//        return cosmetologistRepository.findById(id)
//                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", id));
//    }
//
//    @Override
//    public CosmetologistDTO createCosmetologist(CosmetologistDTO cosmetologistDTO) {
//        return null;
//    }
//
//    @Override
//    public CosmetologistDTO updateCosmetologist(Long cosmetologistId, CosmetologistDTO cosmetologistDTO) {
//        return null;
//    }
//
//    @Override
//    public void deleteCosmetologist(Long cosmetologistId) {
//
//    }
//
//    @Override
//    public void deleteCosmetologistById(Long id) {
//        cosmetologistRepository.deleteById(id);
//    }
//
//    @Override
//    public List<Procedure> getProceduresForCosmetologist(Long cosmetologistId) {
//        Cosmetologist cosmetologist = getCosmetologistById(cosmetologistId);
//        return cosmetologist.getProcedures();
//    }
//
//    @Override
//    public void addProcedureToCosmetologist(Long cosmetologistId, Long procedureId) {
//
//    }
//
//    @Override
//    public void addProcedureToCosmetologist(Long cosmetologistId, Procedure procedure) {
//        Cosmetologist cosmetologist = getCosmetologistById(cosmetologistId);
//        List<Procedure> procedures = cosmetologist.getProcedures();
//        procedures.add(procedure);
//        cosmetologistRepository.save(cosmetologist);
//    }
//
//    @Override
//    public void removeProcedureFromCosmetologist(Long cosmetologistId, Long procedureId) {
//        Cosmetologist cosmetologist = getCosmetologistById(cosmetologistId);
//        List<Procedure> procedures = cosmetologist.getProcedures();
//        procedures.removeIf(procedure -> procedure.getId().equals(procedureId));
//        cosmetologistRepository.save(cosmetologist);
//    }
//
//    @Override
//    public Set<customer> getcustomersForCosmetologist(Long cosmetologistId) {
//        Cosmetologist cosmetologist = getCosmetologistById(cosmetologistId);
//        return cosmetologist.getcustomers();
//    }
//
//    @Override
//    public void addcustomerToCosmetologist(Long cosmetologistId, Long customerId) {
//
//    }
//
//    @Override
//    public void addcustomerToCosmetologist(Long cosmetologistId, customer customer) {
//        Cosmetologist cosmetologist = getCosmetologistById(cosmetologistId);
//        List<customer> customers = cosmetologist.getcustomers();
//        customers.add(customer);
//        cosmetologistRepository.save(cosmetologist);
//    }
//
//    @Override
//    public void removecustomerFromCosmetologist(Long cosmetologistId, Long customerId) {
//        Cosmetologist cosmetologist = getCosmetologistById(cosmetologistId);
//        List<customer> customers = cosmetologist.getcustomers();
//        customers.removeIf(customer -> customer.getId().equals(customerId));
//        cosmetologistRepository.save(cosmetologist);
//    }
}


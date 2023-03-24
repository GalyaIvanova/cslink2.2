package com.example.cslink.procedure.model;

import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import com.example.cslink.procedure.model.dto.ProcedureDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<ProcedureDTO> findByCosmetologists(Cosmetologist cosmetologist);
}
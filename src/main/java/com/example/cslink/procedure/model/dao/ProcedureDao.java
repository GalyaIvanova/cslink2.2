package com.example.cslink.procedure.model.dao;

import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import com.example.cslink.procedure.model.dto.ProcedureDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureDao extends JpaRepository<Procedure, Long> {
    List<ProcedureDto> findByCosmetologists(Cosmetologist cosmetologist);
}
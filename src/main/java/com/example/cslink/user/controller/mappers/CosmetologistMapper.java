package com.example.cslink.user.controller.mappers;

import com.example.cslink.user.model.dto.CosmetologistDto;
import com.example.cslink.user.model.entity.Cosmetologist;

public interface CosmetologistMapper {
    CosmetologistDto toCosmetologistDTO(Cosmetologist cosmetologist);

    Cosmetologist toCosmetologistEntity(CosmetologistDto dto);
}

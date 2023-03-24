package com.example.cslink.user.cosmetologist.controller.mappers;

import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDto;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;

public interface CosmetologistMapper {
    CosmetologistDto toCosmetologistDTO(Cosmetologist cosmetologist);

    Cosmetologist toCosmetologistEntity(CosmetologistDto dto);
}

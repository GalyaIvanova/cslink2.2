package com.example.cslink.user.cosmetologist.controller.mappers;

import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDTO;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;

public interface CosmetologistMapper {
    CosmetologistDTO toCosmetologistDTO(Cosmetologist cosmetologist);

    Cosmetologist toCosmetologistEntity(CosmetologistDTO dto);
}

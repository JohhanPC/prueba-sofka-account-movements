package api_com_bank.account_movements.mappers;

import api_com_bank.account_movements.dtos.request.CuentaRequestDTO;
import api_com_bank.account_movements.dtos.response.CuentaResponseDTO;
import api_com_bank.account_movements.entities.CuentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CuentaMapper {

    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    CuentaEntity toEntity(CuentaRequestDTO dto);

    CuentaResponseDTO toDto(CuentaEntity entity);

}

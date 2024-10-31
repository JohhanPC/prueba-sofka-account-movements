package api_com_bank.account_movements.mappers;

import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.entities.MovimientosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovimientosMapper {

    MovimientosMapper INSTANCE = Mappers.getMapper(MovimientosMapper.class);

    MovimientosEntity toEntityCreate(CreateMovimientosRequestDTO dto);

    MovimientosEntity toEntityUpdate(UpdateMovimientosRequestDTO dto);

    MoviemientosResponseDTO toDto(MovimientosEntity entity);

}

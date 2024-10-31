package api_com_bank.account_movements.mappers;

import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.entities.MovimientosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovimientosMapper {

    MovimientosMapper INSTANCE = Mappers.getMapper(MovimientosMapper.class);

    @Mapping(target = "cuenta", source = "cuenta")
    MovimientosEntity toEntityCreate(CreateMovimientosRequestDTO dto);

    MovimientosEntity toEntityUpdate(UpdateMovimientosRequestDTO dto);

    @Mapping(target = "cuenta", source = "cuenta")
    MoviemientosResponseDTO toDto(MovimientosEntity entity);

}

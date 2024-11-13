package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.entities.MovimientosEntity;
import api_com_bank.account_movements.exceptions.ClientErrorException;
import api_com_bank.account_movements.mappers.MovimientosMapper;
import api_com_bank.account_movements.repositories.MovimientosRepository;
import api_com_bank.account_movements.services.contracts.IMovimientosServices;
import api_com_bank.account_movements.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoviemientosServices implements IMovimientosServices {

    private final MovimientosRepository movimientosRepository;

    @Override
    public ResponseDTO createCuenta(CreateMovimientosRequestDTO createMovimientosRequestDTO) {
        log.info("Creando movimiento: {}", createMovimientosRequestDTO);
        MovimientosEntity movimientosEntity = MovimientosMapper.INSTANCE.toEntityCreate(createMovimientosRequestDTO);
        movimientosRepository.save(movimientosEntity);

        return new ResponseDTO(Messages.MOVIMIENTO_CREADO, "00", new Date());
    }

    @Override
    public ResponseDTO updateCuenta(UpdateMovimientosRequestDTO updateMovimientosRequestDTO) {
        MovimientosEntity movimientoToUpdate = movimientosRepository.findById(updateMovimientosRequestDTO.getId())
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.MOVIMIENTO_NO_ENCONTRADO, updateMovimientosRequestDTO.getId())));

        MovimientosEntity movimientosEntity = MovimientosMapper.INSTANCE.toEntityUpdate(updateMovimientosRequestDTO);
        movimientosEntity.setId(movimientoToUpdate.getId());
        movimientosRepository.save(movimientosEntity);

        return new ResponseDTO(Messages.MOVIMIENTO_ACTUALIZADO, "00", new Date());
    }

    @Override
    public ResponseDTO deleteCuenta(Long id) {
        MovimientosEntity movimientoToDelete = movimientosRepository.findById(id)
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.MOVIMIENTO_NO_ENCONTRADO, id)));

        movimientosRepository.delete(movimientoToDelete);
        return new ResponseDTO(Messages.MOVIMIENTO_ELIMINADO, "00", new Date());
    }

    @Override
    public MoviemientosResponseDTO getMovimientos(Long id) {
        return movimientosRepository.findById(id)
                .map(MovimientosMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ClientErrorException(String.format(Messages.MOVIMIENTO_NO_ENCONTRADO, id)));
    }
}

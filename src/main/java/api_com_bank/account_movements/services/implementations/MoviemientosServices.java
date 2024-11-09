package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.entities.MovimientosEntity;
import api_com_bank.account_movements.mappers.MovimientosMapper;
import api_com_bank.account_movements.repositories.MovimientosRepository;
import api_com_bank.account_movements.services.contracts.IMovimientosServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoviemientosServices implements IMovimientosServices {

    private final MovimientosRepository movimientosRepository;
    @Override
    public ResponseDTO createCuenta(CreateMovimientosRequestDTO createMovimientosRequestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            log.info("Creando movimiento: {}", createMovimientosRequestDTO);
            MovimientosEntity movimientosEntity = MovimientosMapper.INSTANCE.toEntityCreate(createMovimientosRequestDTO);
            movimientosRepository.save(movimientosEntity);

            response.setMessage("Movimiento creado exitosamente");
            response.setRc("00");
            response.setDate(new Date());

        } catch (Exception ex) {
            response.setMessage("Error al crear el movimiento: " + ex.getMessage());
            response.setRc("99");
            response.setDate(new Date());
        }
        return response;
    }

    @Override
    public ResponseDTO updateCuenta(UpdateMovimientosRequestDTO updateMovimientosRequestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<MovimientosEntity> movimientoOptional = movimientosRepository.findById(updateMovimientosRequestDTO.getId());
            if (movimientoOptional.isPresent()) {
                MovimientosEntity movimientoToUpdate = MovimientosMapper.INSTANCE.toEntityUpdate(updateMovimientosRequestDTO);
                movimientoToUpdate.setId(movimientoOptional.get().getId());
                movimientosRepository.save(movimientoToUpdate);

                response.setMessage("Movimiento actualizado exitosamente");
                response.setRc("00");
            } else {
                response.setMessage("Movimiento no encontrado");
                response.setRc("01");
            }
        } catch (Exception ex) {
            response.setMessage("Error al actualizar el movimiento: " + ex.getMessage());
            response.setRc("99");
        }
        response.setDate(new Date());
        return response;
    }

    @Override
    public ResponseDTO deleteCuenta(Long id) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<MovimientosEntity> movimientoOptional = movimientosRepository.findById(id);
            if (movimientoOptional.isPresent()) {
                movimientosRepository.delete(movimientoOptional.get());
                response.setMessage("Movimiento eliminado exitosamente");
                response.setRc("00");
            } else {
                response.setMessage("Movimiento no encontrado");
                response.setRc("01");
            }
        } catch (Exception ex) {
            response.setMessage("Error al eliminar el movimiento: " + ex.getMessage());
            response.setRc("99");
        }
        response.setDate(new Date());
        return response;
    }

    @Override
    public MoviemientosResponseDTO getMovimientos(Long id) {
        try {
            Optional<MovimientosEntity> movimientoOptional = movimientosRepository.findById(id);
            return movimientoOptional.map(MovimientosMapper.INSTANCE::toDto).orElse(null);
        } catch (Exception ex) {
            log.error("Error al obtener el movimiento: {}", ex.getMessage());
            return null;
        }
    }
}

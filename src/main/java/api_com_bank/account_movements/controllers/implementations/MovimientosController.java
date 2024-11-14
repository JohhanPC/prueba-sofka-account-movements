package api_com_bank.account_movements.controllers.implementations;

import api_com_bank.account_movements.controllers.contracts.IMovimientosController;
import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.services.contracts.IHandlerMessage;
import api_com_bank.account_movements.services.contracts.IMovimientosServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class MovimientosController implements IMovimientosController {

    private final IMovimientosServices movimientosServices;
    private final IHandlerMessage handlerMessage;

    @Override
    public ResponseEntity<ResponseDTO> createCuenta(CreateMovimientosRequestDTO createMovimientosRequestDTO) {
        ResponseDTO response = movimientosServices.createCuenta(createMovimientosRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateCuenta(UpdateMovimientosRequestDTO updateMovimientosRequestDTO) {
        ResponseDTO response = movimientosServices.updateCuenta(updateMovimientosRequestDTO);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCuenta(Long id) {
        ResponseDTO response = movimientosServices.deleteCuenta(id);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<MoviemientosResponseDTO> getMovimientos(Long id) {
        MoviemientosResponseDTO movimiento = movimientosServices.getMovimientos(id);
        if (movimiento != null) {
            return new ResponseEntity<>(movimiento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getAccountReport(String clientId, String startDate, String endDate) {
        handlerMessage.sendMessage(clientId);
        ResponseDTO response = new ResponseDTO("Solicitud de reporte enviada", "00", new Date());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

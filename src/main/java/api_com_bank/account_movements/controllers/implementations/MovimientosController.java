package api_com_bank.account_movements.controllers.implementations;

import api_com_bank.account_movements.controllers.contracts.IMovimientosController;
import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.services.contracts.IMovimientosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovimientosController implements IMovimientosController {

    private final IMovimientosServices movimientosServices;

    @Autowired
    public MovimientosController(IMovimientosServices movimientosServices) {
        this.movimientosServices = movimientosServices;
    }

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
}

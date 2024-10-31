package api_com_bank.account_movements.controllers.implementations;

import api_com_bank.account_movements.controllers.contracts.ICuentaController;
import api_com_bank.account_movements.dtos.request.CuentaRequestDTO;
import api_com_bank.account_movements.dtos.response.CuentaResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.services.contracts.ICuentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuentaController implements ICuentaController {

    private final ICuentaServices cuentaServices;

    @Autowired
    public CuentaController(ICuentaServices cuentaServices) {
        this.cuentaServices = cuentaServices;
    }

    @Override
    public ResponseEntity<ResponseDTO> createCuenta(CuentaRequestDTO cuentaRequestDTO) {
        ResponseDTO response = cuentaServices.createCuenta(cuentaRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateCuenta(CuentaRequestDTO cuentaRequestDTO) {
        ResponseDTO response = cuentaServices.updateCuenta(cuentaRequestDTO);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteCuenta(String numeroCuenta) {
        ResponseDTO response = cuentaServices.deleteCuenta(numeroCuenta);
        HttpStatus status = response.getRc().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    public ResponseEntity<CuentaResponseDTO> getCuenta(String numeroCuenta) {
        CuentaResponseDTO cuenta = cuentaServices.getCuenta(numeroCuenta);
        if (cuenta != null) {
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

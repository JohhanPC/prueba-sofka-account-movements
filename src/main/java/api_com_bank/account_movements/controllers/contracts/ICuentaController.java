package api_com_bank.account_movements.controllers.contracts;

import api_com_bank.account_movements.dtos.request.CuentaRequestDTO;
import api_com_bank.account_movements.dtos.response.CuentaResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/cuentas")
public interface ICuentaController {

    @PostMapping(path = "/create")
    ResponseEntity<ResponseDTO> createCuenta(CuentaRequestDTO cuentaRequestDTO);

    @PutMapping(path = "/update")
    ResponseEntity<ResponseDTO> updateCuenta(CuentaRequestDTO cuentaRequestDTO);

    @DeleteMapping(path = "/delete/{numeroCuenta}")
    ResponseEntity<ResponseDTO> deleteCuenta(@PathVariable String numeroCuenta);

    @GetMapping(path = "/get/{numeroCuenta}")
    ResponseEntity<CuentaResponseDTO> getCuenta(@PathVariable String numeroCuenta);

}

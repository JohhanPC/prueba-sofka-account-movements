package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.request.CuentaRequestDTO;
import api_com_bank.account_movements.dtos.response.CuentaResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;

public interface ICuentaServices {

    ResponseDTO createCuenta(CuentaRequestDTO cuentaRequestDTO);

    ResponseDTO updateCuenta(CuentaRequestDTO cuentaRequestDTO);

    ResponseDTO deleteCuenta(String numeroCuenta);

    CuentaResponseDTO getCuenta(String numeroCuenta);

}

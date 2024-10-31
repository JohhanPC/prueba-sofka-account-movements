package api_com_bank.account_movements.services.contracts;

import api_com_bank.account_movements.dtos.request.CreateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.request.UpdateMovimientosRequestDTO;
import api_com_bank.account_movements.dtos.response.MoviemientosResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import org.springframework.web.bind.annotation.*;

public interface IMovimientosServices {

    ResponseDTO createCuenta(CreateMovimientosRequestDTO createMovimientosRequestDTO);

    ResponseDTO updateCuenta(UpdateMovimientosRequestDTO updateMovimientosRequestDTO);

    ResponseDTO deleteCuenta(@PathVariable Long id);

    MoviemientosResponseDTO getMovimientos(@PathVariable Long id);

}

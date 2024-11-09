package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.request.CuentaRequestDTO;
import api_com_bank.account_movements.dtos.response.CuentaResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.entities.CuentaEntity;
import api_com_bank.account_movements.mappers.CuentaMapper;
import api_com_bank.account_movements.repositories.CuentaRepository;
import api_com_bank.account_movements.services.contracts.ICuentaServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuentaServices implements ICuentaServices {

    private final CuentaRepository cuentaRepository;

    @Override
    public ResponseDTO createCuenta(CuentaRequestDTO cuentaRequestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            log.info("Creando cuenta: {}", cuentaRequestDTO);
            CuentaEntity cuentaEntity = CuentaMapper.INSTANCE.toEntity(cuentaRequestDTO);
            cuentaRepository.save(cuentaEntity);

            response.setMessage("Cuenta creada exitosamente");
            response.setRc("00");
            response.setDate(new Date());

        } catch (Exception ex) {
            response.setMessage("Error al crear la cuenta: " + ex.getMessage());
            response.setRc("99");
            response.setDate(new Date());
        }
        return response;
    }

    @Override
    public ResponseDTO updateCuenta(CuentaRequestDTO cuentaRequestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<CuentaEntity> cuentaOptional = cuentaRepository.findById(cuentaRequestDTO.getNumeroCuenta());
            if (cuentaOptional.isPresent()) {
                CuentaEntity cuentaToUpdate = CuentaMapper.INSTANCE.toEntity(cuentaRequestDTO);
                cuentaToUpdate.setNumeroCuenta(cuentaOptional.get().getNumeroCuenta());
                cuentaRepository.save(cuentaToUpdate);

                response.setMessage("Cuenta actualizada exitosamente");
                response.setRc("00");
            } else {
                response.setMessage("Cuenta no encontrada");
                response.setRc("01");
            }
        } catch (Exception ex) {
            response.setMessage("Error al actualizar la cuenta: " + ex.getMessage());
            response.setRc("99");
        }
        response.setDate(new Date());
        return response;
    }

    @Override
    public ResponseDTO deleteCuenta(String numeroCuenta) {
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<CuentaEntity> cuentaOptional = cuentaRepository.findById(numeroCuenta);
            if (cuentaOptional.isPresent()) {
                cuentaRepository.delete(cuentaOptional.get());
                response.setMessage("Cuenta eliminada exitosamente");
                response.setRc("00");
            } else {
                response.setMessage("Cuenta no encontrada");
                response.setRc("01");
            }
        } catch (Exception ex) {
            response.setMessage("Error al eliminar la cuenta: " + ex.getMessage());
            response.setRc("99");
        }
        response.setDate(new Date());
        return response;
    }

    @Override
    public CuentaResponseDTO getCuenta(String numeroCuenta) {
        try {
            Optional<CuentaEntity> cuentaOptional = cuentaRepository.findById(numeroCuenta);
            return cuentaOptional.map(CuentaMapper.INSTANCE::toDto).orElse(null);
        } catch (Exception ex) {
            log.error("Error al obtener la cuenta: {}", ex.getMessage());
            return null;
        }
    }
}

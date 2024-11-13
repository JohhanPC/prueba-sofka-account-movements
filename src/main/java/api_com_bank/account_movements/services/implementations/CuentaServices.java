package api_com_bank.account_movements.services.implementations;

import api_com_bank.account_movements.dtos.request.CuentaRequestDTO;
import api_com_bank.account_movements.dtos.response.CuentaResponseDTO;
import api_com_bank.account_movements.dtos.response.ResponseDTO;
import api_com_bank.account_movements.entities.CuentaEntity;
import api_com_bank.account_movements.exceptions.ClientErrorException;
import api_com_bank.account_movements.mappers.CuentaMapper;
import api_com_bank.account_movements.repositories.CuentaRepository;
import api_com_bank.account_movements.services.contracts.ICuentaServices;
import api_com_bank.account_movements.utils.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuentaServices implements ICuentaServices {

    private final CuentaRepository cuentaRepository;

    @Override
    public ResponseDTO createCuenta(CuentaRequestDTO cuentaRequestDTO) {
        log.info("Creando cuenta: {}", cuentaRequestDTO);
        CuentaEntity cuentaEntity = CuentaMapper.INSTANCE.toEntity(cuentaRequestDTO);
        cuentaRepository.save(cuentaEntity);

        return new ResponseDTO(Messages.CUENTA_CREADA, "00", new Date());
    }

    @Override
    public ResponseDTO updateCuenta(CuentaRequestDTO cuentaRequestDTO) {
        log.info("Actualizando cuenta: {}", cuentaRequestDTO);
        CuentaEntity cuentaUpdate = cuentaRepository.findById(cuentaRequestDTO.getNumeroCuenta())
                .orElseThrow(() -> new ClientErrorException(Messages.CUENTA_NO_ENCONTRADA + cuentaRequestDTO.getNumeroCuenta()));

        CuentaEntity cuentaEntity = CuentaMapper.INSTANCE.toEntity(cuentaRequestDTO);
        cuentaEntity.setNumeroCuenta(cuentaUpdate.getNumeroCuenta());
        cuentaRepository.save(cuentaEntity);

        return new ResponseDTO(Messages.CUENTA_ACTUALIZADA, "00", new Date());
    }

    @Override
    public ResponseDTO deleteCuenta(String numeroCuenta) {
        CuentaEntity cuentaDelete = cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new ClientErrorException(Messages.CUENTA_NO_ENCONTRADA + numeroCuenta));
        cuentaRepository.delete(cuentaDelete);
        return new ResponseDTO(Messages.CUENTA_ELIMINADA, "00", new Date());
    }

    @Override
    public CuentaResponseDTO getCuenta(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta)
                .map(CuentaMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ClientErrorException(Messages.CUENTA_NO_ENCONTRADA + numeroCuenta));
    }
}

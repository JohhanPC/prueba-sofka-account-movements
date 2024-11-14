package api_com_bank.account_movements.dtos.response;

import api_com_bank.account_movements.entities.CuentaEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MoviemientosResponseDTO {

    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private CuentaEntity cuenta;
}

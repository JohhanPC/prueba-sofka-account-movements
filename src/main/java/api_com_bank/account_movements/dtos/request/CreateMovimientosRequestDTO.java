package api_com_bank.account_movements.dtos.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateMovimientosRequestDTO {

    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private String numeroCuenta;

}

package api_com_bank.account_movements.dtos.request;

import lombok.Data;

@Data
public class CuentaRequestDTO {

    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldo;
    private boolean estado;
    private String clienteId;

}

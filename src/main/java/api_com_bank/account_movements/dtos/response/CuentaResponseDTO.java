package api_com_bank.account_movements.dtos.response;

import lombok.Data;

@Data
public class CuentaResponseDTO {

    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldo;
    private boolean estado;
    private String clienteId;

}

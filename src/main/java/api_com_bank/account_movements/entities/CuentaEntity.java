package api_com_bank.account_movements.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUENTA")
public class CuentaEntity {

    @Id
    @Column(name = "NUMERO_CUENTA", nullable = false)
    private String numeroCuenta;

    @Column(name = "TIPO_CUENTA", nullable = false)
    private String tipoCuenta;

    @Column(name = "SALDO", nullable = false)
    private Double saldo;

    @Column(name = "ESTADO", nullable = false)
    private boolean estado;

    @Column(name = "CLIENTE_ID", nullable = false)
    private String clienteId;

}

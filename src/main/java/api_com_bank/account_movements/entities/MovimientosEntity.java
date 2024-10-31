package api_com_bank.account_movements.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIMIENTOS")
public class MovimientosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "TIPO_MOVIMIENTO", nullable = false)
    private String tipoMovimiento;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "SALDO", nullable = false)
    private Double saldo;

    @Column(name = "NUMERO_CUENTA", nullable = false)
    private String numeroCuenta;

}

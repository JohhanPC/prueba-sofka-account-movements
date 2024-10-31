package api_com_bank.account_movements.repositories;

import api_com_bank.account_movements.entities.MovimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientosRepository extends JpaRepository<MovimientosEntity, Long> {
}

package api_com_bank.account_movements.repositories;

import api_com_bank.account_movements.entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaEntity, String> {
}

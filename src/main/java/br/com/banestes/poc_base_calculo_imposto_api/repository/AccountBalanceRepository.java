package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
    List<AccountBalance> findByCodTribCodigo(String codTribCodigo);
}
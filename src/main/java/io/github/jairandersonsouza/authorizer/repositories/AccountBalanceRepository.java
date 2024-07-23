package io.github.jairandersonsouza.authorizer.repositories;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;


@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<AccountBalance> findByAccountIdAndMcc(String id, MccEnum mcc);

    @Modifying
    @Query(value = "UPDATE t_account_balance SET balance = :balance WHERE id = :id", nativeQuery = true)
    void update(BigDecimal balance, String id);
}

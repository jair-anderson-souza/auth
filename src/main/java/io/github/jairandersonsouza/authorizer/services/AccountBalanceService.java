package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.factories.PaymentFactory;
import io.github.jairandersonsouza.authorizer.repository.AccountBalanceRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    private static final Logger log = LoggerFactory.getLogger(PaymentFactory.class);

    @Transactional
    public AccountBalance getAccountIdAndMcc(String id, MccEnum mcc) {
        final var account = this.accountBalanceRepository.findByAccountIdAndMcc(id, mcc);
        return account.orElseThrow(AccountNotExistsException::new);
    }

    public void save(AccountBalance account) {
        try {
            this.accountBalanceRepository.save(account);
        } catch (Exception e) {
            log.info("[AccountBalanceService]: {}", account);
            throw new TransactionRejectedException();
        }
    }
}

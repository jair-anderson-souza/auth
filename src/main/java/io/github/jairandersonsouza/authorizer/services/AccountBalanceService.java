package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.factories.PaymentFactory;
import io.github.jairandersonsouza.authorizer.repository.AccountBalanceRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;


    @Transactional
    public AccountBalance getValidAccount(TransactionInput transactionInput) {
        final var account = getAccount(transactionInput);
        if (!amountIsValid(account, transactionInput)) {
            throw new TransactionRejectedException();
        }
        return account;
    }

    public void save(AccountBalance account) {
        try {
            this.accountBalanceRepository.save(account);
        } catch (Exception e) {
            throw new TransactionRejectedException();
        }
    }

    private AccountBalance getAccount(TransactionInput transactionInput) {
        return this.accountBalanceRepository.findByAccountIdAndMcc(transactionInput.getAccount(), MccEnum.getMcc(transactionInput.getMcc())).orElseThrow(AccountNotExistsException::new);

    }

    private boolean amountIsValid(AccountBalance account, TransactionInput transactionInput) {
        return account.amountGteThan(transactionInput);
    }
}

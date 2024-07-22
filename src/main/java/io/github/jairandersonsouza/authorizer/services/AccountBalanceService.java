package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.AccountBalanceRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;


    public AccountBalance getAccount(TransactionInput transactionInput) {
        return this.accountBalanceRepository.findByAccountIdAndMcc(transactionInput.getAccount(), MccEnum.getMcc(transactionInput.getMcc())).orElseThrow(AccountNotExistsException::new);
    }

    public void save(AccountBalance account) {
        try {
            this.accountBalanceRepository.save(account);
        } catch (Exception e) {
            throw new TransactionRejectedException();
        }
    }

}

package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.repository.AccountBalanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;


    @Transactional
    public AccountBalance getAccount(String id) {
        final var account = this.accountBalanceRepository.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotExistsException();
        }
        return account.get();
    }

    public void save(AccountBalance account) {
        this.accountBalanceRepository.save(account);
    }
}

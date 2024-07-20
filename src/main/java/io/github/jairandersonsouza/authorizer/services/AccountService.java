package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.Account;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    public Account getAccount(String id) {
        final var account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotExistsException();
        }
        return account.get();
    }

    public void save(Account account) {
        this.accountRepository.save(account);
    }
}

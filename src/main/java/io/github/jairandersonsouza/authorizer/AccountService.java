package io.github.jairandersonsouza.authorizer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Transactional
    public Account getAccount(String id, BigDecimal balance) {
        final var account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            //wrap up exception
            throw new RuntimeException("Error account doesnt exist");
        }
        return account.get();
    }

    public void save(Account account) {
        this.accountRepository.save(account);
    }
}

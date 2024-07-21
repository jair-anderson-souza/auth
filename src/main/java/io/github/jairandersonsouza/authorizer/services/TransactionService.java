
package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.processors.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private AccountBalanceService accountBalanceService;

//    @Autowired
//    private Map<String, TransactionProcessor> adapters;

    @Autowired
    private TransactionRepository transactionRepository;

    public void save(Transaction transaction) {
        try {
            this.transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new TransactionRejectedException();
        }
    }
}

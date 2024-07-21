package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import io.github.jairandersonsouza.authorizer.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public abstract class TransactionProcessor {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionService transactionService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void processTransaction(TransactionInput transactionInput) {
        try {
            final var account = this.accountBalanceService.getValidAccount(transactionInput);
            AccountBalance newAccount = account.debitAmount(transactionInput.getTotalAmount());
            this.accountBalanceService.save(newAccount);
            transactionService.save(Transaction.create(transactionInput));
        } catch (Exception e) {
            throw new TransactionRejectedException();
        }
    }

    public MccEnum getMcc(String mcc) {
        return MccEnum.getMcc(mcc);
    }


}

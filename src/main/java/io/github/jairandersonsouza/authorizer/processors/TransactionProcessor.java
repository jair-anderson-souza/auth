package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public abstract class TransactionProcessor {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionRepository transactionRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public void processTransaction(TransactionInput transactionInput) {
        try {
            final var account = this.accountBalanceService.getValidAccount(transactionInput);
            AccountBalance newAccount = account.debitAmount(transactionInput.getTotalAmount());
            this.accountBalanceService.save(newAccount);
            this.transactionRepository.save(Transaction.create(transactionInput));
        } catch (TransactionRejectedException e) {
            throw e;
        }
    }

    public MccEnum getMcc(String mcc) {
        return MccEnum.getMcc(mcc);
    }


}

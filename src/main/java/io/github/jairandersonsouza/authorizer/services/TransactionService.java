package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repositories.TransactionRepository;
import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public abstract class TransactionService {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void startTransaction(TransactionInput transactionInput) {
        try {
            final var account = this.authorizationService.authorizeTransaction(transactionInput);
            AccountBalance newAccount = account.debitAmount(transactionInput.getTotalAmount());
            this.accountBalanceService.save(newAccount);
            save(Transaction.create(transactionInput));
        } catch (TransactionRejectedException e) {
            throw new TransactionRejectedException();
        }
    }

    public MccEnum getMcc(String mcc) {
        return MccEnum.getMcc(mcc);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void save(Transaction transaction) {
        try {
            this.transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new TransactionRejectedException();
        }
    }


}

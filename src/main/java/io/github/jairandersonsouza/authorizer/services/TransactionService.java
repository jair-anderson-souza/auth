package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;
import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
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
            final var transactionDTO = TransactionDTO.create(transactionInput);
            AccountBalance newAccount = getAccount(transactionDTO).debitAmount(transactionInput.getTotalAmount());
            this.accountBalanceService.save(newAccount);
            save(Transaction.create(transactionDTO));
        } catch (TransactionRejectedException e) {
            throw new TransactionRejectedException();
        }
    }

    private AccountBalance getAccount(TransactionDTO transactionDTO) {
        return this.authorizationService.authorizeTransaction(transactionDTO);
    }

    public abstract String getMcc();

    @Transactional(propagation = Propagation.SUPPORTS)
    public void save(Transaction transaction) {
        try {
            this.transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new TransactionRejectedException();
        }
    }


}

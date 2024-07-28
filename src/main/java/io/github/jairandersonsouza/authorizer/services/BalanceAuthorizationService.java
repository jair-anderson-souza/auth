package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.TransactionProcess;
import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BalanceAuthorizationService implements AuthorizationService {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public AccountBalance authorizeTransaction(TransactionInput transactionInput) {
        final var transaction = TransactionProcess.create(transactionInput, this.accountBalanceService.getAccount(transactionInput));
        if (!validateTransaction(transaction)) {
            throw new TransactionRejectedException();
        }
        return transaction.getAccount();
    }


    private boolean validateTransaction(TransactionProcess transaction) {
        return transaction.amountIsValid();
    }

}

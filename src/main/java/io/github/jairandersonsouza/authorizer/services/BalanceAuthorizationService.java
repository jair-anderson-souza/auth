package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;
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
    public AccountBalance authorizeTransaction(TransactionDTO transactionDTO) {
        final var transaction = TransactionProcess.create(transactionDTO, this.accountBalanceService.getAccount(transactionDTO));
        if (!validateTransaction(transaction)) {
            throw new TransactionRejectedException();
        }
        return transaction.getAccount();
    }


    private boolean validateTransaction(TransactionProcess transaction) {
        return transaction.amountIsValid();
    }

}

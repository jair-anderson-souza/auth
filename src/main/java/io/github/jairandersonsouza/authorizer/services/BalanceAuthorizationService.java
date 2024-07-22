package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BalanceAuthorizationService implements AuthorizationService {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Override
    public AccountBalance authorizeTransaction(TransactionInput transactionInput) {
        final var account = this.accountBalanceService.getAccount(transactionInput);
        if (!isAmountValid(account, transactionInput)) {
            throw new TransactionRejectedException();
        }
        return account;
    }


    private boolean isAmountValid(AccountBalance account, TransactionInput transactionInput) {
        return account.amountGteThan(transactionInput.getTotalAmount());
    }

}

package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;


public interface AuthorizationService {

    AccountBalance authorizeTransaction(TransactionInput transactionInput);

}

package io.github.jairandersonsouza.authorizer.template;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;

public interface PaymentProcessor {

    void startTransaction(TransactionInput transactionInput);

    MccEnum getMcc();
}

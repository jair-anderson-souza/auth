package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("CASH")
public class CashTransactionService extends TransactionService {

    @Override
    public void startTransaction(TransactionInput transactionInput) {
        super.startTransaction(transactionInput);
    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.CASH;
    }

}

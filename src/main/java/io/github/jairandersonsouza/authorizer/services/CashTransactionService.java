package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("CASH")
public class CashTransactionService extends TransactionService {

    @Override
    public void startTransaction(TransactionInput transactionInput) {
        super.startTransaction(TransactionInput.create(transactionInput.getAccount(), transactionInput.getTotalAmount(), transactionInput.getMerchant(), transactionInput.getMerchant()));
    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.CASH;
    }

}

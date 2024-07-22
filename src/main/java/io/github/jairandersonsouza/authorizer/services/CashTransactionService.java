package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.processors.TransactionService;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("CASH")
public class CashTransactionService extends TransactionService {

    @Override
    public void processTransaction(TransactionInput transactionInput) {
        super.processTransaction(TransactionInput.create(transactionInput.getAccount(), transactionInput.getTotalAmount(), transactionInput.getMerchant(), transactionInput.getMerchant()));
    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.CASH;
    }

}

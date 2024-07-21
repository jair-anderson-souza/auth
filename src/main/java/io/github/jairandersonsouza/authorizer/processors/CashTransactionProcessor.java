package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("CASH")
public class CashTransactionProcessor extends TransactionProcessor {

    @Override
    public void processTransaction(TransactionInput transactionInput) {
        transactionInput.setMcc(transactionInput.getMerchant());
        super.processTransaction(transactionInput);
    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.CASH;
    }

}

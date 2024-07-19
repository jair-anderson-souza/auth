package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("CASH")
public class CashPaymentProcessor implements PaymentProcessor {

    @Override
    public void processTransaction(TransactionInput transactionInput) {

    }
    @Override
    public MccEnum getMcc() {
        return MccEnum.CASH;
    }
}

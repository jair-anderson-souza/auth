package io.github.jairandersonsouza.authorizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentProcessorFactory {

    @Autowired
    private Map<String, PaymentProcessor> processors;

    public PaymentProcessor getProcessor(TransactionInput transactionInput) {
        if (transactionInput.getMcc().equals("5411") || transactionInput.getMcc().equals("5412")) {
            return this.processors.get(MccEnum.FOOD.name());
        } else if (transactionInput.getMcc().equals("5811") || transactionInput.getMcc().equals("5812")) {
            return this.processors.get(MccEnum.MEAL.name());
        } else {
            return this.processors.get(MccEnum.CASH.name());
        }

    }

}

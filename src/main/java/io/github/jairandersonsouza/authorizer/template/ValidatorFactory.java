package io.github.jairandersonsouza.authorizer.template;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValidatorFactory {

    @Autowired
    private Map<String, PaymentProcessor> targets;

    public PaymentProcessor getProcessor(TransactionInput transactionInput) {

        if (transactionInput.getMcc().equals("5411") || transactionInput.getMcc().equals("5412")) {
            return this.targets.get(MccEnum.FOOD.name());
        } else if (transactionInput.getMcc().equals("5811") || transactionInput.getMcc().equals("5812")) {
            return this.targets.get(MccEnum.MEAL.name());
        } else {
            return this.targets.get(MccEnum.CASH.name());
        }

    }

}

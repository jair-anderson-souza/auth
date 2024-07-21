package io.github.jairandersonsouza.authorizer.factories;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.processors.TransactionProcessor;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentFactory {

    @Autowired
    private Map<String, TransactionProcessor> targets;

    public TransactionProcessor getProcessor(TransactionInput transactionInput) {
        if (isMeal(transactionInput)) {
            return this.targets.get(MccEnum.MEAL.name());
        } else if (isFood(transactionInput)) {
            return this.targets.get(MccEnum.FOOD.name());
        } else {
            return this.targets.get(MccEnum.CASH.name());
        }
    }

    public boolean isMeal(TransactionInput transactionInput) {
        return transactionInput.isMeal();
    }

    public boolean isFood(TransactionInput transactionInput) {
        return transactionInput.isFood();
    }

}

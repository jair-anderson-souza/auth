package io.github.jairandersonsouza.authorizer.factories;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;

import io.github.jairandersonsouza.authorizer.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class TransactionServiceFactory {

    @Autowired
    private Map<String, TransactionService> targets;

    public TransactionService getProcessor(TransactionInput transactionInput) {
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

package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentFactory {

    @Autowired
    private Map<String, PaymentProcessor> targets;

    public PaymentProcessor getProcessor(TransactionInput transactionInput, AccountBalance account) {
        if (isMealOperationValid(account, transactionInput)) {
            return this.targets.get(MccEnum.MEAL.name());
        } else if (isFoodOperationValid(account, transactionInput)) {
            return this.targets.get(MccEnum.FOOD.name());
        } else {
            return this.targets.get(MccEnum.CASH.name());
        }
    }

    boolean isMealOperationValid(AccountBalance account, TransactionInput transactionInput) {
        return isMeal(transactionInput) && account.amountGteThan(transactionInput);
    }

    public boolean isMeal(TransactionInput transactionInput) {
        return transactionInput.isMeal();
    }

    boolean isFoodOperationValid(AccountBalance account, TransactionInput transactionInput) {
        return isFood(transactionInput) && account.amountGteThan(transactionInput);
    }

    public boolean isFood(TransactionInput transactionInput) {
        return transactionInput.isFood();
    }


}

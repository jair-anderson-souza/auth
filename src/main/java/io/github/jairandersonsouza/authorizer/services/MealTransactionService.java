package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("MEAL")
public class MealTransactionService extends TransactionService {

    @Override
    public void startTransaction(TransactionInput transactionInput) {
        super.startTransaction(TransactionInput.create(transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.MEAL.name(), transactionInput.getMerchant()));
    }

    @Override
    public String getMcc() {
        return MccEnum.MEAL.name();
    }


}

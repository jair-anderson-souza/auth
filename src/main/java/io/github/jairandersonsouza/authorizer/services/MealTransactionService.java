package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.processors.TransactionService;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("MEAL")
public class MealTransactionService extends TransactionService {

    @Override
    public void processTransaction(TransactionInput transactionInput) {
        super.processTransaction(transactionInput);
    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.MEAL;
    }


}

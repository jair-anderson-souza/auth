package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MEAL")
public class MealTransactionProcessor extends TransactionProcessor {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void processTransaction(TransactionInput transactionInput) {
        super.processTransaction(transactionInput);
    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.MEAL;
    }


}

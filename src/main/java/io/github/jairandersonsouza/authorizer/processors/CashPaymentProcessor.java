package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CASH")
public class CashPaymentProcessor extends PaymentProcessor {


    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public MccEnum getMcc() {
        return MccEnum.CASH;
    }

}

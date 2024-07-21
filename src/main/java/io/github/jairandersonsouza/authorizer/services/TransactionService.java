
package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.factories.PaymentFactory;
import io.github.jairandersonsouza.authorizer.processors.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private Map<String, TransactionProcessor> adapters;

    @Autowired
    private PaymentFactory paymentFactory;


    public void transact(TransactionInput transactionInput) {
        final var account = this.accountBalanceService.getAccountIdAndMcc(transactionInput.getAccount(), MccEnum.getMcc(transactionInput.getMcc()));
        final var paymentProcessor = this.paymentFactory.getProcessor(transactionInput, account);
        paymentProcessor.processTransaction(transactionInput, account);
    }

}

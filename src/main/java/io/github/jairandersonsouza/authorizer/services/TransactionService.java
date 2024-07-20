
package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.controllers.TransactionController;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.processors.PaymentFactory;
import io.github.jairandersonsouza.authorizer.processors.PaymentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private Map<String, PaymentProcessor> adapters;

    @Autowired
    private PaymentFactory paymentFactory;

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    public void transact(TransactionInput transactionInput) {
        final var account = this.accountBalanceService.getAccount(transactionInput.getAccount(), MccEnum.getMcc(transactionInput.getMcc()));
        final var paymentProcessor = this.paymentFactory.getProcessor(transactionInput, account);
        paymentProcessor.startTransaction(transactionInput, account);
    }

}

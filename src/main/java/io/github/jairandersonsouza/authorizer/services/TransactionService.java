
package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.processors.PaymentProcessorFactory;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentProcessorFactory paymentProcessorFactory;

    public void transact(TransactionInput transactionInput) {
        final var paymentProcessor = this.paymentProcessorFactory.getProcessor(transactionInput);
        paymentProcessor.processTransaction(transactionInput);
    }
}

package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public abstract class PaymentProcessor {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger log = LoggerFactory.getLogger(PaymentFactory.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void startTransaction(TransactionInput transactionInput, AccountBalance account) {
        try {
            AccountBalance newAccount = account.debitAmount(transactionInput.getTotalAmount());
            this.accountBalanceService.save(newAccount);
            var tran = Transaction.create(transactionInput);
            this.transactionRepository.save(tran);
        } catch (TransactionRejectedException e) {
            log.info("[PaymentProcessor]: {}, {}", account, transactionInput);

            throw e;
        }

    }

    public abstract MccEnum getMcc();
}

package io.github.jairandersonsouza.authorizer.template;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service("MEAL")
public class MealPaymentProcessor implements PaymentProcessor {

    @Autowired
    private Map<String, Specification> specifications;


    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    @Override
    public void startTransaction(TransactionInput transactionInput) {
        try {
            final var account = this.accountService.getAccount(transactionInput.getAccount(), transactionInput.getTotalAmount());
            account.debit(transactionInput.getTotalAmount(), getMcc());
            this.accountService.save(account);
            var tran = new Transaction();
            tran.setId(UUID.randomUUID().toString());
            tran.setAccountId(transactionInput.getAccount());
            tran.setAmount(transactionInput.getTotalAmount());
            tran.setMerchant(transactionInput.getMerchant());
            tran.setMcc(transactionInput.getMcc());
            this.transactionRepository.save(tran);
        } catch (TransactionRejectedException e) {
            throw e;
        }
    }

    @Override
    public MccEnum getMcc() {
        return MccEnum.MEAL;
    }


}

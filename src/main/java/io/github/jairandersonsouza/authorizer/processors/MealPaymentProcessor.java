package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MEAL")
public class MealPaymentProcessor extends PaymentProcessor {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionRepository transactionRepository;

//    @Transactional
//    @Override
//    public void startTransaction(TransactionInput transactionInput) {
//        try {
//            final var account = this.accountService.getAccount(transactionInput.getAccount(), transactionInput.getTotalAmount());
//            account.debit(transactionInput.getTotalAmount(), getMcc());
//            this.accountService.save(account);
//            var tran = new Transaction();
//            tran.setId(UUID.randomUUID().toString());
//            tran.setAccountId(transactionInput.getAccount());
//            tran.setAmount(transactionInput.getTotalAmount());
//            tran.setMerchant(transactionInput.getMerchant());
//            tran.setMcc(transactionInput.getMcc());
//            this.transactionRepository.save(tran);
//        } catch (TransactionRejectedException e) {
//            throw e;
//        }
//    }

    @Override
    public MccEnum getMcc() {
        return MccEnum.MEAL;
    }


}

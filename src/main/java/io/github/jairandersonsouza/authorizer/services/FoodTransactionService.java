package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.processors.TransactionService;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.stereotype.Service;

@Service("FOOD")
public class FoodTransactionService extends TransactionService {


    @Override
    public void processTransaction(TransactionInput transactionInput) {
        super.processTransaction(transactionInput);
    }

    //    @Transactional
//    @Override
//    public void startTransaction(TransactionInput transactionInput) {
//        try {
//
//
//            final var account = this.accountService.getAccount(transactionInput.getAccount(), transactionInput.getTotalAmount());
//            account.debit(transactionInput.getTotalAmount(), getMcc());
//
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
//
//    }

    @Override
    public MccEnum getMcc(String mcc) {
        return MccEnum.FOOD;
    }

}

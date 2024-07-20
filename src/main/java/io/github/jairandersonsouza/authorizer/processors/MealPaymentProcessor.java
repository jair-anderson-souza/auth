//package io.github.jairandersonsouza.authorizer.processors;
//
//
//import io.github.jairandersonsouza.authorizer.entities.MccEnum;
//import io.github.jairandersonsouza.authorizer.entities.Transaction;
//import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
//import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
//import io.github.jairandersonsouza.authorizer.services.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.UUID;
//
//@Service("MEAL")
//public class MealPaymentProcessor implements PaymentProcessor {
//
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void processTransaction(TransactionInput transactionInput) {
//        final var account = this.accountService.getAccount(transactionInput.getAccount(), transactionInput.getTotalAmount());
//        account.debit(transactionInput.getTotalAmount(), getMcc());
//
//        this.accountService.save(account);
//        var tran = new Transaction();
//        tran.setId(UUID.randomUUID().toString());
//        tran.setAccountId(transactionInput.getAccount());
//        tran.setAmount(transactionInput.getTotalAmount());
//        tran.setMerchant(transactionInput.getMerchant());
//        tran.setMcc(transactionInput.getMcc());
//        this.transactionRepository.save(tran);
//    }
//
//    @Override
//    public MccEnum getMcc() {
//        return MccEnum.MEAL;
//    }
//}

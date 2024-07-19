
package io.github.jairandersonsouza.authorizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentProcessorFactory paymentProcessorFactory;

    public void transact(TransactionInput transactionInput) {
        final var account = this.accountService.getAccount(transactionInput.getAccount(), transactionInput.getTotalAmount());

        final var paymentProcessor = this.paymentProcessorFactory.getProcessor(transactionInput);

        //descobrindo o tipo de to saldo
        MccEnum transactionType = null;
        if (transactionInput.getMcc().equals("5411") || transactionInput.getMcc().equals("5412")) {
            transactionType = MccEnum.FOOD;
        } else if (transactionInput.getMcc().equals("5811") || transactionInput.getMcc().equals("5812")) {
            transactionType = MccEnum.MEAL;
        } else {
            transactionType = MccEnum.CASH;
        }

        MccEnum finalTransactionType = transactionType;
        final var bal = account.getBalance()
                .stream()
                .filter((it) ->
                        finalTransactionType.equals(it.getMcc()) && it.getBalance().compareTo(transactionInput.getTotalAmount()) > 0
                )
                .findFirst().get();
        final var newBalance = bal.getBalance().subtract(transactionInput.getTotalAmount());
        bal.setBalance(newBalance);
        account.addBalance(bal);
        accountService.save(account);
        var tran = new Transaction();
        tran.setId(UUID.randomUUID().toString());
        tran.setAccountId(transactionInput.getAccount());
        tran.setAmount(transactionInput.getTotalAmount());
        tran.setMerchant(transactionInput.getMerchant());
        tran.setMcc(transactionInput.getMcc());

        transactionRepository.save(tran);




    }
}

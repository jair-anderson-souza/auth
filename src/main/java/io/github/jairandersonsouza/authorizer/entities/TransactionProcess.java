package io.github.jairandersonsouza.authorizer.entities;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;

import java.math.BigDecimal;

public class TransactionProcess {

    private BigDecimal transactionBalance;
    //TODO repetido
    private BigDecimal currentAccountBalance;
    private AccountBalance account;

    public TransactionProcess(BigDecimal transactionBalance, BigDecimal currentAccountBalance, AccountBalance accountBalance) {
        this.transactionBalance = transactionBalance;
        this.currentAccountBalance = currentAccountBalance;
        this.account = accountBalance;
    }

    public BigDecimal getTransactionBalance() {
        return transactionBalance;
    }


    public BigDecimal getCurrentAccountBalance() {
        return currentAccountBalance;
    }

    public AccountBalance getAccount() {
        return account;
    }

    public boolean amountIsValid() {
        return this.transactionBalance.compareTo(this.currentAccountBalance) <= 0;
    }

    public static TransactionProcess create(TransactionDTO transactionDTO, AccountBalance account) {
        return new TransactionProcess(transactionDTO.getTotalAmount(), account.getBalance(), account);
    }
}

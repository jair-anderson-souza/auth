package io.github.jairandersonsouza.authorizer.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Embeddable
public class Balance {

    @Column(name = "balance", columnDefinition = "NUMERIC(10,2)")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private MccEnum mcc;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public MccEnum getMcc() {
        return mcc;
    }

    public void setMcc(MccEnum mcc) {
        this.mcc = mcc;
    }

    public boolean balanceIsValid(BigDecimal amountTransaction) {
        return this.balance.compareTo(amountTransaction) >= 0;
    }

    public void debitAmount(BigDecimal amountTransaction) {
        if (balanceIsValid(amountTransaction)) {
            this.balance = this.balance.subtract(amountTransaction);
        }

    }


}

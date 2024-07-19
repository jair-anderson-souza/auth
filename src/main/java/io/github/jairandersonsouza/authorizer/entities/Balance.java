package io.github.jairandersonsouza.authorizer.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Embeddable
public class Balance {

    //TODO
    //id - UUID - gerar na aplicação, é mais rápido
//    @Id
//    private String id;

//    @Column(name = "account_id")
//    private String accountId;

    //add @Column com valores limites
    //validar, trocar por Money
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private MccEnum mcc;


//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

//    public String getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(String accountId) {
//        this.accountId = accountId;
//    }

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


    public void debitAmount(BigDecimal amountTransaction) {
        this.balance = this.balance.subtract(amountTransaction);
    }


}

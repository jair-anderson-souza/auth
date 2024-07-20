package io.github.jairandersonsouza.authorizer.entities;


import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_account_balance")
public class AccountBalance implements Serializable {

    //TODO
//    //id - UUID - gerar na aplicação, é mais rápido
    @Id
    @Column(name = "id")
    private String id;
//

    @Column(name = "balance", columnDefinition = "NUMERIC(10,2)")
    private BigDecimal balance;

    @Column(name = "mcc")
    @Enumerated(EnumType.STRING)
    private MccEnum mcc;

    @Column(name = "company_name")
    private String companyName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public void debit(BigDecimal amountTransaction, MccEnum mcc) {
//        if (this.getMcc().equals(mcc)) {
        this.debitAmount(amountTransaction);

//        }
    }

    public void debitAmount(BigDecimal amountTransaction) {
//        if (balanceIsValid(amountTransaction)) {
        this.balance = this.balance.subtract(amountTransaction);
//        }

    }


    public boolean amountGteThan(TransactionInput transactionInput) {
        return this.getBalance().compareTo(transactionInput.getTotalAmount()) >= 0;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalance that = (AccountBalance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

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

    public AccountBalance() {
    }

    public AccountBalance(String id, BigDecimal balance, MccEnum mcc, String companyName) {
        this.id = id;
        this.balance = balance;
        this.mcc = mcc;
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }


    public BigDecimal getBalance() {
        return balance;
    }


    public MccEnum getMcc() {
        return mcc;
    }


    public String getCompanyName() {
        return companyName;
    }


    public void debit(BigDecimal amountTransaction) {
        //TODO
        //retornar outra instância de AccountBalance
        this.balance = this.balance.subtract(amountTransaction);

    }

    public boolean amountGteThan(TransactionInput transactionInput) {
        return this.balance.compareTo(transactionInput.getTotalAmount()) >= 0;

    }

    public AccountBalance id(String idAccount) {
        this.id = idAccount;
        return this;
    }

    public AccountBalance mcc(MccEnum mcc) {
        this.mcc = mcc;
        return this;
    }

    public AccountBalance balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public static AccountBalance builder() {
        return new AccountBalance();
    }

    public AccountBalance build() {
        return this;
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

package io.github.jairandersonsouza.authorizer.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_account_balance")
public final class AccountBalance implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "account_id")
    private String accountId;


    @Column(name = "balance", columnDefinition = "NUMERIC(10,2)")
    private BigDecimal balance;

    @Column(name = "mcc")
    @Enumerated(EnumType.STRING)
    private MccEnum mcc;

    @Column(name = "company_name")
    private String companyName;

    private AccountBalance() {
    }

    private AccountBalance(String id, String accountId, BigDecimal balance, MccEnum mcc, String companyName) {
        this.id = id;
        this.accountId = accountId;
        this.balance = balance;
        this.mcc = mcc;
        this.companyName = companyName;
    }


    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
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

    public AccountBalance debitAmount(BigDecimal amountTransaction) {
        final var balance = this.balance.subtract(amountTransaction);
        return new AccountBalance(this.id, this.accountId, balance, this.mcc, this.companyName);
    }

    public boolean amountGteThan(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

    public static AccountBalance create(String id, String accountId, BigDecimal balance, MccEnum mcc, String companyName) {
        return new AccountBalance(id, accountId, balance, mcc, companyName);
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

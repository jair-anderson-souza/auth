package io.github.jairandersonsouza.authorizer.entities;

import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "t_transaction")
public final class Transaction implements Serializable {

    //TODO
    //id - UUID - gerar na aplicação, é mais rápido
    @Id
    private String id;

    @Column(name = "account_id")
    private String accountId;

    //add @Column com valores limites
    //validar, trocar por Money
    private BigDecimal amount;

    private String merchant;

    //TODO
    //validar, trocar por int, limite de 4 numeros
    //validar bean validation
    private String mcc;

    public String getId() {
        return id;
    }

    private Transaction() {
    }

    private Transaction(String id, String accountId, BigDecimal amount, String merchant, String mcc) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.merchant = merchant;
        this.mcc = mcc;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getMcc() {
        return mcc;
    }

    public static Transaction create(TransactionInput transactionInput) {
        return new Transaction(UUID.randomUUID().toString(), transactionInput.getAccount(), transactionInput.getTotalAmount(), transactionInput.getMerchant(), transactionInput.getMcc());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

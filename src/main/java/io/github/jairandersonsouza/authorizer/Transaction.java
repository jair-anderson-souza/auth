package io.github.jairandersonsouza.authorizer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "t_transaction")
public class Transaction implements Serializable {

    //TODO
    //id - UUID - gerar na aplicação, é mais rápido
    @Id
    private String id;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
}

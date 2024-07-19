package io.github.jairandersonsouza.authorizer;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_balance")
public class Balance {

    //TODO
    //id - UUID - gerar na aplicação, é mais rápido
    @Id
    private String id;

    //add @Column com valores limites
    //validar, trocar por Money
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private MccEnum mcc;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(id, balance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

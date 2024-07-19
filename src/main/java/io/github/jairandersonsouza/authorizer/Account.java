package io.github.jairandersonsouza.authorizer;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_account")
public class Account implements Serializable {

    //TODO
    //id - UUID - gerar na aplicação, é mais rápido
    @Id
    private String id;


    @Column(name = "company_name")
    private String companyName;

    //test outras collections
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.MERGE)
    private Set<Balance> balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Balance> getBalance() {
        return balance;
    }

    public void setBalance(Set<Balance> balance) {
        this.balance = balance;
    }

    public void addBalance(Balance bal) {
        if(this.balance == null){
            this.balance = new HashSet<>();
        }
        this.balance.add(bal);
    }
}

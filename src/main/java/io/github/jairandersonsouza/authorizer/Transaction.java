package io.github.jairandersonsouza.authorizer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
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
    private Integer mcc;





}

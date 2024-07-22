package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.requests.TransactionInput;

import java.math.BigDecimal;

public class TransactionUtil {

    private String id;
    private String idAccount;
    private String idTransaction;
    private TransactionInput transaction;


    public TransactionInput makeTransaction() {
        return TransactionInput.create(
                "1123",
                new BigDecimal(100),
                "5811",
                "Google");
    }
}

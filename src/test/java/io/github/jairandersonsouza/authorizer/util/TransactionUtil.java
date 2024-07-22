package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.requests.TransactionInput;

import java.math.BigDecimal;

public class TransactionUtil {

    //TODO validar se todos os tests estão chamando esse cara
    public static TransactionInput makeTransaction(String accountId, BigDecimal totalAmount, String mcc, String merchant) {
        return TransactionInput.create(
                accountId,
                totalAmount,
                mcc,
                merchant);
    }
}

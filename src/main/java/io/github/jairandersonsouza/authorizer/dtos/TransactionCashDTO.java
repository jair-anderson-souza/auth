package io.github.jairandersonsouza.authorizer.dtos;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionCashDTO extends TransactionDTO {

    private String mcc;

    private TransactionCashDTO(String account, BigDecimal totalAmount, String mcc, String merchant) {
        super(account, totalAmount, merchant);
        this.mcc = MccEnum.CASH.name();

    }


    public String getMcc() {
        return mcc;
    }


    public static TransactionCashDTO create(TransactionInput transactionInput) {
        return new TransactionCashDTO(
                transactionInput.getAccount(),
                transactionInput.getTotalAmount(),
                transactionInput.getMcc(),
                transactionInput.getMerchant()

        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TransactionCashDTO that = (TransactionCashDTO) o;
        return Objects.equals(mcc, that.mcc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mcc);
    }
}

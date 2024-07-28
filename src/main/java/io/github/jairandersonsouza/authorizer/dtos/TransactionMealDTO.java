package io.github.jairandersonsouza.authorizer.dtos;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionMealDTO extends TransactionDTO {

    private String mcc;

    private TransactionMealDTO(String account, BigDecimal totalAmount, String mcc, String merchant) {
        super(account, totalAmount, MccEnum.MEAL.name(), merchant);
        this.mcc = mcc;

    }

//    public String getAccount() {
//        return account;
//    }

//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }

    public String getMcc() {
        return mcc;
    }

//    public String getMerchant() {
//        return merchant;
//    }

    public static TransactionMealDTO create(TransactionInput transactionInput) {
        return new TransactionMealDTO(
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
        TransactionMealDTO that = (TransactionMealDTO) o;
        return Objects.equals(mcc, that.mcc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mcc);
    }
}

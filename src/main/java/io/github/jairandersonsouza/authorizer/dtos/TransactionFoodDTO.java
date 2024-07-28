package io.github.jairandersonsouza.authorizer.dtos;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionFoodDTO extends TransactionDTO {

    private String mcc;

    private TransactionFoodDTO(String account, BigDecimal totalAmount, String merchant) {
        super(account, totalAmount, merchant);
        this.mcc = MccEnum.FOOD.name();

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

    public static TransactionFoodDTO create(TransactionInput transactionInput) {
        return new TransactionFoodDTO(
                transactionInput.getAccount(),
                transactionInput.getTotalAmount(),
                transactionInput.getMerchant()

        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TransactionFoodDTO that = (TransactionFoodDTO) o;
        return Objects.equals(mcc, that.mcc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mcc);
    }
}

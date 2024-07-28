package io.github.jairandersonsouza.authorizer.dtos;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import java.math.BigDecimal;
import java.util.Objects;

public class TransactionDTO {

    private String account;
    private BigDecimal totalAmount;
    private String mcc;
    private String merchant;

    protected TransactionDTO(String account, BigDecimal totalAmount, String mcc, String merchant) {
        this.account = account;
        this.totalAmount = totalAmount;
        this.mcc = mcc;
        this.merchant = merchant;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMerchant() {
        return merchant;
    }

    public static TransactionDTO create(TransactionInput transactionInput) {
        return new TransactionDTO(
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
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(account, that.account) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(merchant, that.merchant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, totalAmount, merchant);
    }
}

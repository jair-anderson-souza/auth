package io.github.jairandersonsouza.authorizer.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class TransactionInput {

    @NotEmpty
    private String account;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalAmount;

    @NotEmpty
    private String mcc;

    @NotEmpty
    private String merchant;

    private TransactionInput() {
    }

    private TransactionInput(String account, BigDecimal totalAmount, String mcc, String merchant) {
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

    public boolean isMeal() {
        return this.mcc.equals("5811") || this.mcc.equals("5812");
    }

    public boolean isFood() {
        return this.mcc.equals("5411") || this.mcc.equals("5412");
    }

    public static TransactionInput create(String account, BigDecimal totalAmount, String mcc, String merchant) {
        return new TransactionInput(account, totalAmount, mcc, merchant);
    }

}

package io.github.jairandersonsouza.authorizer.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class TransactionInput {
    @NotEmpty
    private String account;
    //TODO
    //validated this bean validation
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalAmount;

    @NotEmpty
    private String mcc;

    @NotEmpty
    private String merchant;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }


    //TODO
    //remove
    @Override
    public String toString() {
        return "TransactionInput{" +
                "account='" + account + '\'' +
                ", totalAmount=" + totalAmount +
                ", mcc='" + mcc + '\'' +
                ", merchant='" + merchant + '\'' +
                '}';
    }
}

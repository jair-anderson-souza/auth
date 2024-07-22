package io.github.jairandersonsouza.authorizer.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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

    public static List<String> VALID_FOODS = List.of("HIPER", "EXTRA");
    public static List<String> VALID_MEALS = List.of("EATS", "IFOOD");

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
        return validateMcc(VALID_MEALS) || (this.mcc.equals("5811") || this.mcc.equals("5812"));
    }

    public boolean isFood() {
        return validateMcc(VALID_FOODS) || (this.mcc.equals("5411") || this.mcc.equals("5412"));
    }

    public boolean validateMcc(List<String> mccs) {
        return mccs.stream().anyMatch((value) -> this.merchant.toLowerCase().contains(value.toLowerCase()));
    }

    public static TransactionInput create(String account, BigDecimal totalAmount, String mcc, String merchant) {
        return new TransactionInput(account, totalAmount, mcc, merchant);
    }

}

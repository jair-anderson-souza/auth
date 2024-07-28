package io.github.jairandersonsouza.authorizer.dtos;


import java.math.BigDecimal;
import java.util.Objects;

public abstract class TransactionDTO {

    private String account;
    private BigDecimal totalAmount;
    private String merchant;

    protected TransactionDTO(String account, BigDecimal totalAmount, String merchant) {
        this.account = account;
        this.totalAmount = totalAmount;
        this.merchant = merchant;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getMerchant() {
        return merchant;
    }

    public abstract String getMcc();

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

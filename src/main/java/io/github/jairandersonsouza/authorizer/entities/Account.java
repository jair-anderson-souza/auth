//package io.github.jairandersonsouza.authorizer.entities;
//
//import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
//import jakarta.persistence.*;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.List;
//
//@Entity
//@Table(name = "t_account")
//public class Account implements Serializable {
//
//    //TODO
//    //id - UUID - gerar na aplicação, é mais rápido
//    @Id
//    @Column(name = "id")
//    private String id;
//
//
//    @Column(name = "company_name")
//    private String companyName;
//
//    @ElementCollection
//    @CollectionTable(name = "t_account_balance", joinColumns = @JoinColumn(name = "account_id"), foreignKey = @ForeignKey(name = "account_balance_pkey"))
//    private List<Balance> balances;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public List<Balance> getBalances() {
//        return balances;
//    }
//
//    public void setBalances(List<Balance> balances) {
//        this.balances = balances;
//    }
//
//
//    public void debit(BigDecimal amountTransaction, MccEnum mcc) {
//        for (Balance balance : balances) {
//            if (balance.getMcc().equals(mcc)) {
//                balance.debitAmount(amountTransaction);
//            }
//        }
//    }
//
//
//    public boolean amountGteThan(TransactionInput transactionInput, MccEnum mccEnum) {
//        for (Balance balance : this.balances) {
//            if (balance.getMcc().equals(mccEnum)) {
//                return balance.getBalance().compareTo(transactionInput.getTotalAmount()) >= 0;
//            }
//        }
//        return false;
//    }
//
//}

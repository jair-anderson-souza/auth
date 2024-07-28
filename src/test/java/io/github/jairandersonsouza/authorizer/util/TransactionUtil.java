package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;
import io.github.jairandersonsouza.authorizer.entities.Transaction;

import java.math.BigDecimal;

public class TransactionUtil {

    public static TransactionDTO makeTransactionDTO(String accountId, BigDecimal totalAmount, String mcc, String merchant) {
//TODO
        //        if (mcc.equals("MEAL")) {
//            return TransactionMealInput.createMeal(
//                    accountId,
//                    totalAmount,
//                    merchant);
//        } else if (mcc.equals("FOOD")) {
//            return TransactionFoodInput.createFood(
//                    accountId,
//                    totalAmount,
//                    merchant);
//        }
        return TransactionDTO.create(makeTransactionInput(
                accountId,
                totalAmount,
                mcc,
                merchant));
    }

    public static TransactionInput makeTransactionInput(String accountId, BigDecimal totalAmount, String mcc, String merchant) {
        return TransactionInput.create(
                accountId,
                totalAmount,
                mcc,
                merchant);
    }

    public static Transaction makeTransaction(TransactionDTO transactionDTO) {
        return Transaction.create(transactionDTO);
    }
}

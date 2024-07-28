package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.dtos.TransactionCashDTO;
import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;
import io.github.jairandersonsouza.authorizer.dtos.TransactionFoodDTO;
import io.github.jairandersonsouza.authorizer.dtos.TransactionMealDTO;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;

import java.math.BigDecimal;

public class TransactionUtil {

    public static TransactionDTO makeTransactionDTO(String accountId, BigDecimal totalAmount, String mcc, String merchant) {
        if (mcc.equals("MEAL")) {
            return TransactionMealDTO.create(makeTransactionInput(
                            accountId,
                            totalAmount,
                            MccEnum.MEAL.name(),
                            merchant
                    )
            );
        } else if (mcc.equals("FOOD")) {
            return TransactionFoodDTO.create(makeTransactionInput(
                            accountId,
                            totalAmount,
                            MccEnum.FOOD.name(),
                            merchant
                    )
            );
        } else {
            return TransactionCashDTO.create(makeTransactionInput(
                            accountId,
                            totalAmount,
                            MccEnum.CASH.name(),
                            merchant
                    )
            );
        }
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

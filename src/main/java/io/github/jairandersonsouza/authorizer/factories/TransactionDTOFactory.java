package io.github.jairandersonsouza.authorizer.factories;

import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.dtos.TransactionCashDTO;
import io.github.jairandersonsouza.authorizer.dtos.TransactionDTO;
import io.github.jairandersonsouza.authorizer.dtos.TransactionFoodDTO;
import io.github.jairandersonsouza.authorizer.dtos.TransactionMealDTO;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public class TransactionDTOFactory {

    public static TransactionDTO createDTO(TransactionInput transactionInput) {
        if (transactionInput.getMcc().equals(MccEnum.MEAL.name())) {
            return TransactionMealDTO.create(transactionInput);
        } else if (transactionInput.getMcc().equals(MccEnum.FOOD.name())) {
            return TransactionFoodDTO.create(transactionInput);
        } else {
            return TransactionCashDTO.create(transactionInput);
        }
    }

}

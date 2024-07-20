package io.github.jairandersonsouza.authorizer.template;

import io.github.jairandersonsouza.authorizer.entities.Account;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentFactory {

    @Autowired
    private Map<String, PaymentProcessor> targets;

    public PaymentProcessor getProcessor(TransactionInput transactionInput, Account account) {
        //TODO
        //fazer aqui a validação do account
        //1. verifica logo limite de saldo, se não houver, retorna CASH
        //2. verifica logo se o mmc é algo aleatório, se for retorn CASH
        //3. valida o MMC e retorna o PaymentProcessor Correspondente
//        if (operationIsCash(account, transactionInput) || (!operationIsMeal(account, transactionInput) && !operationIsFood(account, transactionInput))) {
//            return this.targets.get(MccEnum.CASH.name());
//        } else
        if (operationIsMeal(account, transactionInput)) {
            return this.targets.get(MccEnum.MEAL.name());
        } else if (operationIsFood(account, transactionInput)) {
            return this.targets.get(MccEnum.FOOD.name());
        } else {
            return this.targets.get(MccEnum.CASH.name());
        }

    }

    boolean operationIsCash(Account account, TransactionInput transactionInput) {
        return (!operationIsMeal(account, transactionInput) && !operationIsMeal(account, transactionInput)) && account.amountGteThan(transactionInput, MccEnum.MEAL);
    }

    boolean operationIsMeal(Account account, TransactionInput transactionInput) {
        return (transactionInput.getMcc().equals("5811") || transactionInput.getMcc().equals("5812")) && account.amountGteThan(transactionInput, MccEnum.MEAL);
    }

    boolean operationIsFood(Account account, TransactionInput transactionInput) {
        return (transactionInput.getMcc().equals("5411") || transactionInput.getMcc().equals("5412")) && account.amountGteThan(transactionInput, MccEnum.FOOD);
    }


}

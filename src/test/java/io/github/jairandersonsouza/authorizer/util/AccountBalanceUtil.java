package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;

import java.math.BigDecimal;

public class AccountBalanceUtil {

    //TODO validar se todos os tests estão chamando esse cara
    public static AccountBalance makeAccountBalance(String id, String accountId, BigDecimal balance, MccEnum mcc, String companyName) {
        return AccountBalance.create(id, accountId, balance, mcc, companyName);
    }


}


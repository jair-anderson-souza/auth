package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;

import java.math.BigDecimal;
import java.util.UUID;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.MEAL;

public class AccountBalanceUtil {

    //TODO
    //MExer aqui
    private static String ID_ACCOUNT = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
    private String idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();

    public static AccountBalance get() {
        return AccountBalance
                .builder()
                .id(ID_ACCOUNT)
                .mcc(MEAL)
                .balance(new BigDecimal(500))
                .build();
    }
}


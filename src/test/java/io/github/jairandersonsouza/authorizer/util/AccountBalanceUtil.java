package io.github.jairandersonsouza.authorizer.util;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;

import java.math.BigDecimal;
import java.util.UUID;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.MEAL;

public class AccountBalanceUtil {

    //TODO
    //MExer aqui
    private static String ID = UUID.fromString("b3404d4c-ada8-465a-977a-0cc83f1451bb").toString();
    private static String ID_ACCOUNT = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
    private String idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();

    public static AccountBalance get() {
//        public static AccountBalance create(String id, BigDecimal balance, MccEnum mcc, String companyName) {
        return AccountBalance.create(ID, ID_ACCOUNT, new BigDecimal(500), MEAL, null);
    }
}


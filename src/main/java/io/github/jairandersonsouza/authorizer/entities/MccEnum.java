package io.github.jairandersonsouza.authorizer.entities;

public enum MccEnum {
    FOOD, MEAL, CASH;


    public static MccEnum getMcc(final String mcc) {
        if (mcc.equals("5811") || mcc.equals("5812")) {
            return MEAL;
        }
        if (mcc.equals("5411") || mcc.equals("5412")) {
            return FOOD;
        }
        return CASH;
    }

}

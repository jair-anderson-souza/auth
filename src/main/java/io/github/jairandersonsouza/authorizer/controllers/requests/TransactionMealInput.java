//package io.github.jairandersonsouza.authorizer.controllers.requests;
//
//
//import io.github.jairandersonsouza.authorizer.entities.MccEnum;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//public class TransactionMealInput extends TransactionInput {
//
//    private MccEnum mcc;
//
//    public static List<String> VALID_MEALS = List.of("EATS", "IFOOD");
//    public static List<String> VALID_MEALS_MCC_MEAL = List.of("5811", "5812");
//
//
//    private TransactionMealInput(String account, BigDecimal totalAmount, String merchant) {
//        super(account, totalAmount, merchant);
//        this.mcc = MccEnum.MEAL;
//
//    }
//
////    public String getMcc() {
////        return mcc;
////    }
//
////    public String getMerchant() {
////        return merchant;
////    }
//
//    //TODO
//    public boolean isMeal() {
//        return validateMcc(VALID_MEALS) || isaBoolean();
//    }
//
//    private boolean isaBoolean() {
//        return VALID_MEALS_MCC_MEAL.contains(this.mcc);
//    }
//
////    public boolean isFood() {
////        return validateMcc(VALID_FOODS) || is();
////    }
//
////    public boolean is() {
////        return VALID_MEALS_MCC_FOOD.contains(this.mcc);
////    }
//
//    public boolean validateMcc(List<String> mccs) {
//        return mccs.stream().anyMatch((value) -> this.getAccount().toLowerCase().contains(value.toLowerCase()));
//    }
//
//    public static TransactionInput create(String account, BigDecimal totalAmount, String mcc, String merchant) {
//        return new TransactionMealInput(account, totalAmount, merchant);
//    }
//
//
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (o == null || getClass() != o.getClass()) return false;
////        TransactionInput that = (TransactionInput) o;
////        return Objects.equals(account, that.account) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(mcc, that.mcc) && Objects.equals(merchant, that.merchant);
////    }
//
////    @Override
////    public int hashCode() {
////        return Objects.hash(account, totalAmount, mcc, merchant);
////    }
//}

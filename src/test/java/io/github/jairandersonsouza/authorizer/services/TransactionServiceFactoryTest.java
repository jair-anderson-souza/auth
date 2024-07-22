//package io.github.jairandersonsouza.authorizer.processors;
//
//import io.github.jairandersonsouza.authorizer.factories.TransactionServiceFactory;
//import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
//import io.github.jairandersonsouza.authorizer.services.CashTransactionService;
//import io.github.jairandersonsouza.authorizer.services.FoodTransactionService;
//import io.github.jairandersonsouza.authorizer.services.MealTransactionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//import static io.github.jairandersonsouza.authorizer.entities.MccEnum.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class TransactionServiceFactoryTest {
//
//    @InjectMocks
//    private TransactionServiceFactory transactionServiceFactory;
//
//
//    private Map<String, TransactionServiceFactory> processors = mock(Map.class);
//
//
//    @BeforeEach
//    public void init() {
//        when(processors.get(FOOD.name())).thenReturn(new FoodTransactionService());
//        when(processors.get(CASH.name())).thenReturn(new CashTransactionService());
//        when(processors.get(MEAL.name())).thenReturn(new MealTransactionService());
//    }
//
//
//    @Test
//    void testShouldReturnsMealPaymentProcess() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5811");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(MealTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldAlsoReturnsMealPaymentProcess() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5812");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(MealTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldReturnsCashPaymentProcessBecauseTheMccIsInvalid() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5800");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(CashTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldReturnsFoodPaymentProcess() {
////        AccountBalance balance = AccountBalance.create(null, null,new BigDecimal(500), FOOD, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5412");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(FoodTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldAlsoReturnsFoodPaymentProcess() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), FOOD, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5411");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(FoodTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldAlsoReturnsCashPaymentProcessBecauseTheAmountIsInvalid() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(50), MEAL, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5412");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(FoodTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldAlsoReturnsCashPaymentProcessBecauseTheMccIsInvalid() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("3454");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(CashTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldAlsoReturnsCashPaymentProcessBecauseTheMccIsAlsoInvalid() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), CASH, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("2234");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(CashTransactionService.class, processor);
//    }
//
//    @Test
//    void testShouldReturnsCashPaymentProcessBecauseTheMccIsAlsoInvalid() {
////        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), CASH, null);
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("3454");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.transactionServiceFactory.getProcessor(transaction);
//        assertInstanceOf(CashTransactionService.class, processor);
//    }
//
//
//}
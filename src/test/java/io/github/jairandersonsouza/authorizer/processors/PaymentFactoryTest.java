package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.factories.PaymentFactory;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentFactoryTest {

    @InjectMocks
    private PaymentFactory paymentFactory;


    private Map<String, TransactionProcessor> processors = mock(Map.class);


    @BeforeEach
    public void init() {
        when(processors.get(FOOD.name())).thenReturn(new FoodTransactionProcessor());
        when(processors.get(CASH.name())).thenReturn(new CashTransactionProcessor());
        when(processors.get(MEAL.name())).thenReturn(new MealTransactionProcessor());
    }


    @Test
    void testShouldReturnsMealPaymentProcess() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5811");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(MealTransactionProcessor.class, processor);
    }

    @Test
    void testShouldAlsoReturnsMealPaymentProcess() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5812");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(MealTransactionProcessor.class, processor);
    }

    @Test
    void testShouldReturnsCashPaymentProcessBecauseTheMccIsInvalid() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5800");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionProcessor.class, processor);
    }

    @Test
    void testShouldReturnsFoodPaymentProcess() {
//        AccountBalance balance = AccountBalance.create(null, null,new BigDecimal(500), FOOD, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5412");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(FoodTransactionProcessor.class, processor);
    }

    @Test
    void testShouldAlsoReturnsFoodPaymentProcess() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), FOOD, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5411");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(FoodTransactionProcessor.class, processor);
    }

    @Test
    void testShouldAlsoReturnsCashPaymentProcessBecauseTheAmountIsInvalid() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(50), MEAL, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5412");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(FoodTransactionProcessor.class, processor);
    }

    @Test
    void testShouldAlsoReturnsCashPaymentProcessBecauseTheMccIsInvalid() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), MEAL, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("3454");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionProcessor.class, processor);
    }

    @Test
    void testShouldAlsoReturnsCashPaymentProcessBecauseTheMccIsAlsoInvalid() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), CASH, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("2234");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionProcessor.class, processor);
    }

    @Test
    void testShouldReturnsCashPaymentProcessBecauseTheMccIsAlsoInvalid() {
//        AccountBalance balance = AccountBalance.create(null, null, new BigDecimal(500), CASH, null);
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("3454");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionProcessor.class, processor);
    }


}
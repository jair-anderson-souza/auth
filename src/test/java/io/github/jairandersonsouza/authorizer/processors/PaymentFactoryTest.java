package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
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


    private Map<String, PaymentProcessor> processors = mock(Map.class);


    @BeforeEach
    public void init() {
        when(processors.get(FOOD.name())).thenReturn(new FoodPaymentProcessor());
        when(processors.get(CASH.name())).thenReturn(new CashPaymentProcessor());
        when(processors.get(MEAL.name())).thenReturn(new MealPaymentProcessor());
    }


    @Test
    void testGetMeal() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(MEAL)
                .balance(new BigDecimal(500))
                .build();

        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5811");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(MealPaymentProcessor.class, processor);
    }

    @Test
    void testGetAlsoMeal() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(MEAL)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5812");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(MealPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheAmountIsLowerForMeal() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(MEAL)
                .balance(new BigDecimal(50))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5811");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheMccIsInvalid() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(MEAL)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5800");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetFood() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(FOOD)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5412");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(FoodPaymentProcessor.class, processor);
    }


    @Test
    void testGetAlsoFood() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(FOOD)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5411");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(FoodPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheAmountIsLowerForFood() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(MEAL)
                .balance(new BigDecimal(50))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5412");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheAmountIsLowerFor() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(MEAL)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("3454");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetCash() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(CASH)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("2234");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetAlsoCash() {
        AccountBalance balance = AccountBalance.builder()
                .mcc(CASH)
                .balance(new BigDecimal(500))
                .build();
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("3454");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, balance);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }


}
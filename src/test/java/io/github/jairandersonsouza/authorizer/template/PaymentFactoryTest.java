package io.github.jairandersonsouza.authorizer.template;

import io.github.jairandersonsouza.authorizer.entities.Account;
import io.github.jairandersonsouza.authorizer.entities.Balance;
import io.github.jairandersonsouza.authorizer.processors.*;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
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
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(MEAL);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5811");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(MealPaymentProcessor.class, processor);
    }

    @Test
    void testGetAlsoMeal() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(MEAL);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5812");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(MealPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheAmountIsLowerForMeal() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(MEAL);
        balance.setBalance(new BigDecimal(50));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5811");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheMccIsInvalid() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(MEAL);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5800");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetFood() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(FOOD);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5412");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(FoodPaymentProcessor.class, processor);
    }


    @Test
    void testGetAlsoFood() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(FOOD);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5411");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(FoodPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheAmountIsLowerForFood() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(MEAL);
        balance.setBalance(new BigDecimal(50));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("5412");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));

        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetCashBecauseTheAmountIsLowerFor() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(MEAL);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("3454");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetCash() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(CASH);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("2234");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }

    @Test
    void testGetAlsoCash() {
        Account account = new Account();
        var balance = new Balance();
        balance.setMcc(CASH);
        balance.setBalance(new BigDecimal(500));
        account.setBalances(List.of(balance));
        var transaction = new TransactionInput();
        transaction.setAccount("1123");
        transaction.setMcc("3454");
        transaction.setMerchant("Google");
        transaction.setTotalAmount(new BigDecimal(100));
        final var processor = this.paymentFactory.getProcessor(transaction, account);
        assertInstanceOf(CashPaymentProcessor.class, processor);
    }


}
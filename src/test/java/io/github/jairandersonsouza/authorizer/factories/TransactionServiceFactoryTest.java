package io.github.jairandersonsouza.authorizer.factories;

import io.github.jairandersonsouza.authorizer.services.CashTransactionService;
import io.github.jairandersonsouza.authorizer.services.FoodTransactionService;
import io.github.jairandersonsouza.authorizer.services.MealTransactionService;
import io.github.jairandersonsouza.authorizer.services.TransactionService;
import io.github.jairandersonsouza.authorizer.util.TransactionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;


import static io.github.jairandersonsouza.authorizer.entities.MccEnum.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceFactoryTest {

    @InjectMocks
    private TransactionServiceFactory transactionServiceFactory;

    private Map<String, TransactionService> services = mock(Map.class);


    @BeforeEach
    public void init() {
        when(services.get(FOOD.name())).thenReturn(new FoodTransactionService());
        when(services.get(CASH.name())).thenReturn(new CashTransactionService());
        when(services.get(MEAL.name())).thenReturn(new MealTransactionService());
    }


    @Test
    void testShouldReturnsMealTransactionService() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5811", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(MealTransactionService.class, processor);
    }

    @Test
    void testShouldAlsoReturnsMealPaymentProcess() {
//TODO sendo criado em todo lugar nessa classe
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5811", "Google");

        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(MealTransactionService.class, processor);
    }

    @Test
    void testShouldReturnsCashTransactionServiceBecauseTheMccIsInvalid() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5800", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionService.class, processor);
    }

    @Test
    void testShouldReturnsFoodPaymentProcess() {
        //TODO remover duplicação
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5412", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(FoodTransactionService.class, processor);
    }

    @Test
    void testShouldAlsoReturnsFoodPaymentProcess() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5411", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(FoodTransactionService.class, processor);
    }

    @Test
    void testShouldAlsoReturnsCashPaymentProcessBecauseTheAmountIsInvalid() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5412", "Google");


        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(FoodTransactionService.class, processor);
    }

    @Test
    void testShouldAlsoReturnsCashPaymentProcessBecauseTheMccIsInvalid() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "3454", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionService.class, processor);
    }

    @Test
    void testShouldAlsoReturnsCashPaymentProcessBecauseTheMccIsAlsoInvalid() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "2234", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionService.class, processor);
    }

    @Test
    void testShouldReturnsCashPaymentProcessBecauseTheMccIsAlsoInvalid() {
        var transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "3454", "Google");
        final var processor = this.transactionServiceFactory.getProcessor(transaction);
        assertInstanceOf(CashTransactionService.class, processor);
    }


}
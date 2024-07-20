//TODO
//package io.github.jairandersonsouza.authorizer.services;
//
//import io.github.jairandersonsouza.authorizer.processors.*;
//import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
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
//import static org.junit.jupiter.api.Assertions.assertInstanceOf;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//class TransactionServiceTest {
//
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @InjectMocks
//    private PaymentProcessorFactory paymentProcessorFactory;
//
//
//    private Map<String, PaymentProcessor> processors = mock(Map.class);
//
//
//    @BeforeEach
//    public void init(){
//        when(processors.get(FOOD.name())).thenReturn(new FoodPaymentProcessor());
//        when(processors.get(CASH.name())).thenReturn(new CashPaymentProcessor());
//        when(processors.get(MEAL.name())).thenReturn(new MealPaymentProcessor());
//    }
//
//
//    @Test
//    void testGetMeal() {
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5811");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
////TODO
////        this.transactionService.valid(transaction);
//
////        assertInstanceOf(MealPaymentProcessor.class, processors);
//    }
//
//}
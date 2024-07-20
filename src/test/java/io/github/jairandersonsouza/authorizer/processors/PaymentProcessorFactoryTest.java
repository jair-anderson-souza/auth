//package io.github.jairandersonsouza.authorizer.processors;
//
//import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.math.BigDecimal;
//import java.util.Map;
//import static io.github.jairandersonsouza.authorizer.entities.MccEnum.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class PaymentProcessorFactoryTest {
//
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
//
//        final var processor = this.paymentProcessorFactory.getProcessor(transaction);
//        assertInstanceOf(MealPaymentProcessor.class, processor);
//    }
//
//    @Test
//    void testGetAlsoMeal() {
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5812");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//
//        final var processor = this.paymentProcessorFactory.getProcessor(transaction);
//        assertInstanceOf(MealPaymentProcessor.class, processor);
//    }
//
//    @Test
//    void testGetCash() {
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("2234");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.paymentProcessorFactory.getProcessor(transaction);
//        assertInstanceOf(CashPaymentProcessor.class, processor);
//    }
//
//    @Test
//    void testGetAlsoCash() {
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("3454");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.paymentProcessorFactory.getProcessor(transaction);
//        assertInstanceOf(CashPaymentProcessor.class, processor);
//    }
//
//    @Test
//    void testGetFood() {
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5412");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.paymentProcessorFactory.getProcessor(transaction);
//        assertInstanceOf(FoodPaymentProcessor.class, processor);
//    }
//
//
//    @Test
//    void testGetAlsoFood() {
//        var transaction = new TransactionInput();
//        transaction.setAccount("1123");
//        transaction.setMcc("5411");
//        transaction.setMerchant("Google");
//        transaction.setTotalAmount(new BigDecimal(100));
//        final var processor = this.paymentProcessorFactory.getProcessor(transaction);
//        assertInstanceOf(FoodPaymentProcessor.class, processor);
//    }
//
//
//}
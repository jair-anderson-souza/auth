//package io.github.jairandersonsouza.authorizer.processors;
//
//import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
//import io.github.jairandersonsouza.authorizer.entities.MccEnum;
//import io.github.jairandersonsouza.authorizer.entities.Transaction;
//import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
//import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
//import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
//import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
//import io.github.jairandersonsouza.authorizer.services.CashTransactionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import static io.github.jairandersonsouza.authorizer.entities.MccEnum.CASH;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CashTransactionServiceTest {
//
//    @InjectMocks
//    private CashTransactionService cashPaymentProcessor;
//
//    @Mock
//    private AccountBalanceService accountBalanceService;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    private String id;
//    private String idAccount;
//    private String idTransaction;
//
//    @BeforeEach
//    public void init() {
//        this.id = UUID.fromString("b3404d4c-ada8-465a-977a-0cc83f1451bb").toString();
//        this.idAccount = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
//        this.idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();
//
//    }
//
//    //TODO
//    @Test
//    void testShouldProcessCashPayment() {
//
//        var transactionInput = new TransactionInput();
//        transactionInput.setAccount("1123");
//        transactionInput.setMcc("2345");
//        transactionInput.setMerchant("Google");
//        transactionInput.setTotalAmount(new BigDecimal(100));
//        var transaction = Transaction.create(transactionInput);
//        AccountBalance accountBalanceRequest = AccountBalance.create(id, transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.getMcc(transactionInput.getMcc()), transactionInput.getMerchant());
//
//        when(accountBalanceService.getAccount(any(TransactionInput.class))).thenReturn(accountBalanceRequest);
//        doNothing().when(accountBalanceService).save(any(AccountBalance.class));
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//
//        this.cashPaymentProcessor.processTransaction(transactionInput);
//        //TODO
//
//        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
//        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
//        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);
//
//        verify(accountBalanceService)
//                .getAccount(argumentCaptorTransactionInput.capture());
//        verify(accountBalanceService)
//                .save(argumentCaptorSaveAccountBalance.capture());
//        verify(transactionRepository)
//                .save(argumentCaptorTransaction.capture());
//
//        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());
//        //TODO
////        assertEquals(transactionInput, argumentCaptorTransaction.getValue());
////        assertEquals(transaction, argumentCaptorSaveAccountBalance.getValue());
////        assertEquals(Transaction.create(transactionInput), argumentCaptorTransaction.getValue());
//    }
//
//    //TODO
//    @Test
//    void testShouldThrownAnTransactionRejected() {
//
//        var transactionInput = new TransactionInput();
//        transactionInput.setAccount("1123");
//        transactionInput.setMcc("2345");
//        transactionInput.setMerchant("Google");
//        transactionInput.setTotalAmount(new BigDecimal(100));
//        var transaction = Transaction.create(transactionInput);
//        AccountBalance accountBalanceRequest = AccountBalance.create(id, transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.getMcc(transactionInput.getMcc()), transactionInput.getMerchant());
//
//
//        when(accountBalanceService.getAccount(any(TransactionInput.class))).thenThrow(TransactionRejectedException.class);
////        doNothing().when(accountBalanceService).save(any(AccountBalance.class));
////        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//
//        var exception = assertThrows(TransactionRejectedException.class, () -> {
//            this.cashPaymentProcessor.processTransaction(transactionInput);
//        });
//        //TODO
//
//        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
//        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
//        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);
//
//        verify(accountBalanceService)
//                .getAccount(argumentCaptorTransactionInput.capture());
//        verify(accountBalanceService, times(0))
//                .save(argumentCaptorSaveAccountBalance.capture());
//        verify(transactionRepository, times(0))
//                .save(argumentCaptorTransaction.capture());
//
//        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());
//        assertInstanceOf(TransactionRejectedException.class, exception);
//    }
//
//    //TODO
//    @Test
//    void testShouldAlsoThrownAnTransactionRejected() {
//
//        var transactionInput = new TransactionInput();
//        transactionInput.setAccount("1123");
//        transactionInput.setMcc("2345");
//        transactionInput.setMerchant("Google");
//        transactionInput.setTotalAmount(new BigDecimal(100));
//        var transaction = Transaction.create(transactionInput);
//        AccountBalance accountBalanceRequest = AccountBalance.create(id, transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.getMcc(transactionInput.getMcc()), transactionInput.getMerchant());
//
//        when(accountBalanceService.getAccount(any(TransactionInput.class))).thenReturn(accountBalanceRequest);
////        when(accountBalanceService.getValidAccount(any(TransactionInput.class))).thenThrow(TransactionRejectedException.class);
//
//        doThrow(RuntimeException.class).when(accountBalanceService).save(any(AccountBalance.class));
////        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//
//        var exception = assertThrows(TransactionRejectedException.class, () -> {
//            this.cashPaymentProcessor.processTransaction(transactionInput);
//        });
//        //TODO
//
//        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
//        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
//        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);
//
//        verify(accountBalanceService)
//                .getAccount(argumentCaptorTransactionInput.capture());
//        verify(accountBalanceService, times(1))
//                .save(argumentCaptorSaveAccountBalance.capture());
//        verify(transactionRepository, times(0))
//                .save(argumentCaptorTransaction.capture());
//
//        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());
//        assertEquals(accountBalanceRequest, argumentCaptorSaveAccountBalance.getValue());
//        assertInstanceOf(TransactionRejectedException.class, exception);
//    }
//
//    @Test
//    void testShouldGetMcc() {
//        MccEnum mcc = this.cashPaymentProcessor.getMcc("2345");
//        assertEquals(CASH, mcc);
//
//    }
//
//
//}
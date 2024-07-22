package io.github.jairandersonsouza.authorizer.services;


import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.util.AccountBalanceUtil;
import io.github.jairandersonsouza.authorizer.util.TransactionUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private CashTransactionService cashTransactionService;

    @InjectMocks
    private MealTransactionService mealTransactionService;

    @InjectMocks
    private FoodTransactionService foodTransactionService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private AccountBalanceService accountBalanceService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void testShouldProcessCashPayment() {
        final var id = UUID.randomUUID().toString();
        var transactionInput = TransactionUtil.makeTransactionInput("1123", new BigDecimal(100), "2345", "Google");
        var transaction = TransactionUtil.makeTransaction(transactionInput);
        AccountBalance accountBalanceResponse = AccountBalanceUtil.makeAccountBalance(id, transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.getMcc(transactionInput.getMcc()), transactionInput.getMerchant());

        when(authorizationService.authorizeTransaction(any(TransactionInput.class))).thenReturn(accountBalanceResponse);
        doNothing().when(accountBalanceService).save(any(AccountBalance.class));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        this.cashTransactionService.startTransaction(transactionInput);
        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);

        verify(authorizationService)
                .authorizeTransaction(argumentCaptorTransactionInput.capture());
        verify(accountBalanceService)
                .save(argumentCaptorSaveAccountBalance.capture());
        verify(transactionRepository)
                .save(argumentCaptorTransaction.capture());

        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());

        assertEquals(accountBalanceResponse, argumentCaptorSaveAccountBalance.getValue());
        assertEquals(transaction.getAmount(), argumentCaptorTransaction.getValue().getAmount());
    }

    @Test
    void testShouldProcessFoodPayment() {
        var transactionInput = TransactionUtil.makeTransactionInput("1123", new BigDecimal(100), "2345", "Google");
        var transaction = TransactionUtil.makeTransaction(transactionInput);
        AccountBalance accountBalanceResponse = AccountBalanceUtil.makeAccountBalance(UUID.randomUUID().toString(), transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.getMcc(transactionInput.getMcc()), transactionInput.getMerchant());

        when(authorizationService.authorizeTransaction(any(TransactionInput.class))).thenReturn(accountBalanceResponse);
        doNothing().when(accountBalanceService).save(any(AccountBalance.class));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        this.foodTransactionService.startTransaction(transactionInput);
        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);

        verify(authorizationService)
                .authorizeTransaction(argumentCaptorTransactionInput.capture());
        verify(accountBalanceService)
                .save(argumentCaptorSaveAccountBalance.capture());
        verify(transactionRepository)
                .save(argumentCaptorTransaction.capture());

        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());

        assertEquals(accountBalanceResponse, argumentCaptorSaveAccountBalance.getValue());
        assertEquals(transaction.getAmount(), argumentCaptorTransaction.getValue().getAmount());
    }

    @Test
    void testShouldProcessMealPayment() {
        var transactionInput = TransactionUtil.makeTransactionInput("1123", new BigDecimal(100), "2345", "Google");
        var transaction = TransactionUtil.makeTransaction(transactionInput);
        AccountBalance accountBalanceResponse = AccountBalanceUtil.makeAccountBalance(UUID.randomUUID().toString(), transactionInput.getAccount(), transactionInput.getTotalAmount(), MccEnum.getMcc(transactionInput.getMcc()), transactionInput.getMerchant());

        when(authorizationService.authorizeTransaction(any(TransactionInput.class))).thenReturn(accountBalanceResponse);
        doNothing().when(accountBalanceService).save(any(AccountBalance.class));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        this.mealTransactionService.startTransaction(transactionInput);
        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);

        verify(authorizationService)
                .authorizeTransaction(argumentCaptorTransactionInput.capture());
        verify(accountBalanceService)
                .save(argumentCaptorSaveAccountBalance.capture());
        verify(transactionRepository)
                .save(argumentCaptorTransaction.capture());

        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());

        assertEquals(accountBalanceResponse, argumentCaptorSaveAccountBalance.getValue());
        assertEquals(transaction.getAmount(), argumentCaptorTransaction.getValue().getAmount());
    }


    @Test
    void testShouldThrownAnTransactionRejected() {
        var transactionInput = TransactionUtil.makeTransactionInput("1123", new BigDecimal(100), "2345", "Google");

        when(authorizationService.authorizeTransaction(any(TransactionInput.class))).thenThrow(TransactionRejectedException.class);

        var exception = assertThrows(TransactionRejectedException.class, () -> {
            this.cashTransactionService.startTransaction(transactionInput);
        });

        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);

        verify(authorizationService, times(1))
                .authorizeTransaction(argumentCaptorTransactionInput.capture());

        verify(accountBalanceService, times(0))
                .save(argumentCaptorSaveAccountBalance.capture());
        verify(transactionRepository, times(0))
                .save(argumentCaptorTransaction.capture());

        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());
        assertEquals("51", exception.getMessage());
        assertInstanceOf(TransactionRejectedException.class, exception);
    }

    @Test
    void testShouldThrownAnExceptionAccountNotExists() {
        var transactionInput = TransactionUtil.makeTransactionInput("1123", new BigDecimal(100), "2345", "Google");

        when(authorizationService.authorizeTransaction(any(TransactionInput.class))).thenThrow(AccountNotExistsException.class);

        var exception = assertThrows(AccountNotExistsException.class, () -> {
            this.cashTransactionService.startTransaction(transactionInput);
        });

        final ArgumentCaptor<TransactionInput> argumentCaptorTransactionInput = ArgumentCaptor.forClass(TransactionInput.class);
        final ArgumentCaptor<AccountBalance> argumentCaptorSaveAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);

        verify(authorizationService, times(1))
                .authorizeTransaction(argumentCaptorTransactionInput.capture());
        verify(accountBalanceService, times(0))
                .save(argumentCaptorSaveAccountBalance.capture());
        verify(transactionRepository, times(0))
                .save(argumentCaptorTransaction.capture());
        assertEquals(transactionInput, argumentCaptorTransactionInput.getValue());
        assertInstanceOf(AccountNotExistsException.class, exception);
    }


    @Test
    void testShouldGetMcc() {
        MccEnum mcc = this.cashTransactionService.getMcc("2345");
        assertEquals(CASH, mcc);

    }
}
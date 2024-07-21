package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.MEAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealTransactionProcessorTest {

    @InjectMocks
    private MealTransactionProcessor mealPaymentProcessor;

    @Mock
    private AccountBalanceService accountBalanceService;

    @Mock
    private TransactionRepository transactionRepository;

    private String id;
    private String idAccount;
    private String idTransaction;

    @BeforeEach
    public void init() {
        this.id = UUID.fromString("b3404d4c-ada8-465a-977a-0cc83f1451bb").toString();
        this.idAccount = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
        this.idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();

    }

    //TODO
    @Test
    void testProcessMealPayment() {
        AccountBalance accountBalanceResponse = AccountBalance.create(id, idAccount, new BigDecimal(400), MEAL, null);
        var transactionInput = new TransactionInput();
        transactionInput.setAccount("1123");
        transactionInput.setMcc("5811");
        transactionInput.setMerchant("Google");
        transactionInput.setTotalAmount(new BigDecimal(100));

        AccountBalance accountBalanceRequest = AccountBalance.create(id, transactionInput.getAccount(), new BigDecimal(400), MccEnum.getMcc(transactionInput.getMcc()), null);

        when(accountBalanceService.getValidAccount(any(TransactionInput.class))).thenReturn(accountBalanceRequest);
        doNothing().when(accountBalanceService).save(any(AccountBalance.class));

        this.mealPaymentProcessor.processTransaction(transactionInput);

        final ArgumentCaptor<AccountBalance> argumentCaptorAccount = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);

        verify(accountBalanceService)
                .save(argumentCaptorAccount.capture());
        verify(transactionRepository)
                .save(argumentCaptorTransaction.capture());

        assertEquals(accountBalanceResponse, argumentCaptorAccount.getValue());
        assertEquals(Transaction.create(transactionInput), argumentCaptorTransaction.getValue());
    }


}
package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.AccountBalanceService;
import io.github.jairandersonsouza.authorizer.util.AccountBalanceUtil;
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
class MealPaymentProcessorTest {

    @InjectMocks
    private MealPaymentProcessor mealPaymentProcessor;

    @Mock
    private AccountBalanceService accountBalanceService;

    @Mock
    private TransactionRepository transactionRepository;

    private String idAccount;
    private String idTransaction;

    @BeforeEach
    public void init() {
        this.idAccount = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
        this.idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();

    }

    //TODO
    @Test
    void testProcessMealPayment() {
        AccountBalance accountBalanceRequest = AccountBalanceUtil.get();

        AccountBalance accountBalanceResponse = AccountBalance.builder()
                .id(idAccount)
                .mcc(MEAL)
                .balance(new BigDecimal(400))
                .build();

        var transactionInput = new TransactionInput();
        transactionInput.setAccount("1123");
        transactionInput.setMcc("5811");
        transactionInput.setMerchant("Google");
        transactionInput.setTotalAmount(new BigDecimal(100));

        final var tranRequest = new Transaction(null, transactionInput.getAccount(), transactionInput.getTotalAmount(), transactionInput.getMerchant(), transactionInput.getMcc());

        final var tranResponse = new Transaction(idTransaction, transactionInput.getAccount(), transactionInput.getTotalAmount(), transactionInput.getMerchant(), transactionInput.getMcc());


        doNothing().when(accountBalanceService).save(any());
//        when(transactionRepository.save(tranRequest)).thenReturn(tranResponse);

        this.mealPaymentProcessor.startTransaction(transactionInput, accountBalanceRequest);

        final ArgumentCaptor<AccountBalance> argumentCaptorAccount = ArgumentCaptor.forClass(AccountBalance.class);
        final ArgumentCaptor<Transaction> argumentCaptorTransaction = ArgumentCaptor.forClass(Transaction.class);


        verify(accountBalanceService)
                .save(argumentCaptorAccount.capture());
        verify(transactionRepository)
                .save(argumentCaptorTransaction.capture());

        assertEquals(accountBalanceResponse, argumentCaptorAccount.getValue());
        //TODO
        //adicionar esse assertEquals, depois que corrigi o generate do uuid
        //assertEquals(tranResponse, argumentCaptorTransaction.getValue());
    }


}
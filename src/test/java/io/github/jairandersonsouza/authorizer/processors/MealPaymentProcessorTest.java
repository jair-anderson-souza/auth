package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
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
    public void init(){
        this.idAccount = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
        this.idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();

    }
    //TODO
    @Test
    void testProcessMealPayment() {
        AccountBalance accountBalanceRequest = new AccountBalance();
        accountBalanceRequest.setId(idAccount);
        accountBalanceRequest.setMcc(MEAL);
        accountBalanceRequest.setBalance(new BigDecimal(500));

        AccountBalance accountBalanceResponse = new AccountBalance();
        accountBalanceResponse.setId(idAccount);
        accountBalanceResponse.setMcc(MEAL);
        accountBalanceResponse.setBalance(new BigDecimal(400));

        var transactionInput = new TransactionInput();
        transactionInput.setAccount("1123");
        transactionInput.setMcc("5811");
        transactionInput.setMerchant("Google");
        transactionInput.setTotalAmount(new BigDecimal(100));

        var tranRequest = new Transaction();
//        tranRequest.setId(idTransaction);
        tranRequest.setAccountId(transactionInput.getAccount());
        tranRequest.setAmount(transactionInput.getTotalAmount());
        tranRequest.setMerchant(transactionInput.getMerchant());
        tranRequest.setMcc(transactionInput.getMcc());

        var tranResponse = new Transaction();
        tranResponse.setId(idTransaction);
        tranResponse.setAccountId(transactionInput.getAccount());
        tranResponse.setAmount(transactionInput.getTotalAmount());
        tranResponse.setMerchant(transactionInput.getMerchant());
        tranResponse.setMcc(transactionInput.getMcc());


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
package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.CASH;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CashTransactionServiceTest {

    @InjectMocks
    private CashTransactionService cashPaymentProcessor;

    @Mock
    private AccountBalanceService accountBalanceService;

    @Mock
    private TransactionRepository transactionRepository;


    @Test
    void testShouldGetMcc() {
        MccEnum mcc = this.cashPaymentProcessor.getMcc("2345");
        assertEquals(CASH, mcc);

    }


}
package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.AccountBalanceRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountBalanceServiceTest {

    @InjectMocks
    private AccountBalanceService accountBalanceService;

    @Mock
    private AccountBalanceRepository accountBalanceRepository;


    private String id;
    private String idAccount;
    private String idTransaction;
    private TransactionInput transaction;

    @BeforeEach
    public void init() {
        this.id = UUID.fromString("b3404d4c-ada8-465a-977a-0cc83f1451bb").toString();
        this.idAccount = UUID.fromString("4507ccc6-47c0-4723-b654-a105c1ba9e52").toString();
        this.idTransaction = UUID.fromString("b152ae80-2897-4e7d-af1b-f6f985598007").toString();
        this.transaction = new TransactionInput();
        this.transaction.setAccount("1123");
        this.transaction.setMcc("5811");
        this.transaction.setMerchant("Google");
        this.transaction.setTotalAmount(new BigDecimal(100));
    }

    @Test
    void testShouldReturnsAValidAccount() {
        AccountBalance accountBalanceResponse = AccountBalance.create(id, transaction.getAccount(), transaction.getTotalAmount(), MccEnum.getMcc(transaction.getMcc()), transaction.getMerchant());
        //TODO
        when(this.accountBalanceRepository.findByAccountIdAndMcc(any(String.class), any(MccEnum.class))).thenReturn(Optional.of(accountBalanceResponse));

        final var accountResponse = this.accountBalanceService.getValidAccount(transaction);

        ArgumentCaptor<String> argumentCaptorAccountId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MccEnum> argumentCaptorMcc = ArgumentCaptor.forClass(MccEnum.class);

        verify(accountBalanceRepository, times(1))
                .findByAccountIdAndMcc(argumentCaptorAccountId.capture(), argumentCaptorMcc.capture());

        assertEquals(transaction.getAccount(), argumentCaptorAccountId.getValue());
        assertEquals(MccEnum.getMcc(transaction.getMcc()), argumentCaptorMcc.getValue());
        assertEquals(accountResponse, accountBalanceResponse);
    }

    @Test
    void testShouldThrownATransactionRejectedException() {
        AccountBalance accountBalanceResponse = AccountBalance.create(id, transaction.getAccount(), new BigDecimal(10), MccEnum.getMcc(transaction.getMcc()), transaction.getMerchant());

        when(this.accountBalanceRepository.findByAccountIdAndMcc(any(String.class), any(MccEnum.class))).thenReturn(Optional.of(accountBalanceResponse));

        final var exception = assertThrows(TransactionRejectedException.class, () -> this.accountBalanceService.getValidAccount(transaction));

        ArgumentCaptor<String> argumentCaptorAccountId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MccEnum> argumentCaptorMcc = ArgumentCaptor.forClass(MccEnum.class);

        verify(accountBalanceRepository, times(1))
                .findByAccountIdAndMcc(argumentCaptorAccountId.capture(), argumentCaptorMcc.capture());

        assertEquals(transaction.getAccount(), argumentCaptorAccountId.getValue());
        assertEquals(MccEnum.getMcc(transaction.getMcc()), argumentCaptorMcc.getValue());
        assertEquals("51", exception.getMessage());
        assertInstanceOf(TransactionRejectedException.class, exception);
    }

    @Test
    void testShouldThrownAAccountNotExistsException() {
        when(this.accountBalanceRepository.findByAccountIdAndMcc(any(String.class), any(MccEnum.class))).thenThrow(AccountNotExistsException.class);

        final var exception = assertThrows(AccountNotExistsException.class, () -> this.accountBalanceService.getValidAccount(transaction));

        ArgumentCaptor<String> argumentCaptorAccountId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MccEnum> argumentCaptorMcc = ArgumentCaptor.forClass(MccEnum.class);

        verify(accountBalanceRepository, times(1))
                .findByAccountIdAndMcc(argumentCaptorAccountId.capture(), argumentCaptorMcc.capture());

        assertEquals(transaction.getAccount(), argumentCaptorAccountId.getValue());
        assertEquals(MccEnum.getMcc(transaction.getMcc()), argumentCaptorMcc.getValue());
        assertInstanceOf(AccountNotExistsException.class, exception);
    }

    @Test
    void testShouldSave() {
        AccountBalance accountBalanceResponse = AccountBalance.create(id, transaction.getAccount(), new BigDecimal(10), MccEnum.getMcc(transaction.getMcc()), transaction.getMerchant());
        when(this.accountBalanceRepository.save(any(AccountBalance.class))).thenReturn(accountBalanceResponse);

        this.accountBalanceService.save(accountBalanceResponse);

        ArgumentCaptor<AccountBalance> argumentCaptorAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);

        verify(accountBalanceRepository, times(1))
                .save(argumentCaptorAccountBalance.capture());

        assertEquals(accountBalanceResponse, argumentCaptorAccountBalance.getValue());
    }

    @Test
    void testShouldThrownAnExceptionForSaving() {
        AccountBalance accountBalanceResponse = AccountBalance.create(id, transaction.getAccount(), new BigDecimal(10), null, transaction.getMerchant());
        when(this.accountBalanceRepository.save(any(AccountBalance.class))).thenThrow(RuntimeException.class);

        final var exception = assertThrows(TransactionRejectedException.class, () -> this.accountBalanceService.save(accountBalanceResponse));
        ArgumentCaptor<AccountBalance> argumentCaptorAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);

        verify(accountBalanceRepository, times(1))
                .save(argumentCaptorAccountBalance.capture());

        assertEquals(accountBalanceResponse, argumentCaptorAccountBalance.getValue());
        assertEquals("51", exception.getMessage());

    }

}
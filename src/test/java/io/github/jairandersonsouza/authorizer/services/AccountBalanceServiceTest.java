package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repository.AccountBalanceRepository;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.util.AccountBalanceUtil;
import io.github.jairandersonsouza.authorizer.util.TransactionUtil;
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

    private TransactionInput transaction;

    @BeforeEach
    public void init() {
        this.transaction = TransactionUtil.makeTransaction("1123", new BigDecimal(100), "5811", "Google");
    }

    @Test
    void testShouldReturnsAValidAccount() {
        AccountBalance accountBalanceResponse = AccountBalanceUtil.makeAccountBalance(UUID.randomUUID().toString(), transaction.getAccount(), transaction.getTotalAmount(), MccEnum.getMcc(transaction.getMcc()), transaction.getMerchant());
        when(this.accountBalanceRepository.findByAccountIdAndMcc(any(String.class), any(MccEnum.class))).thenReturn(Optional.of(accountBalanceResponse));
        final var accountResponse = this.accountBalanceService.getAccount(transaction);
        ArgumentCaptor<String> argumentCaptorAccountId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MccEnum> argumentCaptorMcc = ArgumentCaptor.forClass(MccEnum.class);
        verify(accountBalanceRepository, times(1))
                .findByAccountIdAndMcc(argumentCaptorAccountId.capture(), argumentCaptorMcc.capture());

        assertEquals(accountBalanceResponse, accountResponse);
        assertEquals(accountResponse.getAccountId(), argumentCaptorAccountId.getValue());
        assertEquals(accountResponse.getMcc(), argumentCaptorMcc.getValue());
    }

    @Test
    void testShouldThrownAccountNotExistsException() {

        when(this.accountBalanceRepository.findByAccountIdAndMcc(any(String.class), any(MccEnum.class))).thenReturn(Optional.empty());
        final var exception = assertThrows(AccountNotExistsException.class, () -> this.accountBalanceService.getAccount(transaction));

        ArgumentCaptor<String> argumentCaptorAccountId = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<MccEnum> argumentCaptorMcc = ArgumentCaptor.forClass(MccEnum.class);

        verify(accountBalanceRepository, times(1))
                .findByAccountIdAndMcc(argumentCaptorAccountId.capture(), argumentCaptorMcc.capture());
        assertEquals("07", exception.getMessage());
    }

    @Test
    void testShouldSave() {
        //TODO sendo criado em todo lugar nessa classe
        AccountBalance accountBalanceResponse = AccountBalanceUtil.makeAccountBalance(UUID.randomUUID().toString(), transaction.getAccount(), new BigDecimal(10), MccEnum.getMcc(transaction.getMcc()), transaction.getMerchant());
        when(this.accountBalanceRepository.save(any(AccountBalance.class))).thenReturn(accountBalanceResponse);

        this.accountBalanceService.save(accountBalanceResponse);

        ArgumentCaptor<AccountBalance> argumentCaptorAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);

        verify(accountBalanceRepository, times(1))
                .save(argumentCaptorAccountBalance.capture());

        assertEquals(accountBalanceResponse, argumentCaptorAccountBalance.getValue());
    }

    @Test
    void testShouldThrownAnExceptionForSaving() {
        AccountBalance accountBalanceResponse = AccountBalanceUtil.makeAccountBalance(UUID.randomUUID().toString(), transaction.getAccount(), new BigDecimal(10), null, transaction.getMerchant());
        when(this.accountBalanceRepository.save(any(AccountBalance.class))).thenThrow(RuntimeException.class);

        final var exception = assertThrows(TransactionRejectedException.class, () -> this.accountBalanceService.save(accountBalanceResponse));
        ArgumentCaptor<AccountBalance> argumentCaptorAccountBalance = ArgumentCaptor.forClass(AccountBalance.class);

        verify(accountBalanceRepository, times(1))
                .save(argumentCaptorAccountBalance.capture());

        assertEquals(accountBalanceResponse, argumentCaptorAccountBalance.getValue());
        assertEquals("51", exception.getMessage());

    }

}
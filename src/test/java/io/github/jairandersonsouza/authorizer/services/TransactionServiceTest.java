package io.github.jairandersonsouza.authorizer.services;


import io.github.jairandersonsouza.authorizer.controllers.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.entities.AccountBalance;
import io.github.jairandersonsouza.authorizer.entities.Transaction;
import io.github.jairandersonsouza.authorizer.exceptions.AccountNotExistsException;
import io.github.jairandersonsouza.authorizer.exceptions.TransactionRejectedException;
import io.github.jairandersonsouza.authorizer.repositories.TransactionRepository;
import io.github.jairandersonsouza.authorizer.util.AccountBalanceUtil;
import io.github.jairandersonsouza.authorizer.util.TransactionUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private CashTransactionService cashTransactionService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private AccountBalanceService accountBalanceService;

    @Mock
    private TransactionRepository transactionRepository;





}
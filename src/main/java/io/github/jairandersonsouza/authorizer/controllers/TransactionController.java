package io.github.jairandersonsouza.authorizer.controllers;

import io.github.jairandersonsouza.authorizer.factories.TransactionServiceFactory;
import io.github.jairandersonsouza.authorizer.interceptors.ResponseBuilder;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {

    @Autowired
    private TransactionServiceFactory transactionServiceFactory;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseBuilder transaction(@RequestBody @Valid TransactionInput transactionInput) {
        final var transactionService = this.transactionServiceFactory.getProcessor(transactionInput);
        transactionService.processTransaction(transactionInput);
        return ResponseBuilder.builder().code("00").build();
    }

}

package io.github.jairandersonsouza.authorizer.controllers;

import io.github.jairandersonsouza.authorizer.interceptors.ResponseBuilder;
import io.github.jairandersonsouza.authorizer.requests.TransactionInput;
import io.github.jairandersonsouza.authorizer.services.TransactionService;
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
    private TransactionService transactionService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseBuilder transaction(@RequestBody @Valid TransactionInput transactionInput) {
        this.transactionService.transact(transactionInput);
        return ResponseBuilder.builder().code("00").build();
    }

}

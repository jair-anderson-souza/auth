package io.github.jairandersonsouza.authorizer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    //TODO
    //Change return
    //create exception Handler
    @PostMapping
    public void transaction(@RequestBody @Valid TransactionInput transactionInput) {
        System.out.println(transactionInput);
    }

}

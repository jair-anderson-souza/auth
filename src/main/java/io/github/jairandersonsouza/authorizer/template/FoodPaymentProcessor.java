package io.github.jairandersonsouza.authorizer.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("FOOD")
public class FoodPaymentProcessor implements PaymentProcessor {

    @Autowired
    private Map<String, Specification> specifications;

    @Override
    public void startTransaction() {
        //chamar validation

    }

}

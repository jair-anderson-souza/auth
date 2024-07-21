package io.github.jairandersonsouza.authorizer.processors;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.CASH;
import static io.github.jairandersonsouza.authorizer.entities.MccEnum.MEAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MealPaymentProcessorTest {

    @InjectMocks
    private MealTransactionProcessor mealTransactionProcessor;

    @Test
    void testShouldGetMcc() {
        MccEnum mcc = this.mealTransactionProcessor.getMcc("2345");
        assertEquals(MEAL, mcc);

    }

}
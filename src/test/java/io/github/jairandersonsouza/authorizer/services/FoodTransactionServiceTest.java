package io.github.jairandersonsouza.authorizer.services;

import io.github.jairandersonsouza.authorizer.entities.MccEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.jairandersonsouza.authorizer.entities.MccEnum.FOOD;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FoodTransactionServiceTest {

    @InjectMocks
    private FoodTransactionService foodTransactionProcessor;

    @Test
    void testShouldGetMcc() {
        MccEnum mcc = this.foodTransactionProcessor.getMcc("2345");
        assertEquals(FOOD, mcc);

    }
}
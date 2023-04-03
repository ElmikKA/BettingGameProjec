package com.example.bettinggame.Functions;

import org.junit.jupiter.api.Test;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

public class RandomNumberGeneratorTest {

    @Test
    void testRandomNumberGenerator() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        SecureRandom secureRandom = randomNumberGenerator.randomNumberGenerator();

        assertNotNull(secureRandom);
        assertNotEquals(secureRandom, randomNumberGenerator.randomNumberGenerator());
    }
}

package com.example.bettinggame.Functions;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BetValidationTest {
    private final BetValidation betValidation = new BetValidation();

    @Test
    public void testValidBet() {
        Bet bet = new Bet(10.0, 50);
        assertDoesNotThrow(() -> betValidation.validateBet(bet));
    }

    @Test
    public void testInvalidNumber() {
        Bet bet = new Bet(10.0, 0);
        InvalidBetException exception = assertThrows(InvalidBetException.class, () -> betValidation.validateBet(bet));
        assertEquals("Number must be between 1 and 100", exception.getMessage());
    }

    @Test
    public void testInvalidAmount() {
        Bet bet = new Bet(-5.0, 10);
        InvalidBetException exception = assertThrows(InvalidBetException.class, () -> betValidation.validateBet(bet));
        assertEquals("Bet must be greater than 0.", exception.getMessage());
    }
}

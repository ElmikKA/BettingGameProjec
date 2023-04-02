package com.example.bettinggame.Moduls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Functions.BetValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BetTest {

    @InjectMocks
    private BetValidation betValidation;

    @Test
    public void testValidBet() throws InvalidBetException {
        Bet bet = new Bet(10, 50);
        betValidation.validateBet(bet);
        assertEquals(10, bet.getBetAmount());
        assertEquals(50, bet.getSelectedNumber());
    }

    @Test
    public void testInvalidBet() {
        Bet bet = new Bet(-10, 500);
        assertThrows(InvalidBetException.class, () -> {
            betValidation.validateBet(bet);
        });
    }
}

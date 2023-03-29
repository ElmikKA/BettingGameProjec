package com.example.bettinggame.Service;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BetValidationServiceTest {
    private BetValidationService betValidationService = new BetValidationService();

    @Test
    void validateBetWithInvalidNumberThrowsException() {
        Bet bet = new Bet();
        bet.setSelectedNumber(0);
        bet.setBetAmount(10);

        assertThrows(InvalidBetException.class, () -> {
            betValidationService.validateBet(bet);
        });
    }

    @Test
    void validateBetWithInvalidAmountThrowsException() {
        Bet bet = new Bet();
        bet.setBetAmount(0);
        bet.setSelectedNumber(10);

        assertThrows(InvalidBetException.class, () -> {
            betValidationService.validateBet(bet);
        });
    }

    @Test
    void validateBetWithValidInputDoesNotThrowException() {
        Bet bet = new Bet();
        bet.setBetAmount(10);
        bet.setSelectedNumber(10);

        assertDoesNotThrow(() -> {
            betValidationService.validateBet(bet);
        });
    }

}

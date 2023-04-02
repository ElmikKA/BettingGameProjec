package com.example.bettinggame.Functions;

import com.example.bettinggame.Functions.BetValidation;
import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BetValidationTest {
    @Mock
    private Bet bet;

    @InjectMocks
    private BetValidation betValidation;

    @Test
    void validateBetWithInvalidNumberThrowsException() {
        lenient().when(bet.getSelectedNumber()).thenReturn(0);
        assertThrows(InvalidBetException.class, () -> betValidation.validateBet(bet));
    }

    @Test
    void validateBetWithInvalidAmountThrowsException() {
        when(bet.getSelectedNumber()).thenReturn(10);
        when(bet.getBetAmount()).thenReturn(0.0);
        assertThrows(InvalidBetException.class, () -> betValidation.validateBet(bet));
    }

    @Test
    void validateBetWithValidInputDoesNotThrowException() {
        when(bet.getSelectedNumber()).thenReturn(10);
        when(bet.getBetAmount()).thenReturn(10.0);
        assertDoesNotThrow(() -> betValidation.validateBet(bet));
    }
}

package com.example.bettinggame.Service;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
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
public class BetValidationServiceTest {
    @Mock
    private Bet bet;

    @InjectMocks
    private BetValidationService betValidationService;

    @Test
    void validateBetWithInvalidNumberThrowsException() {
        lenient().when(bet.getSelectedNumber()).thenReturn(0);
        assertThrows(InvalidBetException.class, () -> betValidationService.validateBet(bet));
    }

    @Test
    void validateBetWithInvalidAmountThrowsException() {
        when(bet.getSelectedNumber()).thenReturn(10);
        when(bet.getBetAmount()).thenReturn(0.0);
        assertThrows(InvalidBetException.class, () -> betValidationService.validateBet(bet));
    }

    @Test
    void validateBetWithValidInputDoesNotThrowException() {
        when(bet.getSelectedNumber()).thenReturn(10);
        when(bet.getBetAmount()).thenReturn(10.0);
        assertDoesNotThrow(() -> betValidationService.validateBet(bet));
    }
}

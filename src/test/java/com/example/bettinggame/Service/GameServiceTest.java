package com.example.bettinggame.Service;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
import com.example.bettinggame.Moduls.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private WinCalculationService winCalculationService;

    @InjectMocks
    private GameService gameService;

    @Test
    void playGame_winningBet_returnsResultWithWin() throws InvalidBetException {
        Bet bet = new Bet(10.0, 50);
        when(winCalculationService.calculateWin(bet)).thenReturn(20.0);
        Result result = gameService.playGame(bet);
        verify(winCalculationService).calculateWin(bet);
        assertEquals(20.0, result.getWin(),0.01);
    }

    @Test
    void playGame_losingBet_returnsResultWithZeroWin() throws InvalidBetException{
        Bet bet = new Bet(10.0, 50);
        Result result = gameService.playGame(bet);
        assertEquals(0.0, result.getWin(), 0.01);
    }




}

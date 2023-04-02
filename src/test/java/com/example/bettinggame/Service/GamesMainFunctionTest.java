package com.example.bettinggame.Service;

import com.example.bettinggame.Functions.GamesMainFunction;
import com.example.bettinggame.Functions.WinCalculation;
import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GamesMainFunctionTest {

    @Mock
    private WinCalculation winCalculation;

    @InjectMocks
    private GamesMainFunction gamesMainFunction;

    @Test
    void playGame_winningBet_returnsResultWithWin() throws InvalidBetException {
        Bet bet = new Bet(10.0, 50);
        when(winCalculation.calculateWin(bet)).thenReturn(20.0);
        Result result = gamesMainFunction.playGame(bet);
        verify(winCalculation).calculateWin(bet);
        assertEquals(20.0, result.getWin(),0.01);
    }

    @Test
    void playGame_losingBet_returnsResultWithZeroWin() throws InvalidBetException{
        Bet bet = new Bet(10.0, 50);
        Result result = gamesMainFunction.playGame(bet);
        assertEquals(0.0, result.getWin(), 0.01);
    }




}

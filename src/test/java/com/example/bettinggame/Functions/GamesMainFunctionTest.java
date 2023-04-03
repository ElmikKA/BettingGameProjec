package com.example.bettinggame.Functions;

import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.BetRepository;
import com.example.bettinggame.Repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamesMainFunctionTest {

    @Mock
    private RandomNumberGenerator randomNumberGenerator;
    @Mock
    private WinCalculation winCalculation;
    @Mock
    private ResultRepository resultRepository;
    @Mock
    private BetRepository betRepository;

    @BeforeEach
    void setUp() {
        randomNumberGenerator = Mockito.mock(RandomNumberGenerator.class);
        winCalculation = Mockito.mock(WinCalculation.class);
        resultRepository = Mockito.mock(ResultRepository.class);
        betRepository = Mockito.mock(BetRepository.class);
    }

    @Test
    void testPlayGame_WinTheGame() {
        GamesMainFunction gamesMainFunction = new GamesMainFunction(randomNumberGenerator, winCalculation, resultRepository, betRepository);

        Bet bet = new Bet();
        bet.setBetAmount(10.0);
        bet.setSelectedNumber(50);

        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(randomNumberGenerator.randomNumberGenerator()).thenReturn(secureRandom);
        Mockito.when(secureRandom.nextInt(1, 101)).thenReturn(40);

        double expectedWinAmount = 20.0;
        Mockito.when(winCalculation.calculateWin(bet)).thenReturn(expectedWinAmount);

        Result expected = new Result(expectedWinAmount);

        Result result = gamesMainFunction.playGame(bet);

        assertEquals(expected.getWinAmount(), result.getWinAmount());
    }
    @Test
    void testPlayGame_LoseTheGame() {
        GamesMainFunction gamesMainFunction = new GamesMainFunction(randomNumberGenerator, winCalculation, resultRepository, betRepository);

        Bet bet = new Bet();
        bet.setBetAmount(10.9);
        bet.setSelectedNumber(50);

        SecureRandom secureRandom = Mockito.mock(SecureRandom.class);
        Mockito.when(randomNumberGenerator.randomNumberGenerator()).thenReturn(secureRandom);
        Mockito.when(secureRandom.nextInt(1, 101)).thenReturn(60);


        Result expected = new Result(0.0);
        Result result = gamesMainFunction.playGame(bet);

        assertEquals(expected.getWinAmount(), result.getWinAmount());


    }
}

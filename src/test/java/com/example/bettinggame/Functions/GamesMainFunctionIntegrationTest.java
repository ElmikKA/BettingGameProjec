package com.example.bettinggame.Functions;

import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.BetRepository;
import com.example.bettinggame.Repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class GamesMainFunctionIntegrationTest {
    @Autowired
    private BetRepository betRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Mock
    private RandomNumberGenerator randomNumberGenerator;

    @Spy
    private WinCalculation winCalculation;

    @Autowired
    private GamesMainFunction gamesMainFunction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlayGame() {
        SecureRandom secureRandom = new SecureRandom();
        Bet bet = new Bet(50.0, secureRandom.nextInt(1, 101));

        bet.setBetAmount(50);
        Result result = gamesMainFunction.playGame(bet);

        assertEquals(50.0, bet.getBetAmount(), 0.0);
        assertEquals(bet.getSelectedNumber(), betRepository.findAll().get(0).getSelectedNumber());
        assertEquals(result.getWinAmount(), resultRepository.findAll().get(0).getWinAmount());
    }
}
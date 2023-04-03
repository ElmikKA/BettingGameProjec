package com.example.bettinggame.Functions;

import com.example.bettinggame.Models.Bet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class WinCalculationClassTest {

    private WinCalculation winCalculation;

    @BeforeEach
    public void setUp() {
        winCalculation = new WinCalculation();
    }

    @Test
    public void testCalculateWin() {
        Bet bet = new Bet();
        bet.setBetAmount(10.0);
        bet.setSelectedNumber(50);

        double expectedWinAmount = 19.8;
        double result = winCalculation.calculateWin(bet);

        assertEquals(expectedWinAmount, result, 0.01);
    }
}

package com.example.bettinggame.Functions;

import com.example.bettinggame.Models.Bet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WinCalculationClassIntegrationTest {
    @Autowired
    private WinCalculation winCalculation;

    @Test
    void testCalculateWin() {
        Bet bet = new Bet();
        bet.setBetAmount(10.0);
        bet.setSelectedNumber(50);

        double expectedWinAmount = 19.8;
        double actualWinAmount = winCalculation.calculateWin(bet);

        assertEquals(expectedWinAmount, actualWinAmount, 0.01);
    }
}


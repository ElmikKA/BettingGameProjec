package com.example.bettinggame.Service;

import com.example.bettinggame.Functions.WinCalculation;
import com.example.bettinggame.Models.Bet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WinCalculationClassTest {

    @InjectMocks
    private WinCalculation winCalculation;

    @Test
    public void testCalculateWin(){
        Bet bet = new Bet(50.0, 10);
        double expectedWin = 55.0;
        double actualWin = winCalculation.calculateWin(bet);
        assertEquals(expectedWin, actualWin, 0, String.valueOf(0.01));
    }
}

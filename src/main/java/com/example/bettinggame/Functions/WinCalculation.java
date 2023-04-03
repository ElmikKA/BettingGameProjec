package com.example.bettinggame.Functions;

import com.example.bettinggame.Models.Bet;
import org.springframework.stereotype.Component;

@Component
public class WinCalculation {
    public double calculateWin(Bet bet){
        double chance = 99.0 / (100 - bet.getSelectedNumber());
        double win = bet.getBetAmount() * chance;
        return (Math.round(win * 100.0) / 100.0);
    }
}

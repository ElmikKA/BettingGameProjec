package com.example.bettinggame.Service;

import com.example.bettinggame.Moduls.Bet;
import org.springframework.stereotype.Service;

@Service
public class WinCalculationService {

    public double calculateWin(Bet bet){
        double chance = 99.0 / (100 - bet.getSelectedNumber());
        double win = bet.getBetAmount() * chance;
        return (Math.round(win * 100.0) / 100.0);
    }
}

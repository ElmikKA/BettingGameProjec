package com.example.bettinggame.Functions;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import org.springframework.stereotype.Component;

@Component
public class BetValidation {

    public void validateBet(Bet bet) throws InvalidBetException {
        if(bet.getSelectedNumber() < 1 || bet.getSelectedNumber() > 100){
            throw new InvalidBetException("Number must be between 1 and 100");
        }
        if(bet.getBetAmount() <= 0){
            throw new InvalidBetException("Bet must be greater than 0.");
        }
    }
}

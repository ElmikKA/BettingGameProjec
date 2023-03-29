package com.example.bettinggame.Service;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
import org.springframework.stereotype.Service;

@Service
public class BetValidationService {

    public void validateBet(Bet bet) throws InvalidBetException {
        if(bet.getSelectedNumber() < 1 || bet.getSelectedNumber() > 100){
            throw new InvalidBetException("Number must be between 1 and 100");
        }
        if(bet.getBetAmount() <= 0){
            throw new InvalidBetException("Bet must be greater than 0.");
        }
    }
}

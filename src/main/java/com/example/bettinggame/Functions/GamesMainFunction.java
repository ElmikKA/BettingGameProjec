package com.example.bettinggame.Functions;

import com.example.bettinggame.Controllers.GameController;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.concurrent.ThreadLocalRandom;

@Service
public class GamesMainFunction {

    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    final private WinCalculation winCalculation;

    public GamesMainFunction(WinCalculation winCalculation) {
        this.winCalculation = winCalculation;
    }

    public Result playGame(Bet bet) {
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 101);
        double win = 0;
        Result result = new Result(win);
        if (bet.getSelectedNumber() > randomNumber) {
            win = winCalculation.calculateWin(bet);
            result.setWinAmount(win);
            logger.info("You have Won " + win + " Dollars!");
        } else {
            logger.info("You have lost");
        }

        return result;
    }
}

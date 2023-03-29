package com.example.bettinggame.Service;

import com.example.bettinggame.Controllers.GameController;
import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
import com.example.bettinggame.Moduls.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.concurrent.ThreadLocalRandom;

@Service
public class GameService {

    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    final private WinCalculationService winCalculationService;

    public GameService(WinCalculationService winCalculationService) {
        this.winCalculationService = winCalculationService;
    }

    public Result playGame(Bet bet) {
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 101);
        double win = 0;
        Result result = new Result(win);
        if (bet.getSelectedNumber() > randomNumber) {
            win = winCalculationService.calculateWin(bet);
            result.setWin(win);
            logger.info("You have Won " + win + " Dollars!");
        } else {
            logger.info("You have lost");
        }

        return result;
    }
}

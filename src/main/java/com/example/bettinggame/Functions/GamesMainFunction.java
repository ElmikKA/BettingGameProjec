package com.example.bettinggame.Functions;

import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.BetRepository;
import com.example.bettinggame.Repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Slf4j
@RequiredArgsConstructor
@Component
public class GamesMainFunction {

    final private RandomNumberGenerator randomNumberGenerator;
    final private WinCalculation winCalculation;
    final private ResultRepository resultRepository;
    final private BetRepository betRepository;

    public Result playGame(Bet bet) {
        SecureRandom secureNumber = randomNumberGenerator.randomNumberGenerator();
        int randomNumber = secureNumber.nextInt(1, 101);
        double win = 0;
        Result result = new Result(win);
        if (bet.getSelectedNumber() > randomNumber) {
            win = winCalculation.calculateWin(bet);
            result.setWinAmount(win);
            log.info("You have Won " + win + " Dollars!");
        } else {
            log.info("You have lost");
        }
        betRepository.save(bet);
        resultRepository.save(result);

        return result;
    }
}

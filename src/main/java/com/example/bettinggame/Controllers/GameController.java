package com.example.bettinggame.Controllers;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.PlayerRepository;
import com.example.bettinggame.Repository.ResultRepository;
import com.example.bettinggame.Functions.BetValidation;
import com.example.bettinggame.Functions.GamesMainFunction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
@RequestMapping("/api")
public class GameController {

    private final ResultRepository resultRepository;
    private final PlayerRepository playerRepository;
    private final BetValidation betValidation;
    private final GamesMainFunction gamesMainFunction;
    private final Logger logger = LoggerFactory.getLogger(GameController.class);


    public GameController(ResultRepository resultRepository, PlayerRepository playerRepository, BetValidation betValidation, GamesMainFunction gamesMainFunction) {
        this.resultRepository = resultRepository;
        this.playerRepository = playerRepository;
        this.betValidation = betValidation;
        this.gamesMainFunction = gamesMainFunction;
    }

    @PostMapping("/play")
    public ResponseEntity<Result> bet(@RequestBody Bet bet) {
        try{
            betValidation.validateBet(bet);
            Result result = gamesMainFunction.playGame(bet);
            playerRepository.save(bet);
            resultRepository.save(result);
            return ResponseEntity.ok(result);
        } catch (InvalidBetException e) {
           logger.info("Invalid bet: " + e.getMessage());
           return ResponseEntity.badRequest().build();
        }
    }
}

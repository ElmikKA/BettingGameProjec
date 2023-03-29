package com.example.bettinggame.Controllers;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
import com.example.bettinggame.Moduls.Result;
import com.example.bettinggame.Repository.PlayerRepository;
import com.example.bettinggame.Repository.ResultRepository;
import com.example.bettinggame.Service.BetValidationService;
import com.example.bettinggame.Service.GameService;
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
    private final BetValidationService betValidationService;
    private final GameService gameService;
    private final Logger logger = LoggerFactory.getLogger(GameController.class);


    public GameController(ResultRepository resultRepository, PlayerRepository playerRepository, BetValidationService betValidationService, GameService gameService) {
        this.resultRepository = resultRepository;
        this.playerRepository = playerRepository;
        this.betValidationService = betValidationService;

        this.gameService = gameService;
    }

    @PostMapping("/play")
    public ResponseEntity<Result> bet(@RequestBody Bet bet) {
        try{
            betValidationService.validateBet(bet);
            Result result = gameService.playGame(bet);
            playerRepository.save(bet);
            resultRepository.save(result);
            return ResponseEntity.ok(result);
        } catch (InvalidBetException e) {
           logger.info("Invalid bet: " + e.getMessage());
           return ResponseEntity.badRequest().build();
        }
    }


}

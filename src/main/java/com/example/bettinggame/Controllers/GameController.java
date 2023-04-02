package com.example.bettinggame.Controllers;

import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.BetRepository;
import com.example.bettinggame.Repository.ResultRepository;
import com.example.bettinggame.Functions.BetValidation;
import com.example.bettinggame.Functions.GamesMainFunction;
import com.example.bettinggame.Service.BetService;
import com.example.bettinggame.Service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
public class GameController {

    private final ResultRepository resultRepository;
    private final BetRepository betRepository;
    private final BetValidation betValidation;
    private final GamesMainFunction gamesMainFunction;
    private final BetService betService;
    private final ResultService resultService;
    private final Logger logger = LoggerFactory.getLogger(GameController.class);


    public GameController(ResultRepository resultRepository, BetRepository betRepository, BetValidation betValidation, GamesMainFunction gamesMainFunction, BetService betService, ResultService resultService) {
        this.resultRepository = resultRepository;
        this.betRepository = betRepository;
        this.betValidation = betValidation;
        this.gamesMainFunction = gamesMainFunction;
        this.betService = betService;
        this.resultService = resultService;
    }

    @GetMapping("/bets")
    public List<Bet> getAllBets() {
        return betService.getAllBets();
    }

    @GetMapping("/results")
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @PostMapping("/play")
    public ResponseEntity<Result> bet(@RequestBody Bet bet) {
        try{
            betValidation.validateBet(bet);
            Result result = gamesMainFunction.playGame(bet);
            betRepository.save(bet);
            resultRepository.save(result);
            return ResponseEntity.ok(result);
        } catch (InvalidBetException e) {
           logger.info("Invalid bet: " + e.getMessage());
           return ResponseEntity.badRequest().build();
        }
    }
}

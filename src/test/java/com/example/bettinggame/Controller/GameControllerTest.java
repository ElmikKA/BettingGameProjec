package com.example.bettinggame.Controller;

import com.example.bettinggame.Controllers.GameController;
import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Functions.BetValidation;
import com.example.bettinggame.Functions.GamesMainFunction;
import com.example.bettinggame.Service.BetService;
import com.example.bettinggame.Service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private BetValidation betValidation;

    @Mock
    private GamesMainFunction gamesMainFunction;
    @Mock
    private BetService betService;
    @Mock
    private ResultService resultService;

    private GameController gameController;

    @BeforeEach
    public void setup() {
        gameController = new GameController(betValidation, gamesMainFunction, betService, resultService);
    }

    @Test
    void testGetAllBets() {
        Bet bet1 = new Bet(20.0, 20);
        Bet bet2 = new Bet(50.0, 30);
        List<Bet> expectedBets = Arrays.asList(bet1, bet2);

        when(betService.getAllBets()).thenReturn(expectedBets);

        List<Bet> actualBets = gameController.getAllBets();

        assertEquals(expectedBets, actualBets);
    }
    @Test
    public void testGetAllResults() {
        List<Result> expectedResults = new ArrayList<Result>();
        expectedResults.add(new Result(10));
        expectedResults.add(new Result(20));

        when(resultService.getAllResults()).thenReturn(expectedResults);

        List<Result> actualResults = gameController.getAllResults();

        assertEquals(expectedResults, actualResults);
    }


    @Test
    public void testValidBet() throws InvalidBetException {
        Bet bet = new Bet(50.0, 42);
        Result result = new Result(100.0);
        when(gamesMainFunction.playGame(bet)).thenReturn(result);

        ResponseEntity<Result> response = gameController.bet(bet);

        verify(betValidation, times(1)).validateBet(bet);
        assertEquals(result, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testInvalidBet() throws InvalidBetException {
        Bet bet = new Bet(0.0, 42);
        String expectedErrorMessage = "Bet amount must be greater than 0";
        doThrow(new InvalidBetException(expectedErrorMessage)).when(betValidation).validateBet(bet);

        ResponseEntity<Result> response = gameController.bet(bet);

        verify(betValidation, times(1)).validateBet(bet);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}


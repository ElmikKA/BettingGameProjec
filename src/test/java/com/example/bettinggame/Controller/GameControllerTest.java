package com.example.bettinggame.Controller;

import com.example.bettinggame.Controllers.GameController;
import com.example.bettinggame.Exeption.InvalidBetException;
import com.example.bettinggame.Moduls.Bet;
import com.example.bettinggame.Moduls.Result;
import com.example.bettinggame.Repository.PlayerRepository;
import com.example.bettinggame.Repository.ResultRepository;
import com.example.bettinggame.Service.BetValidationService;
import com.example.bettinggame.Service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private BetValidationService betValidationService;

    @Mock
    private GameService gameService;

    private GameController gameController;

    @BeforeEach
    public void setup() {
        gameController = new GameController(resultRepository, playerRepository, betValidationService, gameService);
    }

    @Test
    public void testValidBet() throws InvalidBetException {
        Bet bet = new Bet(50.0, 42);
        Result result = new Result(100.0);
        when(gameService.playGame(bet)).thenReturn(result);
        when(playerRepository.save(bet)).thenReturn(bet);
        when(resultRepository.save(result)).thenReturn(result);

        ResponseEntity<Result> response = gameController.bet(bet);

        verify(betValidationService, times(1)).validateBet(bet);
        verify(playerRepository, times(1)).save(bet);
        verify(resultRepository, times(1)).save(result);
        assertEquals(result, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testInvalidBet() throws InvalidBetException {
        Bet bet = new Bet(0.0, 42);
        String expectedErrorMessage = "Bet amount must be greater than 0";
        doThrow(new InvalidBetException(expectedErrorMessage)).when(betValidationService).validateBet(bet);

        ResponseEntity<Result> response = gameController.bet(bet);

        verify(betValidationService, times(1)).validateBet(bet);
        verify(playerRepository, times(0)).save(bet);
        verify(resultRepository, times(0)).save(any(Result.class));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}


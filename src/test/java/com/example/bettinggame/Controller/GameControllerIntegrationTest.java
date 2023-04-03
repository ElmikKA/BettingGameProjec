package com.example.bettinggame.Controller;

import com.example.bettinggame.Functions.RandomNumberGenerator;
import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Service.BetService;
import com.example.bettinggame.Service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BetService betService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {
        betService.deleteAllBets();
        resultService.deleteAllResults();
    }


    @Test
    void testBet_WithInvalidBet_ShouldReturnBadRequest() {
        Bet bet = new Bet(20.0, 200);
        ResponseEntity<Result> response = restTemplate.postForEntity("/api/play", bet, Result.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void testBet_WithValidBet_ShouldReturnAllBets() {
        betService.saveBet(new Bet(20.0, 20));
        betService.saveBet(new Bet(50.0, 30));

        ResponseEntity<List<Bet>> response = restTemplate.exchange("/api/bets", HttpMethod.GET, null, new ParameterizedTypeReference<List<Bet>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testResult_WithValidResult_ShouldReturnAllResults() {
        resultService.saveResult(new Result(20.0));
        resultService.saveResult(new Result(50.0));

        ResponseEntity<List<Result>> response = restTemplate.exchange("/api/results", HttpMethod.GET, null, new ParameterizedTypeReference<List<Result>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testPlayGame_Success() {
        Bet bet = new Bet(20.0, 20);

        ResponseEntity<Result> response = restTemplate.postForEntity("/api/play", bet, Result.class);
        double expectedWin = 4.0;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(4.0, expectedWin, 0.01);
    }

    @Test
    void testPlayGame_InvalidBet() {
        Bet bet = new Bet(1000.0, 101);

        ResponseEntity<Result> response = restTemplate.postForEntity("/api/play", bet, Result.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}

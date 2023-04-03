package com.example.bettinggame.Service;

import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Repository.BetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BetServiceIntegrationTest {

    @Autowired
    private BetService betService;

    @Autowired
    private BetRepository betRepository;

    @Test
    public void testSaveBet() {
        Bet bet = new Bet();
        bet.setBetAmount(10.0);
        bet.setSelectedNumber(10);
        betService.saveBet(bet);

        List<Bet> allBets = betRepository.findAll();
        assertEquals(1, allBets.size());
        assertEquals(bet, allBets.get(0));
    }

    @Test
    public void testGetAllBets() {
        Bet bet1 = new Bet();
        bet1.setBetAmount(10.0);
        bet1.setSelectedNumber(10);
        betService.saveBet(bet1);

        Bet bet2 = new Bet();
        bet2.setBetAmount(10.0);
        bet2.setSelectedNumber(10);
        betService.saveBet(bet2);

        List<Bet> allBets = betService.getAllBets();
        assertEquals(2, allBets.size());
        assertEquals(bet1, allBets.get(0));
        assertEquals(bet2, allBets.get(1));
    }

    @Test
    public void testGetBetById() {
        Bet bet1 = new Bet();
        bet1.setBetAmount(10.0);
        bet1.setSelectedNumber(10);
        betService.saveBet(bet1);

        Bet bet2 = new Bet();
        bet2.setBetAmount(10.0);
        bet2.setSelectedNumber(10);
        betService.saveBet(bet2);

        Bet foundBet = betService.getBetById(bet2.getId());
        assertEquals(bet2, foundBet);
    }

    @Test
    public void testDeleteBetById() {
        Bet bet = new Bet();
        bet.setBetAmount(10.0);
        bet.setSelectedNumber(10);
        betService.saveBet(bet);

        betService.deleteBetById(bet.getId());
        List<Bet> allBets = betRepository.findAll();
        assertTrue(allBets.isEmpty());
    }

    @Test
    public void testDeleteAllBets() {
        Bet bet1 = new Bet();
        bet1.setBetAmount(10.0);
        bet1.setSelectedNumber(10);
        betService.saveBet(bet1);

        Bet bet2 = new Bet();
        bet2.setBetAmount(10.0);
        bet2.setSelectedNumber(10);
        betService.saveBet(bet2);

        betService.deleteAllBets();
        List<Bet> allBets = betRepository.findAll();
        assertTrue(allBets.isEmpty());
    }
}

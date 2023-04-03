package com.example.bettinggame.Service;

import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Repository.BetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BetServiceTest {
    private BetService betService;

    @Mock
    private BetRepository betRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        betService = new BetService(betRepository);
    }

    @Test
    public void testGetAllBets() {
        List<Bet> bets = Arrays.asList(
                new Bet(20.0, 20),
                new Bet(50.0, 30)
        );
        when(betRepository.findAll()).thenReturn(bets);

        List<Bet> result = betService.getAllBets();

        verify(betRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetBetById() {
        Bet bet = new Bet(20.0, 20);
        when(betRepository.findById(1L)).thenReturn(java.util.Optional.of(bet));

        Bet result = betService.getBetById(1L);

        verify(betRepository, times(1)).findById(1L);
        assertEquals(bet, result);
    }

    @Test
    public void testSaveBet() {
        Bet bet = new Bet(20.0, 20);
        when(betRepository.save(bet)).thenReturn(bet);

        Bet result = betService.saveBet(bet);

        verify(betRepository, times(1)).save(bet);
        assertEquals(bet, result);
    }

    @Test
    public void testDeleteBetById() {
        Long id = 1L;

        betService.deleteBetById(id);

        verify(betRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteAllBets() {
        betService.deleteAllBets();

        verify(betRepository, times(1)).deleteAll();
    }
}

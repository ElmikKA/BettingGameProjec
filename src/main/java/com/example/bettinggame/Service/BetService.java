package com.example.bettinggame.Service;

import com.example.bettinggame.Models.Bet;
import com.example.bettinggame.Repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BetService {

    @Autowired
    private final BetRepository betRepository;

    public List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    public Bet getBetById(Long id) {
        return betRepository.findById(id).orElse(null);
    }

    public Bet saveBet(Bet bet) {
        return betRepository.save(bet);
    }

    public void deleteBetById(Long id) {
        betRepository.deleteById(id);
    }
    public void deleteAllBets() {
        betRepository.deleteAll();
    }

}

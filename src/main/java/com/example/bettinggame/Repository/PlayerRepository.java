package com.example.bettinggame.Repository;

import com.example.bettinggame.Moduls.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Bet, Long> {
}

package com.example.bettinggame.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private double betAmount;
    @NotNull
    @Min(1)
    @Max(100)
    private int selectedNumber;

    public Bet() {}

    public Bet(double betAmount, int selectedNumber) {
        this.betAmount = betAmount;
        this.selectedNumber = selectedNumber;
    }

}
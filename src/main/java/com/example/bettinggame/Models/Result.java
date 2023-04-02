package com.example.bettinggame.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "win")
    private double winAmount;


    public Result(double winAmount) {
        this.winAmount = winAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "win=" + winAmount +
                '}';
    }
}

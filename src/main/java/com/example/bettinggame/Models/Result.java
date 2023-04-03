package com.example.bettinggame.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Result")
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double winAmount;

    public Result () {

    }
    public Result(double winAmount) {
        this.winAmount = winAmount;
    }
    @Override
    public String toString() {
        return "Result{" +
                "win=" + winAmount +
                '}';
    }
}

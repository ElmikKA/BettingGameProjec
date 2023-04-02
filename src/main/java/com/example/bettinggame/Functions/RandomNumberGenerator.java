package com.example.bettinggame.Functions;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
@Component
public class RandomNumberGenerator {

    public SecureRandom randomNumberGenerator() {
        return new SecureRandom();
    }
}

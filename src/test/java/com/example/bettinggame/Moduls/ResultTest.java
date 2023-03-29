package com.example.bettinggame.Moduls;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResultTest {

    @Mock
    private Result result;

    @Test
    public void testGetWin() {
        double win = 50.0;
        when(result.getWin()).thenReturn(win);
        assertEquals(win, result.getWin());
    }

    @Test
    public void testSetWin() {
        double win = 0;
        Result result = new Result(win);
        win = 100;
        result.setWin(win);
        assertEquals(win, result.getWin());
    }
}

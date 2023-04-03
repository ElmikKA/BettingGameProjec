package com.example.bettinggame.Service;

import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResultServiceTest {
    @Mock
    ResultRepository resultRepository;

    ResultService resultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resultService = new ResultService(resultRepository);
    }

    @Test
    void testGetAllResults() {
        List<Result> results = new ArrayList<>();
        results.add(new Result(20.0));
        results.add(new Result(30.0));

        when(resultRepository.findAll()).thenReturn(results);

        List<Result> actualResults = resultService.getAllResults();

        assertEquals(results, actualResults);
        verify(resultRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Result result = new Result(20.0);

        when(resultRepository.findById(1L)).thenReturn(Optional.of(result));

        Result actualResult = resultService.findById(1L);

        assertEquals(result, actualResult);
        verify(resultRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveResult() {
        Result result = new Result(20.0);

        when(resultRepository.save(result)).thenReturn(result);

        Result savedResult = resultService.saveResult(result);

        assertEquals(result, savedResult);
        verify(resultRepository, times(1)).save(result);
    }

    @Test
    void testDeletedResultById() {
        Long id = 1L;
        resultService.deletedResultById(id);

        verify(resultRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteAllResults() {
        resultService.deleteAllResults();

        verify(resultRepository, times(1)).deleteAll();
    }
}


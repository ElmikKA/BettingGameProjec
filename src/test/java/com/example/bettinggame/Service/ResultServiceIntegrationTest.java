package com.example.bettinggame.Service;

import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.ResultRepository;
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
public class ResultServiceIntegrationTest {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ResultService resultService;

    @Test
    public void testSaveResult() {
        Result result = new Result();
        result.setWinAmount(200);
        resultService.saveResult(result);

        List<Result> allResults = resultRepository.findAll();
        assertEquals(1, allResults.size());
        assertEquals(result, allResults.get(0));
    }

    @Test
    public void testGetAllResults(){
        Result result1 = new Result();
        result1.setWinAmount(200);
        resultService.saveResult(result1);

        Result result2 = new Result();
        result2.setWinAmount(200);
        resultService.saveResult(result2);

        List<Result> allResults = resultService.getAllResults();
        assertEquals(2, allResults.size());
        assertEquals(result1, allResults.get(0));
        assertEquals(result2, allResults.get(1));
    }

    @Test
    public void testGetResultById() {
        Result result1 = new Result();
        result1.setWinAmount(200);
        resultService.saveResult(result1);

        Result result2 = new Result();
        result2.setWinAmount(200);
        resultService.saveResult(result2);

        Result foundResult = resultService.findById(result2.getId());
        assertEquals(result2, foundResult);
    }

    @Test
    public void testDeleteResultById() {
        Result result = new Result();
        result.setWinAmount(200);
        resultService.saveResult(result);

        resultService.deletedResultById(result.getId());
        List<Result> allResults = resultRepository.findAll();
        assertTrue(allResults.isEmpty());
    }

    @Test
    public void testDeleteAllResults() {
        Result result1 = new Result();
        result1.setWinAmount(200);
        resultService.saveResult(result1);

        Result result2 = new Result();
        result2.setWinAmount(200);
        resultService.saveResult(result2);

        resultService.deleteAllResults();
        List<Result> allResults = resultRepository.findAll();
        assertTrue(allResults.isEmpty());
    }
}

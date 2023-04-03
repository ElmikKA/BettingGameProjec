package com.example.bettinggame.Service;

import com.example.bettinggame.Models.Result;
import com.example.bettinggame.Repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {

    @Autowired
    private final ResultRepository resultRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    //findResultById
    public Result findById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    //saveResult
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    // deleteById
    public void deletedResultById(Long id) {
        resultRepository.deleteById(id);
    }

    public void deleteAllResults(){
        resultRepository.deleteAll();
    }
}

package com.cognizant.hqllearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.hqllearn.model.Attempt;
import com.cognizant.hqllearn.repository.AttemptRepository;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Transactional(readOnly = true)
    public Attempt getAttempt(int userId, int attemptId) {
        return attemptRepository.getAttempt(userId, attemptId);
    }
}

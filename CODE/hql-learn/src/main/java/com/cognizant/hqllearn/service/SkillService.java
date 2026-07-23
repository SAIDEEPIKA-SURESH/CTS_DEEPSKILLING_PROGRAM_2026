package com.cognizant.hqllearn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.hqllearn.model.Skill;
import com.cognizant.hqllearn.repository.SkillRepository;

@Service
public class SkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Skill get(int id) {
        LOGGER.info("Start");
        return skillRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Skill skill) {
        LOGGER.info("Start");
        skillRepository.save(skill);
        LOGGER.info("End");
    }
}

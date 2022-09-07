package com.schoolManagement.service.impl;

import com.schoolManagement.model.Exams;
import com.schoolManagement.repository.ExamRepository;
import com.schoolManagement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public void save(Exams exams) {
        examRepository.save(exams);
    }

    @Override
    public List<Exams> findAll() {
        return examRepository.findAll();
    }
}

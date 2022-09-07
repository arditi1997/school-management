package com.schoolManagement.service;

import com.schoolManagement.model.Exams;

import java.util.List;

public interface ExamService {
    void save(Exams exams);

    List<Exams> findAll();
}

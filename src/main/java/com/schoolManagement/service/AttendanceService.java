package com.schoolManagement.service;

import com.schoolManagement.model.Attendance;

import java.util.List;

public interface AttendanceService {
    void save(Attendance attendance);

    List<Attendance> findAll();
}

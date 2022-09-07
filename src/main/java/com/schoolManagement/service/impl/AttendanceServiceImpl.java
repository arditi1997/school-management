package com.schoolManagement.service.impl;

import com.schoolManagement.model.Attendance;
import com.schoolManagement.repository.AttendanceRepository;
import com.schoolManagement.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public void save(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }
}

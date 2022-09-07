package com.schoolManagement.service.impl;

import com.schoolManagement.model.Role;
import com.schoolManagement.repository.RoleRepository;
import com.schoolManagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}

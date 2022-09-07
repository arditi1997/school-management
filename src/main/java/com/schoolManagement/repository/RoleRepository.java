package com.schoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRole(String role);

}

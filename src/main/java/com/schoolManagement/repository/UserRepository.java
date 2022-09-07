package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Role;
import com.schoolManagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	public User findByUsername(String name);

	public User findByResetToken(String reset);

	public List<User> findByRolesAndStatus(Role roles, String status);
	
	public List<User> findByRoles(Role roles);

	public User findByParentOfStudent(User user);

	public List<User> findByRoles_RoleAndNameLikeAndLastNameLike( String roleName, String name, String lastname);

	public User findByClasses(Classes classes);
	

}

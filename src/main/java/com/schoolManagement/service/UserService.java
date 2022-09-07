package com.schoolManagement.service;

import java.util.List;

import com.schoolManagement.model.Classes;
import com.schoolManagement.model.Role;
import org.springframework.mail.SimpleMailMessage;

import com.schoolManagement.model.User;

public interface UserService {

	public User findByUsername(String name);

	public User findByResetToken(String reset);

	public boolean isUserAlreadyPresent(User user);

	public User findUserByResetToken(String resetToken);

	public User findByEmail(String email);

	public void savePassword(User user, String password);

	public void save(User user);

	public User editUserSettings(String name, String surname);

	public List<User> findAll();

	public User getOne(int id);

	public User setUser(User user, int userId);

	public User setParent(User user, int parentId);

	public User setTeacher(User user, int teacherId);
	
	public User setStudent(User user, int studentId);

	public User setAdmin(User user, int adminId);

	public SimpleMailMessage sendEmail(User existingUser);

	public User registerUser(User user);
	
	public List<User> findByRoles_RoleAndNameLikeAndLastNameLike(String roleName, String name, String lastname);

	List<User> findByRoles(Role studentRole);

	User findByParentOfStudent(User user);

	void delete(User userParent);

	User findByClasses(Classes classes);
}

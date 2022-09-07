package com.schoolManagement.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.schoolManagement.model.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolManagement.repository.RoleRepository;
import com.schoolManagement.repository.UserRepository;
import com.schoolManagement.service.EmailService;
import com.schoolManagement.service.UserService;
import com.schoolManagement.model.Role;
import com.schoolManagement.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private BCryptPasswordEncoder encoder;

	// Check if a user is present
	@Override
	public boolean isUserAlreadyPresent(User user) {
		boolean isUserAlreadyExists = false;
		User existingUser = userRepository.findByEmail(user.getEmail());
		// If user is found in database, then then user already exists.
		if (existingUser != null) {
			isUserAlreadyExists = true;
		}
		return isUserAlreadyExists;
	}

	@Override
	public User findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	public User findByResetToken(String reset) {
		return userRepository.findByResetToken(reset);
	}

	@Override
	public User editUserSettings(String name, String surname) {

		String auth = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(auth);
		user.setLastName(surname);
		user.setName(name);
		userRepository.save(user);

		return user;
	}

	@Override
	public User setStudent(User user, int studentId) {
		user = userRepository.getOne(studentId);
		Role userRole = roleRepository.findByRole("SITE_STUDENT");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
	@Override
	public User setParent(User user, int parentId) {
		user = userRepository.getOne(parentId);
		Role userRole = roleRepository.findByRole("SITE_PARENT");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
	@Override
	public User setTeacher(User user, int teacherId) {
		user = userRepository.getOne(teacherId);
		Role userRole = roleRepository.findByRole("SITE_TEACHER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
	@Override
	public User setUser(User user, int userId) {
		user = userRepository.getOne(userId);
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}

	@Override
	public User setAdmin(User user, int adminId) {
		user = userRepository.getOne(adminId);
		Role userRole = roleRepository.findByRole("SITE_ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}

	// Create the email with verification token

	@Override
	public SimpleMailMessage sendEmail(User existingUser) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(existingUser.getEmail());
		mailMessage.setSubject("Complete Password Reset!");
		mailMessage.setFrom("arditmete2@gmail.com");
		mailMessage.setText("To complete the password reset process, please click here:" + "\n"
				+ "http://localhost:8083/reset?token=" + existingUser.getResetToken() + "\n"
				+ "Your verification token is:" + existingUser.getResetToken());

		// Send the email
		emailService.sendEmail(mailMessage);

		return mailMessage;
	}

	@Override
	public void savePassword(User user, String password) {
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User getOne(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public User registerUser(User user) {
		user.setResetToken(UUID.randomUUID().toString().substring(0, 8));
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findByRoles_RoleAndNameLikeAndLastNameLike(String rolename, String name, String lastname) {
		return userRepository.findByRoles_RoleAndNameLikeAndLastNameLike(rolename, name + "%", lastname +"%");
	}

	@Override
	public List<User> findByRoles(Role studentRole) {
		return userRepository.findByRoles(studentRole);
	}

	@Override
	public User findByParentOfStudent(User user) {
		return userRepository.findByParentOfStudent(user);
	}

	@Override
	public void delete(User userParent) {
		userRepository.delete(userParent);
	}

	@Override
	public User findByClasses(Classes classes) {
		return userRepository.findByClasses(classes);
	}

}

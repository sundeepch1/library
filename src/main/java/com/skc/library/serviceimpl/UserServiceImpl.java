package com.skc.library.serviceimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skc.library.model.User;
import com.skc.library.repository.UserRepository;
import com.skc.library.service.UserService;
import com.skc.library.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		String password = PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		user.setCreatedDate(new Date());
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>)userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String name) {
		return userRepository.findByEmailIgnoreCase(name);
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User user1= user.orElse(new User());
			return user1;
		}
		return null;
	}

	@Override
	public Boolean updateUser(User user) {
		User userDB = userRepository.findByEmailIgnoreCase(user.getEmail());
		user.setPassword(userDB.getPassword());
		if(userRepository.save(user) instanceof User) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean changePassword(HashMap<String, String> user) {
		String email = user.get("email");
		String oldPassword = user.get("oldPassword");
		String newPassword = user.get("newPassword");
		
		User userDb = userRepository.findByEmailIgnoreCase(email); 
		if(PasswordUtil.getBCryptPasswordEncoder().matches(oldPassword, userDb.getPassword())) {
			userDb.setPassword(PasswordUtil.getPasswordHash(newPassword));
			userRepository.save(userDb);
			return true;
		}
		return false;
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

}

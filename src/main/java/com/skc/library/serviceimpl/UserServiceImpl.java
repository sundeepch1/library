package com.skc.library.serviceimpl;

import java.util.Date;
import java.util.List;

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

}

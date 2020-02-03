package com.skc.library.service;

import java.util.HashMap;
import java.util.List;

import com.skc.library.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String name);
	
	User getUserById(Long phoneNumner);

	Boolean updateUser(User user);

	Boolean changePassword(HashMap<String, String> user);

	void deleteUserById(Long userId);

}

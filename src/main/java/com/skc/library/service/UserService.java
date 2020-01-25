package com.skc.library.service;

import java.util.List;

import com.skc.library.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String name);

}

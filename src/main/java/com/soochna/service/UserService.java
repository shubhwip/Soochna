package com.soochna.service;

import java.util.List;

import com.soochna.entity.User;

public interface UserService {
	
	public User findByUid(String uid);
	public User saveUser(User user);
	public boolean isUserExist(User user);
	public boolean isUidExist(String uid);
	public boolean isUidNotValid(User user);
	public List<User> findAll();
	public User findByUidAndPassword(String uid, String passwordHash);
}	
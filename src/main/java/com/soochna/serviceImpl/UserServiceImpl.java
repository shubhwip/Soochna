/**
 * 
 */
package com.soochna.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soochna.entity.User;
import com.soochna.repositories.UserRepository;
import com.soochna.service.UserService;

/**
 * @author root
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean isUserExist(User user) {
		return findByUid(user.getUid()) != null;
	}

	@Override
	public User findByUid(String uid) {
		return userRepository.findByUid(uid);
	}

	@Override
	public boolean isUidNotValid(User user) {
		if (user.getUid().length() != 12) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean isUidExist(String uid) {
		return userRepository.findByUid(uid) != null;
	}

	@Override
	public User findByUidAndPassword(String uid, String passwordHash) {
		return userRepository.findByUidAndPassword(uid, passwordHash);
	}

}
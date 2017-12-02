/**
 * 
 */
package com.soochna.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.soochna.entity.User;

/**
 * @author root
 *
 */
public interface UserRepository extends CrudRepository<User, Serializable> {

	User findByUid(String uid);

	User findByUidAndPassword(String uid, String passwordHash);

	List<User> findAll();
}
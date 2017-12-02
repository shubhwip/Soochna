package com.soochna.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soochna.dao.MinistryDetails;
import com.soochna.dao.UserDetails;
import com.soochna.dao.UserLogin;
import com.soochna.entity.User;
import com.soochna.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createUser(@RequestBody User user, UriComponentsBuilder uriComponentBuilder) {
		logger.info("Creating User");

		if (userService.isUidNotValid(user)) {
			logger.error("Invalid UID, please a valid one");
			return "Invalid UID, please a valid one";
		}
		if (userService.isUserExist(user)) {
			logger.error("User with given UID already exists");
			return "User with given UID already exists";
		}
		user.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		user.setModifiedTimeStamp(new Timestamp(System.currentTimeMillis()));
		user.setDeleted(false);
		userService.saveUser(user);
		return "User Registered Successfully";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody UserLogin userLogin, UriComponentsBuilder uriComponentBuilder,
			HttpSession httpSession) {

		User user = new User();
		String passwordHash = this.generateHash(userLogin.getPassword());
		user = userService.findByUidAndPassword(userLogin.getUid(), passwordHash);
		if (user != null) {
			httpSession.setAttribute("ministryName", user.getMinistryName());
			httpSession.setAttribute("uid", user.getUid());
			return new ResponseEntity<>(user.getMinistryName(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid UID/Password", HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<String> logout(UriComponentsBuilder uriComponentBuilder, HttpSession httpSession) {

		Object session = httpSession.getAttribute("uid");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		httpSession.removeAttribute("uid");
		httpSession.removeAttribute("ministryName");
		return new ResponseEntity<String>("Logged out successfully", HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/user/details", method = RequestMethod.GET)
	public ResponseEntity<String> getUser(HttpSession httpSession, UriComponentsBuilder uriComponentBuilder) {

		Object session = httpSession.getAttribute("uid");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);
		String uid = session.toString();
		UserDetails userDetails = new UserDetails();
		ObjectMapper mapper = new ObjectMapper();
		if (!userService.isUidExist(uid)) {
			return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user = userService.findByUid(uid);
		userDetails.setMinistryName(user.getMinistryName());
		userDetails.setMinistryHead(user.getFirstName() + " " + user.getLastName());
		userDetails.setEmail(user.getEmail());
		userDetails.setUid(uid);
		try {
			String userData = mapper.writeValueAsString(userDetails);
			return new ResponseEntity<String>(userData, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/ministry/all", method = RequestMethod.GET)
	public ResponseEntity<String> getAllMinistry(UriComponentsBuilder uriComponentBuilder, HttpSession httpSession) {
		Object session = httpSession.getAttribute("uid");
		if (session == null)
			return new ResponseEntity<String>("Please login", HttpStatus.UNAUTHORIZED);

		List<User> allUsers = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<MinistryDetails> ministryList = new ArrayList<>();

		allUsers = userService.findAll();
		for (int i = 0; i < allUsers.size(); i++) {
			MinistryDetails ministryDetails = new MinistryDetails();
			ministryDetails.setMinistryName(allUsers.get(i).getMinistryName());
			ministryDetails.setMinistryHead(allUsers.get(i).getFirstName() + " " + allUsers.get(i).getLastName());
			ministryDetails.setEmail(allUsers.get(i).getEmail());
			ministryList.add(ministryDetails);
		}
		try {
			String ministries = "{ \"ministry\":" + mapper.writeValueAsString(ministryList) + "}";
			return new ResponseEntity<String>(ministries, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
		}

	}

	private String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			return "Internal Server Error";
		}

		return hash.toString();
	}

}
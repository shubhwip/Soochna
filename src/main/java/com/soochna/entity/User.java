/**
 * 
 */
package com.soochna.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ankit
 *
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@NotNull
	@Column(name = "uid")
	private String uid;

	@Column(name = "firstName")
	@NotEmpty(message = "First Name cannot be null")
	private String firstName;

	@Column(name = "lastName")
	@NotEmpty(message = "Last Name cannot be null")
	private String lastName;
	@Column(name = "email")
	@NotEmpty(message = "Email Name cannot be null")
	private String email;

	@Column(name = "password")
	@NotEmpty(message = "Password Name cannot be null")
	private String password;

	@Column(name = "ministryName")
	@NotEmpty(message = "Ministry Name cannot be null")
	private String ministryName;

	@Column(name = "createdTimeStamp")
	Timestamp createdTimeStamp;

	@Column(name = "modifiedTimeStamp")
	Timestamp modifiedTimeStamp;

	@Column(name = "isDeleted")
	boolean isDeleted;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String uid, String firstName, String lastName, String email, String password, String ministryName,
			Timestamp createdTimeStamp, Timestamp modifiedTimeStamp, boolean isDeleted) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.ministryName = ministryName;
		this.createdTimeStamp = createdTimeStamp;
		this.modifiedTimeStamp = modifiedTimeStamp;
		this.isDeleted = isDeleted;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = this.generateHash(password);
	}

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public Timestamp getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}

	public void setModifiedTimeStamp(Timestamp modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

package com.xxz.login.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_account", catalog = "panda")
public class PAccount implements java.io.Serializable {

	// Fields

	private String userid;
	private String username;
	private String account;
	private String password;
	private int gender;
	private int age;
	private Date registrationTime;
	private String moduleAuthorityGroup;
	private int administrator;
	private String phoneNumber;
	private String emailAddress;

	// Constructors

	/** default constructor */
	public PAccount() {
	}

	/** minimal constructor */
	public PAccount(String userid) {
		this.userid = userid;
	}

	/** full constructor */
	public PAccount(String userid, String username, String account, String password,
			int gender, int age, Date registrationTime,
			String moduleAuthorityGroup, int administrator, String phoneNumber,
			String emailAddress) {
		this.userid = userid;
		this.username = username;
		this.account = account;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.registrationTime = registrationTime;
		this.moduleAuthorityGroup = moduleAuthorityGroup;
		this.administrator = administrator;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	// Property accessors
	@Id
	@Column(name = "userid", unique = true, nullable = false)
	public String getuserId() {
		return this.userid;
	}

	public void setuserId(String userid) {
		this.userid = userid;
	}

	@Column(name = "username", length = 10)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "account", length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "gender")
	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "age")
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "registration_time", length = 10)
	public Date getRegistrationTime() {
		return this.registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Column(name = "module_authority_group", length = 4000)
	public String getModuleAuthorityGroup() {
		return this.moduleAuthorityGroup;
	}

	public void setModuleAuthorityGroup(String moduleAuthorityGroup) {
		this.moduleAuthorityGroup = moduleAuthorityGroup;
	}

	@Column(name = "administrator")
	public int getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "email_address", length = 50)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
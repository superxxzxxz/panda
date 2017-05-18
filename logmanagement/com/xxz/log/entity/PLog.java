package com.xxz.log.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_log")
public class PLog implements java.io.Serializable {

	// Fields

	private String id;
	private String userid;
	private String username;
	private Date operationTime;
	private String operationIp;

	// Constructors

	/** default constructor */
	public PLog() {
	}

	/** full constructor */
	public PLog(String id, String userid, String username,
			Date operationTime, String operationIp) {
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.operationTime = operationTime;
		this.operationIp = operationIp;
	}

	// Property accessors
	@Id
	@Column(name = "id", length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "userid", length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "username", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "operation_time", length = 10)
	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name = "operation_ip", length = 20)
	public String getOperationIp() {
		return this.operationIp;
	}

	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}

}
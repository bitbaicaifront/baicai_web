package com.baicai.domain;

import java.sql.Timestamp;

public class UserPO {
	private Long id;
	private String account;
	private String password;
	private Integer  login_time;
	private Timestamp last_login_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Integer login_time) {
		this.login_time = login_time;
	}
	public Timestamp getLast_login_date() {
		return last_login_date;
	}
	public void setLast_login_date(Timestamp last_login_date) {
		this.last_login_date = last_login_date;
	}

}
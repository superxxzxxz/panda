package com.xxz.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.xxz.common.tool.HibernateDaoImpl;
import com.xxz.login.entity.PAccount;

@Service("loginSystemService")
public class LoginSystemService {
	@Autowired
	@Qualifier("hibernateDaoImpl")
	private HibernateDaoImpl hibernateDaoImpl;

	public HibernateDaoImpl getHibernateDaoImpl() {
		return hibernateDaoImpl;
	}

	public void setHibernateDaoImpl(HibernateDaoImpl hibernateDaoImpl) {
		this.hibernateDaoImpl = hibernateDaoImpl;
	}
	public Object userLoginService(String account,String password){
		String hql="from PAccount where account=? and password=?";
		Object values[] ={account,password};
		return (PAccount)this.hibernateDaoImpl.getObject(hql, values);
	}
}

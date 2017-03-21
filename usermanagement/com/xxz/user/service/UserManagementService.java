package com.xxz.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xxz.common.tool.HibernateDaoImpl;
import com.xxz.login.entity.PAccount;

@Service("userManagementService")
public class UserManagementService {
	@Autowired
	@Qualifier("hibernateDaoImpl")
	private HibernateDaoImpl hibernateDaoImpl;

	public HibernateDaoImpl getHibernateDaoImpl() {
		return hibernateDaoImpl;
	}

	public void setHibernateDaoImpl(HibernateDaoImpl hibernateDaoImpl) {
		this.hibernateDaoImpl = hibernateDaoImpl;
	}
	//查询所有用户
	public Map getUserAll(int pageIndex,int pageSize){
		Map<String,Object> map=new HashMap<String,Object>();
		String hql="from PAccount where 1=1";
		String hqlSize="select count(id) from PAccount where 1=1";
		List<PAccount> list=this.hibernateDaoImpl.find(hql);
		Long pages=(Long) this.hibernateDaoImpl.getObject(hqlSize);
		map.put("list", list);
		map.put("count", pages);
		map.put("code", 0);
		map.put("msg", "获取成功");
		return map;
	}
	//根据ID获取用户对象
	public PAccount getUserId(String userid) throws Exception{
		return (PAccount) this.hibernateDaoImpl.get(PAccount.class,userid);
	}
	//保存用户
	public void saveUserAll(PAccount at){
		this.hibernateDaoImpl.save(at);
	}
	//编辑用户
	public void updateUserAll(PAccount at){
		this.hibernateDaoImpl.update(at);
	}
	//删除用户
	public void deleteUser(PAccount at){
		this.hibernateDaoImpl.delete(at);
	}
}

package com.xxz.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xxz.common.tool.HibernateDaoImpl;
import com.xxz.log.entity.PLog;
import com.xxz.login.entity.PAccount;

@Service("logService")
public class LogService {
	@Autowired
	@Qualifier("hibernateDaoImpl")
	private HibernateDaoImpl hibernateDaoImpl;

	public HibernateDaoImpl getHibernateDaoImpl() {
		return hibernateDaoImpl;
	}

	public void setHibernateDaoImpl(HibernateDaoImpl hibernateDaoImpl) {
		this.hibernateDaoImpl = hibernateDaoImpl;
	}
	/**
	 * 查询所有日志记录
	 * @param limit
	 * @param offset
	 * @return
	 */
	public Map getAllLog(int pageNumber,int pageSize){
		String hql="from PLog where 1=1";
		String hqlSize="select count(id) from PLog where 1=1";
		Map<String,Object> map=new HashMap<String,Object>();
		List<PLog> list=this.hibernateDaoImpl.find(hql,pageNumber,pageSize);
		Long pages=(Long) this.hibernateDaoImpl.getObject(hqlSize);
		map.put("rows", list);
		map.put("total", pages);
		//map.put("code", 0);
		//map.put("msg", "获取成功");
		return map;
	}
}

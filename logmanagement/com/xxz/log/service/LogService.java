package com.xxz.log.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xxz.common.tool.HibernateDaoImpl;
import com.xxz.common.tool.Pub;
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
	public Map getAllLog(HttpSession sessoin,HttpServletRequest request,int pageNumber, int pageSize,String sortName,String sortOrder,String userName,String startDate,String endDate) {
		//String str=URLDecoder.decode(userName, "UTF-8");
		String hql="from PLog where 1=1";
		String hqlSize="select count(id) from PLog where 1=1";
		if(!Pub.outofnull(userName).equals("")){
			hql+=" and username LIKE '%"+userName+"%'";
			hqlSize+=" and username LIKE '%"+userName+"%'";
		}
		if(!Pub.outofnull(startDate).equals("")&&!Pub.outofnull(endDate).equals("")){
			hql+=" and DATE_FORMAT(operationTime,'%Y-%m-%d') >= '"+startDate+"' and DATE_FORMAT(operationTime,'%Y-%m-%d') <= '"+endDate+"'";
			hqlSize+=" and DATE_FORMAT(operationTime,'%Y-%m-%d') >= '"+startDate+"' and DATE_FORMAT(operationTime,'%Y-%m-%d') <= '"+endDate+"'";
		}
		hql+=" order by "+sortName+" "+sortOrder;
		System.out.println("语句"+hql);
		Map<String,Object> map=new HashMap<String,Object>();
		List<PLog> list=this.hibernateDaoImpl.find(hql,pageNumber,pageSize);
		Long pages=(Long) this.hibernateDaoImpl.getObject(hqlSize);
		map.put("rows", list);
		map.put("total", pages);
		return map;
	}
	public PLog getPLogId(String id) throws Exception{
		return (PLog) this.hibernateDaoImpl.get(PLog.class,id);
	}
	public void savePLog(PLog plog){
		this.hibernateDaoImpl.save(plog);
	}
	public void deletePLog(List dlist){
		this.hibernateDaoImpl.deleteAll(dlist);
	}
}

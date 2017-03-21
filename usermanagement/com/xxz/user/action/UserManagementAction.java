package com.xxz.user.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxz.login.entity.PAccount;
import com.xxz.user.service.UserManagementService;
/**
 * 
 * @author XXZ
 *
 */
@Controller
@RequestMapping("/UserManagementAction")
public class UserManagementAction {
	@Autowired
	@Qualifier("userManagementService")
	private UserManagementService userManagementService;

	public UserManagementService getUserManagementService() {
		return userManagementService;
	}

	public void setUserManagementService(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
	//查询所有用户
	@RequestMapping(value="/userLoginAction.do", method = RequestMethod.GET)
	@ResponseBody 
	public Map getUserAll(int pageIndex,int pageSize){
		return this.userManagementService.getUserAll(pageIndex,pageSize);
	}
	//根据ID获取用户对象
	@RequestMapping(value="/getUserId.do", method = RequestMethod.POST)
	@ResponseBody 
	public PAccount getUserId(String userid) throws Exception{
		return this.userManagementService.getUserId(userid);
	}
	//保存用户操作
	@RequestMapping(value="/saveUserAll.do", method = RequestMethod.POST)
	@ResponseBody 
	public void saveUserAll(HttpServletRequest request,PrintWriter out){
		PAccount at=new PAccount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String username = request.getParameter("username");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String age = request.getParameter("age");
			String phoneNumber = request.getParameter("phoneNumber");
			String emailAddress = request.getParameter("emailAddress");
			String administrator = request.getParameter("administrator");
			String gender = request.getParameter("gender");
			at.setuserId("xxz"+String.valueOf(System.currentTimeMillis()));//用户ID时间戳
			at.setUsername(username);
			at.setAccount(account);
			at.setPassword(password);
			at.setAge(Integer.parseInt(age));
			at.setGender(Integer.parseInt(gender));
			at.setRegistrationTime(new Date());
			at.setModuleAuthorityGroup("**");
			at.setPhoneNumber(phoneNumber);
			at.setEmailAddress(emailAddress);
			at.setAdministrator(Integer.parseInt(administrator));
			this.userManagementService.saveUserAll(at);
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			out.print("error");
		}
	}
	//编辑用户操作
	@RequestMapping(value="/updateUserAll.do", method = RequestMethod.POST)
	@ResponseBody 
	public void updateUserAll(HttpServletRequest request,PrintWriter out){
		PAccount at=new PAccount();
	try{
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String phoneNumber = request.getParameter("phoneNumber");
		String emailAddress = request.getParameter("emailAddress");
		String administrator = request.getParameter("administrator");
		String gender = request.getParameter("gender");
		at=this.userManagementService.getUserId(userid);
		if(at!=null){
			at.setUsername(username);
			at.setAccount(account);
			at.setPassword(password);
			at.setAge(Integer.parseInt(age));
			at.setGender(Integer.parseInt(gender));
			at.setPhoneNumber(phoneNumber);
			at.setEmailAddress(emailAddress);
			at.setAdministrator(Integer.parseInt(administrator));
			this.userManagementService.updateUserAll(at);
			out.print("success");
		}
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		out.print("error");
	}
	}
	//删除用户操作
	@RequestMapping(value="/deleteUser.do", method = RequestMethod.POST)
	@ResponseBody 
	public void deleteUser(HttpServletRequest request,PrintWriter out){
		PAccount at=new PAccount();
	try{
		String userid = request.getParameter("userid");
		at=this.userManagementService.getUserId(userid);
		if(at!=null){
			this.userManagementService.deleteUser(at);
			out.print("success");
		}
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		out.print("error");
	}
	}
}

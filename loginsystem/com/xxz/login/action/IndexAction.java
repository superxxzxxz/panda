package com.xxz.login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxz.common.tool.Pub;
@Controller
public class IndexAction {
	@RequestMapping("/index.do")
	public String urlIndex(HttpServletRequest request, HttpSession session){
		if(session.getAttribute("user")==null){
			return "../../login";
		}else{
			return "../../index";
		}
	}
	
	@RequestMapping("/login.do")
	public String urlLogin(HttpServletRequest request){
		Cookie[] cookies=request.getCookies(); 
		if(cookies!=null){ 
			for(int i=0;i<cookies.length;i++){ 
				if(cookies[i].getName().equals("logineduser")){
					String[] ac =cookies[i].getValue().split("&&");
					String u ="";
					String p = "";
					try{
						u=Pub.outofnull(ac[0]);
						p=Pub.outofnull(ac[1]);
					}catch (Exception e){}
					request.setAttribute("loginedusername",u); 
					request.setAttribute("logineduserpassword",p); 
				}
			}
		}
		return "../../login";
	}
	@RequestMapping("/secureaccessurl.do")
	public String secureAccessURL(HttpServletRequest request){
		String path = request.getParameter("path");
		path =path.replace(".jsp","");
		return path;
	}
}

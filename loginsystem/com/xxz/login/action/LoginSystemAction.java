package com.xxz.login.action;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxz.common.tool.Pub;
import com.xxz.common.tool.ValidateCode;
import com.xxz.login.entity.PAccount;
import com.xxz.login.service.LoginSystemService;
/**
 * 
 * @author xxz
 *登录控制器
 */
@Controller
public class LoginSystemAction {
	@Autowired
	@Qualifier("loginSystemService")
	private LoginSystemService loginSystemService;
	public LoginSystemService getLoginSystemService() {
		return loginSystemService;
	}
	public void setLoginSystemService(LoginSystemService loginSystemService) {
		this.loginSystemService = loginSystemService;
	}
	private ValidateCode vc=new ValidateCode();
	/**
	 * 获取验证码
	 */
	@RequestMapping("/validateCode.do")
	public void validateCode(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		int w =160, h = 60;//设置图片宽/高
		try {
		String verification_Code=vc.generateVerifyCode(4);//验证码位数
		session.setAttribute("vcode",verification_Code);//存入session，登录时匹配
		OutputStream os = response.getOutputStream();//创建输出流
		vc.outputImage(w, h, os, verification_Code);//输出显示
		os.flush();
		os.close();
		os = null;
		response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 系统登录
	 */
	@RequestMapping("/userLoginAction.do")
	public void userLoginAction(HttpServletRequest request,HttpServletResponse response,HttpSession session,String account,String password,String savepwd,String verification_code){
		PAccount paccount=(PAccount) this.loginSystemService.userLoginService(account, password);
		try {
			String sendRedirectUrl="login.do";//登录后要重定向的URL
			String sv_code=(String) session.getAttribute("vcode");//获取session中的验证码匹配
			if(paccount!=null&&Pub.outofnull(sv_code).equalsIgnoreCase(Pub.outofnull(verification_code))){//用户不为空并且验证码相等（验证码不考虑大小写）
				session.setAttribute("user",paccount);
				Cookie[] cookies=request.getCookies(); 
				boolean isc=true;
				if(!Pub.outofnull(savepwd).equals("")){//判断是否保存密码
					if(cookies!=null){ 
						for(int i=0;i<cookies.length;i++){ 
							if(cookies[i].getName().equals("logineduser")){  
								cookies[i].setValue(paccount.getAccount()+"&&"+paccount.getPassword());
								response.addCookie(cookies[i]);
								isc=false;
							}
						}
						if(isc){
							Cookie userck = null;
							userck = new Cookie("logineduser", paccount.getAccount()+"&&"+paccount.getPassword()); 
							userck.setMaxAge(60 * 60 * 24 * 30);   //设置Cookie有效期为30天
							response.addCookie(userck);
						}
					}
				}else{
					Cookie userck = new Cookie("logineduser", null);
					userck.setMaxAge(0);   //删除Cookie
					response.addCookie(userck);
				}
			}else if(paccount==null){
				request.setAttribute("errors", "登陆失败,请输入正确的账号或密码！");
				sendRedirectUrl = "login.do";
				RequestDispatcher dispatcher = request.getRequestDispatcher(sendRedirectUrl);
				dispatcher.forward(request, response);
				return ;
			}else if(!Pub.outofnull(sv_code).equals(verification_code)){
				request.setAttribute("errors", "登陆失败,请输入正确的验证码！");
				sendRedirectUrl = "login.do";
				RequestDispatcher dispatcher = request.getRequestDispatcher(sendRedirectUrl);
				dispatcher.forward(request, response);
				return ;
			}
			response.sendRedirect("index.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用户注销
	 * @param session
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/userLogout.do")
	public void userLogout(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		session.invalidate();//销毁当前session
		System.out.println("ssssssssss");
		response.sendRedirect("login.do");
		
	}
}

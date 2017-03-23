package com.xxz.common.tool;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author 
 * @fileName LoginFilter.java
 *
 */
public class LoginFilter implements Filter{
	private String notFilterDir = "";
	protected Log log = LogFactory.getLog(this.getClass());
	private FilterConfig filterConfig;

	public void destroy() {
		notFilterDir = null;
	}
	/**
	 * LoginFilter登录验证
	 * 1获取不验证范围<param-name>notFilterDir</param-name>正常访问
	 * 2其他如果session中无用户表示没登录，跳转到登录页面
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String uri = req.getRequestURI();
			String[] notFilterDirs = notFilterDir.split(",");
			for (int i = 0; i < notFilterDirs.length; i++) {
				String notFilterDirValue = notFilterDirs[i];
				if (uri.indexOf(notFilterDirValue) != -1){//如果地址url包含（ <param-name>notFilterDir</param-name>）中
					chain.doFilter(request, response);
					return;
				}
			}
			String loginname = Pub.outofnull(request.getParameter("login_name"));
			if(!loginname.equals("")){
				chain.doFilter(request, response);
				return;
			}
			loginname = Pub.outofnull(request.getParameter("open_id"));
			if(!loginname.equals("")){
				chain.doFilter(request, response);
				return;
			}
			HttpSession session = req.getSession();
			if (session.getAttribute("user")==null){
				res.sendRedirect(this.filterConfig.getServletContext().getContextPath() + "/login.do");
			}else{
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		notFilterDir = filterConfig.getInitParameter("notFilterDir");
	}
}

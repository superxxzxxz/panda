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

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String uri = req.getRequestURI();
			if (uri.indexOf(".htm") != -1||uri.replace(req.getContextPath(), "").equals("/")){//内网网站
				chain.doFilter(request, response);
				return;
			}
			if(uri.indexOf("taskFileUpload.do")!=-1||uri.indexOf("uploadassetsfile.do")!=-1||
					uri.indexOf("getOrgCodeByIp.do")!=-1||uri.indexOf("getloginimg.do")!=-1){
				chain.doFilter(request, response);
				return;
			}
			String[] notFilterDirs = notFilterDir.split(",");
			for (int i = 0; i < notFilterDirs.length; i++) {
				String notFilterDirValue = notFilterDirs[i];
				if (uri.indexOf(notFilterDirValue) != -1){
					 if(notFilterDirValue.equals("/CallbackServlet/")){
						 //CallbackServlet.frompostbean= Util.getFromPostBean(request.getInputStream());
					 }
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
				res.sendRedirect(this.filterConfig.getServletContext().getContextPath() + "/login.jsp");
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

package com.xxz.common.tool;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
public class Utils {
	/**
	 * 获取客户端IP，反代理
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		     String ipAddress = null;   
		     ipAddress = request.getHeader("x-forwarded-for");   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
		      ipAddress = request.getHeader("Proxy-Client-IP");   
		     }   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
		         ipAddress = request.getHeader("WL-Proxy-Client-IP");   
		     }   
		     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
		      ipAddress = request.getRemoteAddr();   
		      if(ipAddress.equals("127.0.0.1")){   
		       //根据网卡取本机配置的IP   
			    InetAddress inet=null;   
			    try {   
			     inet = InetAddress.getLocalHost();   
			    } catch (UnknownHostException e) {   
			     e.printStackTrace();   
			    }   
			    ipAddress= inet.getHostAddress();   
		      }   
		            
		     }   
		     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
		     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
		         if(ipAddress.indexOf(",")>0){   
		             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
		         }   
		     }   
		     return ipAddress;    
    }
	/**
	 * 获取现在时间，根据参数返回是否带时分秒的现在时间
	 * @param Contain 
	 * @return
	 * @throws ParseException 
	 */
	public static Date getNowDate(String contain) throws ParseException {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter=null;
		   if(Pub.outofnull(contain).equals("")||Pub.outofnull(contain).equals("n")){
			    formatter = new SimpleDateFormat("yyyy-MM-dd");
		   }else if(Pub.outofnull(contain).equals("y")){
			    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   }
		   String dateString = formatter.format(currentTime);
		   Date currentTime_2 = formatter.parse(dateString);
		   return currentTime_2;
	}
}

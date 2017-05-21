package com.xxz.common.tool;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
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
		 if (request.getHeader("x-forwarded-for") == null) {
			   return request.getRemoteAddr();
			  }
			  return request.getHeader("x-forwarded-for");
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

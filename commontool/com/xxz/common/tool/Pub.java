package com.xxz.common.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author wzg
 * @fileName Pub.java
 *
 */
public class Pub {
	
    static public String outofnull(String str) {
		if (str == null) {
			str = "";
		} else if (str.toLowerCase().equals("null")) {
			str = "";
		}
		return str.trim();
	}
	
	// wzg 优化于 2013 1 21
	public String outnull(String str) {
		if (str == null) {
			str = "";
		} else if (str.toLowerCase().equals("null")) {
			str = "";
		}
		return str.trim();
	}
	
	static public String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	 public static String HanyuToPinyin(String name) {//把中文转换成拼音全写
			String pinyin = "";
			char[] nameChar = name.toCharArray();
			HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			for (int i = 0; i < nameChar.length; i++) {
				if (nameChar[i] > 128) {
					try {
						pinyin += PinyinHelper.toHanyuPinyinStringArray(
								nameChar[i], defaultFormat)[0]
								+ " ";
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				}
			}
			return pinyin;
		}
	
    /**   
     * 汉字转换位汉语拼音首字母，英文字符不变   
     * @param chines 汉字   
     * @return 拼音   
     */     
    public static String converterToFirstSpell(String chines){             
         String pinyinName = "";      
        char[] nameChar = chines.toCharArray();      
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();      
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);      
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);      
        for (int i = 0; i < nameChar.length; i++) {      
            if (nameChar[i] > 128) {      
                try {      
                     pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);      
                 } catch (BadHanyuPinyinOutputFormatCombination e) {      
                     e.printStackTrace();      
                 }      
             }else{      
                 pinyinName += nameChar[i];      
             }      
         }      
        return pinyinName.toUpperCase();      
     } 
    
	 public static String getCookiescss(HttpServletRequest request){
		 String css ="";
		 Cookie[] cookies=request.getCookies(); 
		 if(cookies!=null){for(int i=0;i<cookies.length;i++){if(cookies[i].getName().equals("style")){request.setAttribute("styleName", cookies[i].getValue());}}}    
		 String style = (String)request.getAttribute("styleName");
		 if(style==null || style.equals("gray")){css = "gray";}else if(style.equals("white")){css="metro";
		 }else if(style.equals("black")){css="black";}else if(style.equals("gwhite")){css="bootstrap";
		 }else{css="default";}
		 return css;
	 }
	 
	   
		static public  boolean Del(String str){
			 if(str==null||str.equals("")) {
				 
		     }
			 String inj_str = "'/and/exec/insert/select/delete/update/count/*/%/chr/mid/master/truncate/char/declare/or/drop";
			 String inj_stra[] = inj_str.split("/");
				 for (String ob:inj_stra ){
					 if (str.indexOf(ob)!=-1) {
						 return true; 
					 }
				 }
		     return false;
		 }
		
		/**
		 * 把double 处理后面.0
		 * @param num
		 * @return
		 */
		public static String rep(double num){
			String s="";
			String str=String.valueOf(num);
			String length=str.substring(str.indexOf(".")+1,str.length());
			if(length.length()>3){
				s=str.substring(0,str.indexOf(".")+3);
			}else if(str.contains(".0")){
				s=str.substring(0,str.indexOf(".0"));
			}else{
				s=str;    
			}
			return s;
		}
		/**
		 * d1 0 判定为日期 1 double 2 字符串
		 * @param temp
		 * @return
		 */
		public static String isDoubleorDateorString(String temp){
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String d1="";
			Date d;
			double b =0.0;
			try {
				format.setLenient(true);
		        format.parse(temp);
				d1="0";
			} catch (ParseException e) {
				d1="1";
			}
			if("1".equals(d1)){
				try {
					format1.setLenient(true);
			        format1.parse(temp);
					d1="0";
				} catch (ParseException e) {
					d1="1";
				}
			}
			if("1".equals(d1)){
				try{
					b= Double.parseDouble(temp);
					if(b>100){
						d1="2";
					}
				}catch (Exception e) {
					d1="2";
				}
			}
			return d1;
		}
		public static List<String> parseJsonToArray(String data) {//it 资产 /delregeditasset.do 中用到
	        List<String> list = new ArrayList<String>();
	        if (null == data || data.length() == 0) {
	            return null;
	        }
	        if (data.length() < 4) {
	            return null;
	        }
	        String temp = data.substring(2, data.length() - 2);
	        temp = temp.replaceAll("\\'", "");
	        String splitResult[] = temp.split(",");
	        for (int i = 0; i < splitResult.length; i++) {
	            list.add(splitResult[i].substring(splitResult[i].indexOf(":") + 1));
	        }
	        return list;
	    }
		
		/**
		 * 
		 * @param c 字符串
		 * @param c1 包含字符串
		 * @return
		 */
		public static int parseChar(String c,String c1) {
			return c.split(c1).length;
		}
		
		
		public static String getAppStatus(HttpSession session){
			String appStatus="";
			String filefolderpath = session.getServletContext().getRealPath("");
			filefolderpath+=File.separator+"upload"+File.separator+"conf.txt";
			File file = new File(filefolderpath);
			BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            while ((tempString = reader.readLine()) != null) {
	            	if(tempString.indexOf("#")==-1){
	            		appStatus=tempString;
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try { reader.close();}catch (IOException e1){}
	            }
	        }
			return appStatus;
		}
		
		/** 
	     * 检测是否是移动设备访问 
	     *  
	    * @Title: check 
	     * @Date : 2014-7-7 下午01:29:07 
	     * @param userAgent 浏览器标识 
	     * @return true:移动设备接入，false:pc端接入 
	     */  
	    public static boolean check(String userAgent){    
		     String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"    
		            +"|windows (phone|ce)|blackberry"    
		            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"    
		            +"|laystation portable)|nokia|fennec|htc[-_]"    
		            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
		     String tableReg = "\\b(ipad|(Nexus 7)|up.browser"    
		            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";  
		  //移动设备正则匹配：手机端、平板  
		         Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);    
		         Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE); 
	    	
	    	if(null == userAgent){    
	            userAgent = "";    
	        }    
	        // 匹配    
	        Matcher matcherPhone = phonePat.matcher(userAgent);    
	        Matcher matcherTable = tablePat.matcher(userAgent);    
	        if(matcherPhone.find() || matcherTable.find()){    
	            return true;    
	        } else {    
	            return false;    
	        }    
	    } 
	    
	    
	    /**
		 * 获取客户端ip方法
		 */
		public static String getIpAddr(HttpServletRequest request) {   
		     String ipAddress = null;   
		     //ipAddress = request.getRemoteAddr();   
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
	    
		public static String ReadFile(String Path){
			BufferedReader reader = null;
			String laststr = "";
			try{
				FileInputStream fileInputStream = new FileInputStream(Path);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
				reader = new BufferedReader(inputStreamReader);
				String tempString = null;
				while((tempString = reader.readLine()) != null){
					laststr += tempString;
				}
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if(reader != null){
					try {
						reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			return laststr;
		}
		
		/*
	    public static void main(String[] args) { 
	        int n = 6;
	        for(int i = 0; i < n - 1; i++) 
	        { 
	            for(int x = i + 1; x < n; x++) 
	            { 
	                System.out.print(" "); 
	            } 
	            for(int y = 0; y < (i + 1) * 2 - 1; y++) 
	            { 
	                System.out.print("*"); 
	            } 
	            System.out.println(); 
	        } 
	        for(int i = 0; i < n; i++) 
	        { 
	            for(int x = 0; x < i; x++) 
	            { 
	                System.out.print(" "); 
	            } 
	            for(int y = i; y < 2 * n - i - 1; y++) 
	            { 
	                System.out.print("*"); 
	            } 
	            System.out.println(); 
	        } 
	 
	 
	    } 
		public static void main(String[] args) {
			 
	        int i = 0;
	 
	        int k = 0;
	 
	        for (i = 0; i <= 3; i++) {
	 
	            for (k = 0; k <= i; k++) {
	                // 对角线1,2,3,4
	                if (i == k) {
	                    System.out.print((i+1) + "    ");
	                }
	                // 5,6,7
	                if (i == k + 1) {
	                    System.out.print(i + 4 + "    ");
	                }
	                // 8,9
	                if (i == k + 2) {
	                    System.out.print(i + 6 + "    ");
	                }
	                // 10
	                if (i == k + 3) {
	                    System.out.print(i + 7 + "   ");
	                }
	 
	            }
	            System.out.print("\r");
	 
	        }
	    }*/

}

package com.xxz.log.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxz.log.service.LogService;

@Controller
@RequestMapping("/LogAction")
public class LogAction {
	@Autowired
	@Qualifier("logService")
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	/**
	 * 查询所有日志记录
	 * @param pageNumber 页码
	 * @param pageSize 每一页显示的条数
	 * @param sortName 要排序的列名
	 * @param sortOrder 定义排序方式 'asc' 或者 'desc'
	 * @return
	 */
	@RequestMapping(value="/getAllLog.do")
	@ResponseBody 
	public Map getAllLog(HttpSession sessoin,HttpServletRequest request, int pageNumber, int pageSize,String sortName,String sortOrder,String userName,String startDate,String endDate){
		//String userName=URLDecoder.decode(request.getParameter("userName"),"GBK");
		return this.logService.getAllLog(sessoin,request,pageNumber,pageSize,sortName,sortOrder,userName,startDate,endDate);
	}
}

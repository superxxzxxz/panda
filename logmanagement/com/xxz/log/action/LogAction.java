package com.xxz.log.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxz.log.service.LogService;
import com.xxz.user.service.UserManagementService;

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
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value="/getAllLog.do", method = RequestMethod.GET)
	@ResponseBody 
	public Map getAllLog(int pageNumber, int pageSize){
		return this.logService.getAllLog(pageNumber,pageSize);
	}
}

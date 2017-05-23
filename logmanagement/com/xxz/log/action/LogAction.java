package com.xxz.log.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxz.common.tool.Pub;
import com.xxz.log.entity.PLog;
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
	@RequestMapping(value="/getAllLog.do",method = RequestMethod.GET)
	@ResponseBody 
	public Map getAllLog(HttpSession sessoin,HttpServletRequest request, int pageNumber, int pageSize,String sortName,String sortOrder,String userName,String startDate,String endDate){
		return this.logService.getAllLog(sessoin,request,pageNumber,pageSize,sortName,sortOrder,userName,startDate,endDate);
	}
	/**
	 * 删除选中日志信息
	 * @param out
	 * @param logid
	 */
	@RequestMapping(value="/deleteAllLog.do",method = RequestMethod.POST)
	@ResponseBody
	public void deleteLog(PrintWriter out,String logid){
		List<PLog> deleteList=new ArrayList<PLog>();
		PLog plog=new PLog();
		try{
			if(Pub.outofnull(logid).indexOf(",")!=-1){//多条
				String[] logids=logid.split(",");
				for(int i=0;i<logids.length;i++){
				    plog=this.logService.getPLogId(logids[i]);
					deleteList.add(plog);
				}
			}else{
				    plog=this.logService.getPLogId(logid);
				    deleteList.add(plog);
			}
			this.logService.deletePLog(deleteList);
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			out.print("error");
		}
	}
}

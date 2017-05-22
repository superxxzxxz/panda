<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>日志管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- js start 使用require.js来管理js加载 -->
	<script src="resources/js/require.js"></script>
	<script src="resources/js/main.js"></script>
	<!--<script src="resources/layer/layer.js"></script>
	<link rel="stylesheet" href="resources/layer/mobile/need/layer.css" type="text/css"></link> -->
  </head>
  <script>
  require(['bootstraptablezh','datetimepickerzh','layui'], function() {  //加载模块
	  		//var hei=document.body.clientHeight;
	  		//var wid=document.body.clientWidth-100;
			$('#logtable').bootstrapTable({
			        url: 'LogAction/getAllLog.do',      //请求后台的URL（*）
		            method: 'get',                      //请求方式（*）
		            //toolbar: '#logtoolbar',             //工具按钮用哪个容器
		            striped: true,                      //是否显示行间隔色
		            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		            pagination: true,                   //是否显示分页（*）
		            sortable: true,                     //是否启用排序
		            sortOrder: "desc",     				//排序方式
		            sortName: "operationTime",			//排序列名称
		            width:'',     
		            queryParams:log.queryParams, 		//查询参数
		            queryParamsType:'',                 //默认为limit， 在默认情况下，传给服务器的参数为；offset，limit， 设置为''|undefined则传给服务器的参数为pageSize，pageNumber
		            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		            pageNumber:1,                       //初始化加载第一页，默认第一页
		            pageSize:10,                         //每页的记录行数（*）
		            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
		            //search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		            strictSearch: true,
		            //showColumns: true,                //是否显示所有的列
		            //showToggle:true,                  //是否显示详细视图和列表视图的切换按钮
		            //showRefresh: true,                //是否显示刷新按钮
		            minimumCountColumns: 0,             //最少允许的列数
		            clickToSelect: true,                //是否启用点击选中行
		            singleSelect: false,                //复选框只能选择一条记录
		            height:document.body.clientHeight-80,                         //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
		            cardView: false,                    //是否显示详细视图
		            detailView: false,                  //是否显示父子表
		            idField : "id",						//主键ID
		            undefinedText : "无数据",           //当数据为 undefined 时显示的字符
		            columns: [{
		                checkbox: true,
		            }, {
		                field: 'username',
		                title: '用户姓名',
		                align : 'center',
            			valign : 'middle'
		            }, {
		                field: 'operationIp',
		                title: 'IP地址',
		                align : 'center',
            			valign : 'middle'
		            }, {
		                field: 'operationTime',
		                title: '操作时间',
		                align : 'center',
            			valign : 'middle',
            			sortable : true,
            			formatter: function (value, row, index) {
                    		return format(value);   
             			}
		            }, {
		                field: 'operationTerm',
		                title: '操作项',
		                align : 'center',
            			valign : 'middle',
		                formatter: function (value, row, index) {
                    		if (value=="1"){
                    			return '<i class="glyphicon glyphicon-user"></i> 登录'; 
                    		}else{
                    			return '<i class="glyphicon glyphicon-off"></i> 注销';
                    		}
             			}
		            }, ]
			});
			//日期搜索框
			$('#startDate').datetimepicker({
        		  language: 'zh-CN',//显示中文
				  format: 'yyyy-mm-dd',//显示格式
				  minView: "month",//设置只显示到月份
				  initialDate: new Date(),//初始化当前日期
				  autoclose: true,//选中自动关闭
				  todayBtn: true//显示今日按钮
		    });
			$('#endDate').datetimepicker({
		   		  language: 'zh-CN',//显示中文
				  format: 'yyyy-mm-dd',//显示格式
				  minView: "month",//设置只显示到月份
				  initialDate: new Date(),//初始化当前日期
				  autoclose: true,//选中自动关闭
				  todayBtn: true//显示今日按钮
			});
			//时间戳转日期格式yyyy-MM-dd HH:mm:ss
			function add0(m){return m<10?'0'+m:m }
			function format(shijianchuo)
			{
			//shijianchuo是整数，否则要parseInt转换
			var time = new Date(shijianchuo);
			var y = time.getFullYear();
			var m = time.getMonth()+1;
			var d = time.getDate();
			var h = time.getHours();
			var mm = time.getMinutes();
			var s = time.getSeconds();
			return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
			}
	});
	log={
		queryParams:function(params){
		  var userName=$("#userName").val();
		  var startDate=$("#startDate input").val();
	      var endDate=$("#endDate input").val();
		  var temp = { //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
		  pageSize: params.pageSize, //页面大小
		  pageNumber: params.pageNumber, //页码
		  //minSize: $("#leftLabel").val(),
		  //maxSize: $("#rightLabel").val(),
		  //minPrice: $("#priceleftLabel").val(),
		  //maxPrice: $("#pricerightLabel").val(),
		  //Cut: Cut,
		  //Color: Color,
		  //Clarity: Clarity,
		  sortName: params.sortName, //排序列名
		  sortOrder: params.sortOrder,//排位命令（desc，asc）
		  userName:userName,
		  startDate:startDate,
		  endDate:endDate
		  };
		  return temp;
		},
		
		//搜索
		logsearch:function(){
			var userName=$("#userName").val();
			var startDate=$("#startDate input").val();
			var endDate=$("#endDate input").val();
			$("#logtable").bootstrapTable('refresh', {url:'LogAction/getAllLog.do',query: {userName:userName,startDate:startDate,endDate:endDate}});
		},
		//删除已选择日志
		
		deleteLog:function(){
			/*layui.config({
				base: 'resources'
			}).use('layer', function(){//lay/modules/layer','layui/css/modules/layer/default/layer.css*/
			var logrow=$('#logtable').bootstrapTable('getAllSelections');
			if(logrow.length>=1){
					layer.confirm('确定删除已选中的<span style="color:red;">&nbsp;"'+logrow.length+'"&nbsp;</span>条日志？', {
						  btn: ['确认删除','取消'] //按钮
						}, function(index){
							//删除操作
							/*$.ajax({
							  type: 'POST',
							  url: 'UserManagementAction/deleteUser.do',
							  data: {userid:dataValues.userId},
							  dataType: 'text',
							  async:false,
							  success:function(delete_data){
								if(delete_data=="success"){
									window.location.reload();//tab刷新
									layer.close(index);//关闭询问框
									layer.msg('成功删除！', {icon:1});
								}else{
									layer.close(index);//关闭询问框
									layer.msg('删除失败，请联系管理员。', {icon:5});
								}
							  }
							});*/
						}, function(){
							//取消删除
						});
			
			}else{
				layer.msg('请选择一条或多条日志信息', {icon:5});
				layer.msg("sss");
				//alert();
			}
			//});
		}
		
	}
  </script>
  <body style="overflow:hidden;">
	<!-- 工具栏 -->
	<div id="callout-glyphicons-location" class="bs-callout bs-callout-info">
		<div style="float: left;margin-right: 20px;"><button id="remove" class="btn btn-danger" onclick="log.deleteLog()"><i class="glyphicon glyphicon-remove"></i> 删除已选</button></div>
		<div class="input-group" style="width:200px;float: left;margin-right:10px" >
		  <span class="input-group-addon" id="basic-username">姓名</span>
		  <input id="userName" type="text" class="form-control" placeholder="用户姓名(模糊)" aria-describedby="basic-username">
		</div>
            <div class="form-group" style="width:200px;float:left;margin-right:10px;">  
                <!--指定 date标记-->  
                <div class='input-group date' id='startDate'>  
                    <input type='text' class="form-control" placeholder="开始时间"/>  
                    <span class="input-group-addon">  
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>  
            </div> 
            <div class="form-group" style="width:200px;float:left;">  
                <!--指定 date标记-->  
                <div class='input-group date' id='endDate'>  
                    <input type='text' class="form-control" placeholder="结束时间"/>  
                    <span class="input-group-addon">  
                        <span class="glyphicon glyphicon-calendar"></span>  
                    </span>  
                </div>  
        	</div>  
        	<div style="margin-left:10px; float:left;"><button type="button" class="btn btn-primary" onclick="log.logsearch()"><i class="glyphicon glyphicon-search"></i> 查询</button></div>
    </div>  
	<div style="width:98%;margin: 15px auto;"><table id="logtable"></table></div>
  </body>
</html>

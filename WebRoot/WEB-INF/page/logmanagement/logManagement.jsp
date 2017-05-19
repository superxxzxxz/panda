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
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- js start 使用require.js来管理js加载 -->
	<script src="resources/js/require.js"></script>
	<script src="resources/js/main.js"></script>
  </head>
  <script>
  require(['jquery','bootstrap','layui','bootstrapTable'], function($,b,l,bt) {  //加载模块
			$('#logtable').bootstrapTable({
			        url: 'LogAction/getAllLog.do',         //请求后台的URL（*）
		            method: 'get',                      //请求方式（*）
		            toolbar: '#logtoolbar',             //工具按钮用哪个容器
		            striped: true,                      //是否显示行间隔色
		            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		            pagination: true,                   //是否显示分页（*）
		            sortable: true,                    //是否启用排序
		            sortOrder: "asc",     				//排序方式
		            width:800,     
		            queryParams:queryParams, 
		            queryParamsType:'',                //默认为limit， 在默认情况下，传给服务器的参数为；offset，limit， 设置为''|undefined则传给服务器的参数为pageSize，pageNumber
		            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		            pageNumber:1,                       //初始化加载第一页，默认第一页
		            pageSize:1,                       //每页的记录行数（*）
		            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
		            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		            strictSearch: true,
		            showColumns: true,                  //是否显示所有的列
		            showRefresh: true,                  //是否显示刷新按钮
		            minimumCountColumns: 2,             //最少允许的列数
		            clickToSelect: true,                //是否启用点击选中行
		            singleSelect: false,                //复选框只能选择一条记录
		            height:600,                         //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
		            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
		            cardView: false,                    //是否显示详细视图
		            detailView: false,                  //是否显示父子表
		            idField : "id",
		            columns: [{
		                checkbox: true
		            }, {
		                field: 'username',
		                title: '用户姓名'
		            }, {
		                field: 'operationIp',
		                title: 'IP地址'
		            }, {
		                field: 'Level',
		                title: '部门级别'
		            }, {
		                field: 'operationTime',
		                title: '操作时间'
		            }, ]
			});
	});
  function queryParams(params) { //配置参数
	  var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	  pageSize: params.pageSize, //页面大小
	  pageNumber: params.pageNumber, //页码
	  //minSize: $("#leftLabel").val(),
	  //maxSize: $("#rightLabel").val(),
	  //minPrice: $("#priceleftLabel").val(),
	  //maxPrice: $("#pricerightLabel").val(),
	  //Cut: Cut,
	  //Color: Color,
	  //Clarity: Clarity,
	  //sort: params.sort, //排序列名
	  //sortOrder: params.order//排位命令（desc，asc）
	  };
	  return temp;
	  } 
  </script>
  <body>
  	<!-- <div style="margin:6px 0px 0px 15px; background-color: white;width: 98%;">
			<blockquote class="layui-elem-quote" style="border-left: 5px solid #0078AD;">
				 <button type="button" class="btn btn-danger">删除</button>
			</blockquote>
	</div> -->
	<div id="logtoolbar" style="padding: 0px 20px;">
		<button id="remove" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i>删除</button>
	</div>
	<table id="logtable"></table>
  </body>
</html>

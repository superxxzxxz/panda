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
	<script src="resources/js/require.js" data-main="resources/js/main" defer async="true" ></script>
	<!-- js end -->
	<script>
		$(function(){
			alert();
		});
		function click(){
			alert();
		}
	</script>
  </head>
  <body>
     日志管理<br>
     <input type="text" id="">
     <button type="button" onclick="click()">哈哈</button>
  </body>
</html>

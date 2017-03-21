<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录-PanDa服务平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   </head>
    <link rel="shortcut icon" href="resources/images/title-panda.icon">
  	<link rel="stylesheet" href="resources/css/login.css" />
	<link rel="stylesheet" href="resources/layer/skin/default/layer.css" />
	<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/layer/layer.js" ></script>
	<script type="text/javascript" src="resources/js/login.js" ></script>
	<script type="text/javascript">
    	$(function(){
    			var errorsstr = '${empty requestScope.errors?"":requestScope.errors }';
    			if(errorsstr != ""){
    				layer.msg(errorsstr,{time: 2000});
    			}
    			document.getElementById("account").focus();
    	})
    	function login_onsubmit(){//提交方法
    		var check_account=$("#account").val();
    		var check_password=$("#password").val();
    		var return_check=checkaccount(check_account,check_password);
    		if(return_check!="success"){
    			layer.msg(return_check,{time: 800});
    			return false;
    		}
    		return true;
    	}
    </script>
  <body style="background-color: whitesmoke">
		<div class="login-title"><img src="resources/images/login-title.png"></img></div>
		<div class="login-window">
			<div class="login-window-input">
				<form action="userLoginAction.do" onsubmit="return login_onsubmit()" method="post">
					<img style="vertical-align: middle;" src="resources/images/login-account.png" />&nbsp;<span class="login-account-span">账号：</span><input placeholder="请输入账号" id="account" class="login-account" name="account" type="text" value="${loginedusername}"/><br/>
					<img style="vertical-align: middle;" src="resources/images/login-password.png" />&nbsp;<span class="login-password-span">密码：</span><input placeholder="请输入密码" id="password" class="login-password" name="password" type="text" value="${logineduserpassword}" /><br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="login-btn" class="login-btn" type="submit" name="login-btn" value="登    录" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label for="save-pwd-checkbox"><input class="save-pwd-checkbox" id="save-pwd-checkbox" name="savepwd" type="checkbox" value="true" <%=request.getAttribute("logineduserpassword")!=null?"checked='checked'":"" %>/><span class="save-pwd">&nbsp;&nbsp;保存密码</span></label>
				</form>
			</div>
		</div>
		<div class="login-bottom">
			<span>©2017 Technical support XXZ</span>
		</div>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录-熊猫服务平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   </head>
    <link rel="shortcut icon" href="resources/images/title-panda.icon">
  	<link rel="stylesheet" href="resources/css/login.css" />
  	<link rel="stylesheet" href="resources/layui/css/layui.css" />
  	
	<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="resources/js/login.js" ></script>
    <script type="text/javascript" src="resources/layui/layui.js"></script>
	
	<script type="text/javascript">
    	layui.use(['layer', 'form'], function(){
  				var layer = layui.layer;
    			var errorsstr = '${empty requestScope.errors?"":requestScope.errors }';
    			if(errorsstr != ""){
    				layer.msg(errorsstr,{time: 2000});
    			}
    			document.getElementById("account").focus();
    			//if(document.getElementById("account").value!=""){document.getElementById("verification_code").focus();}else{document.getElementById("account").focus();}
    			document.getElementById("verification_img").src = "validateCode.do?id="+Math.random(); 
    	});
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
    	function validateCode(){//获取验证码
    		document.getElementById("verification_img").src = "validateCode.do?id="+Math.random(); 
    	}
    </script>
  <body style="background-color: whitesmoke; ">
		<div class="login-title"><img src="resources/images/logo-pandassss.png"></img></div>
		<div class="login-window">
			<div class="login-window-input">
				<form action="userLoginAction.do" class="layui-form layui-form-pane" onsubmit="return login_onsubmit()" method="post">
					<!-- <img style="vertical-align: middle;" src="resources/images/login-account.png" />&nbsp;<span class="login-account-span">账号：</span><input placeholder="请输入账号" id="account" class="login-account" name="account" type="text" value="${loginedusername}"/><br/>
					<img style="vertical-align: middle;" src="resources/images/login-password.png" />&nbsp;<span class="login-password-span">密码：</span><input placeholder="请输入密码" id="password" class="login-password" name="password" type="text" value="${logineduserpassword}" /><br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="login-btn" class="login-btn" type="submit" name="login-btn" value="登    录" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label for="save-pwd-checkbox"><input class="save-pwd-checkbox" id="save-pwd-checkbox" name="savepwd" type="checkbox" value="true" <%=request.getAttribute("logineduserpassword")!=null?"checked='checked'":"" %>/><span class="save-pwd">&nbsp;&nbsp;保存密码</span></label> -->
					    <div class="layui-form-item">
						    <label class="layui-form-label"><img style="vertical-align: middle;" src="resources/images/login-accounts.png" />&nbsp;帐 号</label>
						    <div class="layui-input-inline">
						      <input id="account" name="account" lay-verify="account" placeholder="请输入帐号" autocomplete="off" class="layui-input" type="text" value="${loginedusername}"/>
						    </div>
  						</div>
  						 <div class="layui-form-item">
						    <label class="layui-form-label"><img style="vertical-align: middle;" src="resources/images/login-passwords.png" />&nbsp;密 码</label>
						    <div class="layui-input-inline">
						      <input id="password" name="password" lay-verify="password" placeholder="请输入密码" autocomplete="off" class="layui-input" type="password" value="${logineduserpassword}"/>
						    </div>
  						</div>
  						 <div class="layui-form-item">
  						 <label class="layui-form-label"><img src="resources/images/login-verification.png"></img>&nbsp;验 证</label>
				            <div class="layui-input-inline">
				                <input type="text" id="verification_code" name="verification_code" lay-verify="verification_code" placeholder="验证码" autocomplete="off" class="layui-input">
				                <div class="captcha"><img id="verification_img" name="verification_img" src="" alt="" title='点击切换' onclick="validateCode()"></div>
				            </div>
        				</div>
  						<div class="login-bottom-tool">
  							<button class="layui-btn layui-btn-normal" lay-submit lay-filter="*">登     录</button>
  							<input id="save-pwd-checkbox" name="savepwd" title="保存密码" type="checkbox" value="true" <%=request.getAttribute("logineduserpassword")!=null?"checked='checked'":"" %>/>
  						</div>
				</form>
			</div>
		</div>
		<div class="login-bottom">
			<span>©2017-XXZ</span>
		</div>
	</body>
	<script>
	 layui.use(['form', 'layer'], function(){
		  var form = layui.form()
		  ,layer = layui.layer;
		  //自定义验证规则
			form.verify({
				account:function(value){
				  	if(value==""){
			  	  return '账号不能为空';
			  	}
				  	if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '账号不能有特殊字符';
			    }
			    if(!/^[A-Za-z0-9]+$/.test(value)){
			      return '账号只能是字母或数字';
			    }
			    if(value.length<6||value.length>20){
			      return '账号字符数需6-20位之间';
			    }
				  },
				   password:function(value){
				   	if(value==""){
			  	  return '密码不能为空';
			  	}
				  	if(!/^[u4e00-\u9fa5]/.test(value)){
			      return '密码不能为中文或空';
			    }
			    if(value.length<6||value.length>20){
			      return '密码字符数需6-20位之间';
			    }
				  },
				verification_code:function(value){
					if(value.length>4||value==""){
						return '验证码字符长度为4位';
					}
				}
			  });
			form.on('submit(*)', function(data){//layui监听提交
				//return false;
			});
	});
	</script>
</html>

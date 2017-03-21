<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户窗口</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="resources/layui/css/layui.css" />
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
  </head>
  	<script type="text/javascript" src="resources/layui/layui.js"></script>
   	<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
  <style>
  	.layui-form-item{
  	   margin: 15px 0px 0px 20px;
  	}
  </style>
  <body>
  <div id="userformDiv">
  		<form id="userForm" class="layui-form layui-form-pane" action="" method="post" onsubmit="">
  			<input type="hidden" id="userid" name="userid" value=""/>
  			<div class="layui-form-item">
			    <label class="layui-form-label">姓名</label>
			    <div class="layui-input-inline">
			      <input id="username" name="username" lay-verify="username" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
			    </div>
			    <label class="layui-form-label">账号</label>
			    <div class="layui-input-inline">
			      <input id="account" name="account" lay-verify="account" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">密码</label>
			    <div class="layui-input-inline">
			      <input id="password" name="password" lay-verify="password" placeholder="请输入" autocomplete="off" class="layui-input" type="password">
			    </div>
			    <label class="layui-form-label">年龄</label>
			    <div class="layui-input-inline">
			      <input id="age" name="age" lay-verify="age" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
			    </div>
  			</div>
  			<div class="layui-form-item">
			    <label class="layui-form-label">手机号码</label>
			    <div class="layui-input-inline">
			      <input id="phoneNumber" name="phoneNumber" lay-verify="phone" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
			    </div>
			    <label class="layui-form-label">邮箱地址</label>
			    <div class="layui-input-inline">
			      <input id="emailAddress" name="emailAddress" lay-verify="email" placeholder="请输入" autocomplete="off" class="layui-input" type="text">
			    </div>
  			</div>
  			<div class="layui-form-item" style="width:609px;">
			<label class="layui-form-label">用户性质</label>
			    <div class="layui-input-block">
			      <select id="administrator" name="administrator">
			        <option value=""></option>
			        <option value="1">管理员</option>
			        <option value="0" selected="selected">普通用户</option>
			      </select>
			    </div>
			</div>
  			<div class="layui-form-item" pane style="width:606px;">
  				<label class="layui-form-label">性别</label>
			    <div class="layui-input-block" >
			      <input id="gender_m" name="gender" title="男" value="1" checked="checked" type="radio">
      			  <input id="gender_w" name="gender" title="女" value="0" type="radio">
			    </div>
  			</div>
  			  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn"  lay-submit lay-filter="*" type="submit" onclick="submitUser()"><i class="layui-icon">&#xe618; </i> 立 即 保 存</button>
			      <button id="reset" type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon">&#xe60e; </i> 重 置</button>
			      <button id="close_btn" class="layui-btn layui-btn-danger"><i class="layui-icon">&#x1006; </i> 关 闭</button>
			    </div>
  			</div>
  		</form>
  </div>
  </body>
  <script>
   var url="";
   var form;
   layui.use('form', function(){
   form = layui.form(); //只有执行了这一步，部分表单元素才会修饰成功
   	var url_iframeid=getQueryString('iframeid');//通过url参数获取弹层唯一ID判断添加和修改
   	if(url_iframeid=="add-iframe"){
   		$('#reset').click();//添加侧清空form
   		url="UserManagementAction/saveUserAll.do";
  	}else{
  		url="UserManagementAction/updateUserAll.do";
  	}
   layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  form.on('submit(*)', function(data){
		  //默认提交方式
	  });
	  //自定义验证规则
		form.verify({
		  username: function(value){
		  	if(value==""){
		  	  return '姓名不能为空';
		  	}
		    if(!/^[a-zA-Z\u4e00-\u9fa5]+$/.test(value)){
		      return '姓名只能是中文或字母';
		    }
		    if(value.length>10){
		      return '你的姓名也太长了';
		    }
		    },
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
			  age:function(value){
			  	 if(value==""){
			  	  return '年龄不能为空';
			  	 }
			  	 if(value<1||value>120){
			  	  return value+'岁太久or太少了';
			  	 }
			  	 if(!/-?[1-9]\d*/.test(value)){
			  	  return '年龄只能输入数字';
			  	 }
			  }
		  });
	  });
	  //监听关闭按钮
		$('#close_btn').on('click',function(){
			 var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		     parent.layer.close(index); //执行关闭自身操作
		 	 return false;
		}); 
   });
	 function submitUser(){
			  //var s=this.form.verify();
			  //alert(JSON.stringify(s))
			  return false;
		  	  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		   	  $.ajax({
				  type: 'POST',
				  url: url,
				  data:$('#userForm').serialize(),
				  dataType: 'text',
				  async: false,
				  success:function(returndata){
				  	if (returndata == 'success') {
				  		window.parent.location.reload();
				  		parent.layer.close(index); //执行关闭自身操作
						parent.layer.msg('保存成功', {icon:1});
						return false;
					}else{
						parent.layer.close(index); //执行关闭自身操作
						parent.layer.msg('保存失败，请联系管理员', {icon:5});
						return false;
					}
				  }
			  });
			}
   function getQueryString(name) {//获取url参数方法
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
  } 
  </script>
</html>

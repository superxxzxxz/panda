<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<link rel="stylesheet" href="resources/layui/css/layui.css" />
		<link rel="stylesheet" href="resources/css/btable.css" />
		<link rel="stylesheet" href="resources/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<script type="text/javascript" src="resources/layui/layui.js"></script>
	<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
	<body>
		<div style="margin:6px 0px 0px 15px; background-color: white;width: 98%;">
			<blockquote class="layui-elem-quote">
				    <button class="layui-btn" id="addUser"><i class="layui-icon">&#xe61f; </i>添加用户</button>
				    <button class="layui-btn" id="editUser"><i class="layui-icon">&#xe642; </i>编辑用户</button>
				    <button class="layui-btn" id="deleteUser"><i class="layui-icon">&#xe640; </i>删除用户</button>
			</blockquote>
			<div id="content" style="width: 100%;height: 533px;"></div>
		</div>
	</body>
	 <script>
	 	var td_index=false;//false没有选中
	 	var dataValues="";//根据userid获取用户数据
	 	//弹层
        layui.config({
            base: 'resources/js/'
        }).use(['btable'], function () {
            var btable = layui.btable(),
            $ = layui.jquery;
            btable.set({
                elem: '#content',
                url: 'UserManagementAction/userLoginAction.do',
                pageSize: 3,
                columns: [{
                    fieldName: '姓名',
                    field: 'username'
                }, {
                    fieldName: '帐号',
                    field: 'account'
                }, {
                    fieldName: '年龄',
                    field: 'age'
                }, {
                    fieldName: '注册时间',
                    field: 'registrationTime'
                }],
                even: true,
                skin: '',
                checkbox: true,
                paged: true,
                singleSelect: true,
                field:'userId'
            });
            btable.render();//渲染table
            //获取选中数据方法
	        function getSelected(){
				btable.getSelected(function(obj){//获取选中一行
					td_index=true;//true为选中一行
					$.ajax({
					  type: 'POST',
					  url: 'UserManagementAction/getUserId.do',
					  data: {userid:obj.id},
					  dataType: 'json',
					  async:false,//同步
					  success:function(data){
					  	dataValues=data;//回调数据给全局
					  }
					});
	        	});
	        	return td_index;
			}
            //添加用户事件
            $('#addUser').on('click', function() {
            	Elasticlayer('add-iframe','添加用户信息','655px','380px','secureaccessurl.do?path=usermanagement/userwindow/userwin');
			});
			//修改用户事件
			$('#editUser').on('click',function(){
				var edit_index=getSelected();
				if(edit_index==true){
					Elasticlayer('edit-iframe','修改用户信息','655px','380px','secureaccessurl.do?path=usermanagement/userwindow/userwin');
		 		}else{
		 			layer.msg('请选择一条数据', {icon:5});
		 		}
			});
			//删除用户事件
			$('#deleteUser').on('click',function(){
				var delete_index=getSelected();
				if(delete_index==true){
					//询问框
					layer.confirm('确定删除姓名为<span style="color:red;">&nbsp;"'+dataValues.username+'"&nbsp;</span>的用户信息？', {
					  btn: ['确认删除','取消'] //按钮
					}, function(index){
						//删除操作
						$.ajax({
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
						});
					}, function(){
						//取消删除
					});
		 		}else{
		 			layer.msg('请选择一条数据', {icon:5});
		 		}
			});
	        $(window).on('resize', function (e) {
	            var $that = $(this);
	            $('#content').height($that.height() - 92);
	        }).resize();
        	});
		//弹层方法
		function Elasticlayer(id,title,w,h,content){
			layer.open({
		 		id:id,
			 	title: title,
				maxmin: true,//开启最大化true/false
				type: 2,//弹层类型
				content: content+'&iframeid='+id,//内容/引用页面
				area: [w,h],//宽/高
				btnAlign: 'c',//按钮居中对齐
				moveOut: true,//拖出
				shade: [0.8, '#393D49'],//遮罩
				closeBtn: 1,//右上关闭按钮样式1,2可选
				anim:0,//动画0-6，关闭-1
				zIndex:19960220,
		        /*btn: ['保存', '关闭'],
		        yes: function(index, layero){//按钮【按钮一】的回调
		        	//layero.submit();
		        	layero.find("#username").val("sssss");
		        	//$(window.parent.document).contents().find("#userform")[0].contentWindow.submitUser(); 
			  	},
			  	close: function(index, layero){//按钮【按钮二】的回调
			  		layer.closeAll();
			    	//return false 开启该代码可禁止点击该按钮关闭
				},
				rightclose: function(){//右上关闭回调 
					layer.closeAll();
				},*/
				success: function(layero, index){
				    if(id=="edit-iframe"){//只有修改时填充数据
				    	var body = layer.getChildFrame('body', index);//获取弹层dom对象 
					    body.find('#userid').val(dataValues.userId);
					    body.find('#username').val(dataValues.username);
					    body.find('#account').val(dataValues.account);
					    body.find('#password').val(dataValues.password);
					    body.find('#age').val(dataValues.age);
					    body.find('#phoneNumber').val(dataValues.phoneNumber);
					    body.find('#emailAddress').val(dataValues.emailAddress);
					    if(dataValues.gender=='1'){
					    	body.find("span").eq(0).click();
					    }else{
					    	body.find("span").eq(1).click();
					    }
					    if(dataValues.administrator=='1'){
					    	body.find("dd[lay-value='1']").click();
					    }else{
					    	body.find("dd[lay-value='0']").click();
					    }
				    }
  				},
  				end : function(){ 
  					//弹层销毁后回调(没有参数)
				}    
		      });
		}
    </script>
</html>
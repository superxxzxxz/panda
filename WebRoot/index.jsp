<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
	    <title>熊猫服务平台</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="shortcut icon" href="resources/images/title-panda.icon">
		<link rel="stylesheet" href="resources/layui/css/layui.css" />
		<link rel="stylesheet" href="resources/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body>
		<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<div class="logo" href="#">
							<img class="logo-panda" src="resources/images/logo-panda.png"></img>
						</div>
						<div title="隐藏菜单" class="admin-side-toggle">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
						<div title="全屏" class="admin-side-full">
							<i class="fa fa-life-bouy" aria-hidden="true"></i>
						</div>
					</div>
					<ul class="layui-nav admin-header-item">
						<li class="layui-nav-item">
							<a href="javascript:;">清 除 缓 存</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">浏 览 网 站</a>
						</li>
						<li class="layui-nav-item" id="video1">
							<a href="javascript:;">关 于</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="resources/images/0.jpg" />
								<span>beginner</span>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
								</dd>
								<dd>
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd>
								<dd id="lock">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd>
								<dd>
									<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite>控制面板</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
						<div class="layui-tab-item layui-show">
							<iframe src=""></iframe>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>2017 &copy; XXZ</p>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>

			<!--锁屏模板 start-->
			<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="resources/images/0.jpg" />
					</div>
					<div class="admin-header-lock-name" id="lockUserName">beginner</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
			<!--锁屏模板 end -->
			<script type="text/javascript" src="resources/layui/layui.js"></script>
			<script type="text/javascript" src="resources/js/nav.js"></script>
			<script src="resources/js/index.js"></script>
			<script>
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;

					$('#video1').on('click', function() {
						layer.open({
							title: '关于',
							maxmin: true,
							type: 2,
							content: 'video.html',
							area: ['800px', '500px']
						});
					});

				});
			</script>
		</div>

	</body>

</html>
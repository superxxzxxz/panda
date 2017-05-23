<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>首页-echarts图表信息展示</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <!-- js start 使用require.js来管理js加载 -->
	<script src="resources/js/require.js"></script>
	<script src="resources/js/main.js"></script> 
  </head>
  <body>
   <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
   <div id="echarts-main" style="width: 500px;height:300px;border: 1px ;"></div>
   <script type="text/javascript">
   require(['echarts'], function(echarts) {  //加载模块
	 // 基于准备好的dom，初始化echarts实例
	 var myChart = echarts.init(document.getElementById('echarts-main'));

	 var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
	 var data = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	 var yMax = 500;
	 var dataShadow = [];

	 for (var i = 0; i < data.length; i++) {
	     dataShadow.push(yMax);
	 }

	 option = {
	     title: {
	         text: '系统日志信息',
	         x: 'left'
	         //subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
	     },
	     tooltip: {
	    	  trigger: 'item',
	          formatter: "{a} <br/>{b} : {c} ({d}%)"
		     },
	     legend: {
             data:['日志']
         },
	     xAxis: {
	         data: dataAxis,
	         axisLabel: {
	             inside: true,
	             textStyle: {
	                 color: '#fff'
	             }
	         },
	         axisTick: {
	             show: false
	         },
	         axisLine: {
	             show: false
	         },
	         z: 10
	     },
	     yAxis: {
	         axisLine: {
	             show: false
	         },
	         axisTick: {
	             show: false
	         },
	         axisLabel: {
	             textStyle: {
	                 color: '#999'
	             }
	         }
	     },
	     dataZoom: [
	         {
	             type: 'inside'
	         }
	     ],
	     series: [
	         { // For shadow
	        	 name: '日志',
	             type: 'bar',
	             itemStyle: {
	                 normal: {color: 'rgba(0,0,0,0.05)'}
	             },
	             barGap:'-100%',
	             barCategoryGap:'40%',
	             data: dataShadow,
	             animation: false
	         },
	         {
	             type: 'bar',
	             itemStyle: {
	                 normal: {
	                     color: new echarts.graphic.LinearGradient(
	                         0, 0, 0, 1,
	                         [
	                             {offset: 0, color: '#83bff6'},
	                             {offset: 0.5, color: '#188df0'},
	                             {offset: 1, color: '#188df0'}
	                         ]
	                     )
	                 },
	                 emphasis: {
	                     color: new echarts.graphic.LinearGradient(
	                         0, 0, 0, 1,
	                         [
	                             {offset: 0, color: '#2378f7'},
	                             {offset: 0.7, color: '#2378f7'},
	                             {offset: 1, color: '#83bff6'}
	                         ]
	                     )
	                 }
	             },
	             data: data
	         }
	     ]
	 };
	 //chart.setOption(option, notMerge, lazyUpdate);
	 // Enable data zoom when user click bar.
	 var zoomSize = 6;
	 myChart.on('click', function (params) {
	     console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	     myChart.dispatchAction({
	         type: 'dataZoom',
	         startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
	         endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	     });
	 });
	 // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
	 	
});
	 
 </script> 

  </body>
</html>

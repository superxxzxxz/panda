/**
 * 使用require配置项
 */    
require.config({  
      baseUrl : 'resources', //基目录 
      map: {
          '*': {
              css: 'js/css.min'
          }
      },
      paths : {  //paths ：js文件路径;
           jquery : 'js/jquery-3.1.1.min', 
           layui : 'layui/layui',
           layer: 'layer/layer',
           echarts:'echarts3-5-4/echarts',
           datetimepickerzh:'bootstrap-3.3.7-dist/js/bootstrap-datetimepicker.zh-CN',
           bootstraptablezh: 'bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN'
      },
      shim : {  //依赖关系
          bootstraptablezh : {  //由于bootstraptablezh要在依赖之后加载
              deps : [ 'jquery' , 
                       'bootstrap-3.3.7-dist/js/bootstrap.min',
                       'bootstrap-3.3.7-dist/js/bootstrap-table',
                       'bootstrap-3.3.7-dist/js/bootstrap-datetimepicker.min',
                       'css!bootstrap-3.3.7-dist/css/bootstrap.min.css',
                       'css!bootstrap-3.3.7-dist/css/bootstrap-table.css',
                       'css!../resources/bootstrap-3.3.7-dist/css/docs.min.css',
                       'css!../resources/bootstrap-3.3.7-dist/css/bootstrap-datetimepicker.min.css'
                     ]
         },
         layui : {  //由于layui要在依赖之后加载
             deps : [ 'jquery',
                      //'../resources/layer/layer',
                      //'css!../resources/layer/mobile/need/layer.css',
                      'css!../resources/layui/css/layui.css'
                    ]
         },
         layer : {  //由于layui要在依赖之后加载
             deps : [ 'jquery',
                      'css!../resources/layer/skin/default/layer.css'
                    ]
         },
         datetimepickerzh : {  //针对datetimepickerzh依赖关系,在jquery之后
             deps : [ 'jquery'
                    ]
        }
     }  
});  

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
           bootstrap: 'bootstrap-3.3.7-dist/js/bootstrap.min',
           layui : 'layui/layui',
           bootstrapTable: 'bootstrap-3.3.7-dist/js/bootstrap-table',
           //bootstraptablezh: 'bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN'
           //css : 'js/css.min'
      },
      shim : {  //依赖关系
    	  bootstrap : {  //针对bootstrap依赖关系
               deps : [ 'jquery' , 
                        'css!bootstrap-3.3.7-dist/css/bootstrap.min.css' 
                      ]
               //exports : 'bootstrap'  
          },
          layui : {  //针对layui依赖关系
               deps : [ 'jquery', 
                        'css!../resources/layui/css/layui.css' 
                      ]
          },
          bootstrapTable : {  //针对bootstrap-table依赖关系
               deps : [ 'jquery',
                        'bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN',
                        'css!bootstrap-3.3.7-dist/css/bootstrap-table.css' 
                     ]
          }
     }  
});  

(function() {  
     require.config({  
          baseUrl : 'resources', //基目录 
          paths : {  //paths ：js文件路径;
               jquery : 'js/jquery-3.1.1.min',  
               bootstrap : 'bootstrap-3.3.7-dist/js/bootstrap.min'  
          },  
          shim : {  //shim : 配置依赖关系;
               bootstrap : {  
                    deps : [ 'jquery' ],  
                    exports : 'bootstrap'  
               }  
          }  
           
     });  
     require(['bootstrap'], function($) {  //require([主模块])
    	 console.log($);
     });  
})(this);
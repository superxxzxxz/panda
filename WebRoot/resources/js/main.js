     require.config({  
          baseUrl : 'resources', //基目录 
          paths : {  //paths ：js文件路径;
               jquery : 'js/jquery-3.1.1.min',  
          } 
     });  
     require(['jquery'], function($) {  //require([主模块])
    	 //return $.Model.extend({});
    	 alert($)
     });  

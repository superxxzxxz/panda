var notchina=/[\u4E00-\u9FA5\uF900-\uFA2D]/;//不允许中文，日文，韩文
function checkaccount(account,password){
	if(account==""||password==""){
		return "请输入完整信息！";
	}
	if(account!=""&&password!=""){
		if(notchina.test(account)||notchina.test(password)){
			return "不允许中文字符！";
    	}
	}
	return "success";
}

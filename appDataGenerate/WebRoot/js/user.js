
function ajax(url,data)
{
	
	$.ajax({
		type: "POST",
		url: url,
		dateType: "html",
	    data:data,
		success: function(returnedData){
			
			if(returnedData!='OK')
			{
				alert(returnedData);
				return false;
			}
			else
			{
				alert("登陆成功");
				$("#loginForm").submit();
			}
		}
	});
}


//登陆异步验证
function validateUserLogin(ctx){
		
		var data = "username="+$("#username").val()+"&userpassword="+$("#password").val();
		console.info("异步验证的值为:"+data);
		console.info(ctx+"/validate");
		ajax(ctx+"/validate",data);
}






<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ctx = basePath;
%>
					
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>


<script type="text/javascript">

 $(function(){
	$("#login").click(function(){
		/* $.ajax({
					type: "POST",
					
					url: window.ctx+"login/check",
					//async:false,
					//dateType: "html",
					
				    data:{username:'aa',password:'123'},
					success: function(returnedData){
						alert(data);
					}
				});	 */
				
				
				alert("ss00");
		/* $.ajax({
					type: "POST",
					
					url: window.ctx+"login/check",
					//async:false,
					//dateType: "html",
					
				    data:{username:'aa',password:'123'},
					success: function(returnedData){
						alert(data);
					}	
				});	 */
				
				
				
		$.post(window.ctx+'/login/check',{username:'aa',password:'112'},function(data){
							alert(data);
							
						});	
  				
	});

});




</script>


  </head>
  
  <body>
    <input type="text" id="username">
    <input type="text" id="password">
    <button id="login">登录</button>
    
    
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#test1").click(function(){
				$.post('login/test',{},function(data){
					alert(data);
					
				});	
			});
			
			$("#test2").click(function(){
				$.post('dataGenerate/insertGen',{},function(data){
					alert(data);
					
				});	
			});
			
			$("#test3").click(function(){
				$.post('dataGenerate/insertFormat',{},function(data){
					alert(data);
					
				});	
			});
			
			
			$("#test4").click(function(){
				$.post('dataGenerate/insertData',{},function(data){
					alert(data);
					
				});	
			});
			
			$("#test5").click(function(){
				$.post('dataGenerate/importDataToDB',{},function(data){
					alert(data);
					
				});	
			});
			
			$("#test6").click(function(){
				$.post('dataGenerate/test',{},function(data){
					alert(data);
					
				});	
			});
			$("#test7").click(function(){
				$.post('dataGenerate/insertAndFormat',{},function(data){
					alert(data);
					
				});	
			});
			
			$("#test8").click(function(){
				$.post('dataGenerate/insertDatas',{},function(data){
					alert(data);
					
				});	
			});
			
			
			$("#submit").click(function(){
				$.post('CountCardController/addBrand',{
					brand_backgorund_color:$("brand_backgorund_color").val(),
					isExist_code:$("#isExist_code").val(),
					bar_code:$("#bar_code").val()
					},function(data){
					alert(data);
					
				});	
			});
			
			
			$("#brandTest").click(function(){
				$.post('CountCardController/pagingBrandData',{
					iCurrentPage:2,
					iPageSize:3
					},function(data){
					alert(data);
					
				});	
			});
			
			$("#delete").click(function(){
				$.post('AccountCard/deleteAccountCard',{
					user_id:$("#user_id").val()
					},function(data){
					alert(data);
					
				});	
			});
			
			$("#selectAccountInPage").click(function(){
				$.post('AccountCard/getAccountCardInPage',{
					iCurrentPage:$("#currentPage").val(),
					iPageSize:$("#pageSize").val()
					},function(data){
					alert(data);
					
				});	
			});
			
			
			
			
			$("#updateAccountCardWithoutPicture").click(function(){
				$.post('AccountCard/updateAccountCardWithoutPicture',{
					 user_id : $("#user_id2").val(), 
					 account_id : $("#account_id2").val(), 
					 card_id : $("#card_id2").val(), 
					 brand_id : $("#brand_id2").val(), 
					 remark : $("#remark2").val()
					},function(data){
					alert(data);
					
				});	
			});
			
			
			
			$("#editBrandWithoutPicture").click(function(){
				$.post('CountCardController/editBrandWithoutPicture',{
					 brand_id : $("#edit_brand_id22").val(),
					 brand_backgorund_color : $("#edit_brand_backgorund_color2").val(),
					 bard_code  : $("#edit_bar_code2").val()
					},function(data){
					alert(data);
					
				});	
			});
			
			
			$("#testCache").click(function(){
				$.post('mytests/tests',{
					 
					},function(data){
					alert(data);
					
				});	
			});
			
			$("#UpdateAppUseJsonPicture").click(function(){
				$.post('TestBrandDepotAppOutsideController/updateAccountCardWithPictureByUserIdUseJson',{
					 
					},function(data){
					alert(data);
					
				});	
			});
			
			$("#UpdateAccountCardAppUseJsonPicture").click(function(){
				$.post('AccountCard/updateAccountCardWithPictureByUserIdUseJson',{
					 
					},function(data){
					alert(data);
					
				});	
			});
			
			$("#uploadFile").click(function(){
				$.post('mytests/importBrand',{
					 
					},function(data){
					alert(data);
					
				});	
			});
			
			
			
			
			$("#importBrand").click(function(){
				$.post('testBrandStore/importDataFromFile',{
					 
					},function(data){
					alert(data);
					
				});	
			});
			
			
		});	
	
		
	</script>
  </head>
  
  <body>
    <button id="test1">测试</button>
    <button id="test2">测试2</button>
    <button id="test3">测试3</button>
    <button id="test4">测试4</button>
    <button id="test5">测试5</button><br/>
    <button id="test6">测试1-1</button>
    <button id="test7">测试2-2</button>
    <button id="test8">测试2-3</button>
    
    <br/>
    <hr/>
    <div>
    <form action="CountCardController/addBrand" method="post" enctype="multipart/form-data">
    
    <input type="file" id="file" name="file">
    
		背景颜色：    
    	<input id="brand_backgorund_color" name="brand_backgorund_color"><br/>
    	是否存在二维码：
    	<select id= "isExist_code" name="isExist_code">
    		<option value="1">是</option>
    		<option value="0">否</option>
    	</select><br/>
    	
    	<input id="bar_code" name="bar_code">
    	<button id= "submit">有图片添加</button>
    	
    </form>
    </div>
   
    <div>
    	
     <form action="CountCardController/editBrandWithPicture" method="post" enctype="multipart/form-data">
    
   	 <input type="file" id="edit_brand_logo_picture" name="edit_brand_logo_picture">
        品牌号：<input name="edit_brand_id">
		背景颜色：    
    	<input id="edit_brand_backgorund_color" name="edit_brand_backgorund_color"><br/>
    	是否存在二维码：
    	<select id= "isExist_code" name="eidt_isExist_code">
    		<option value="1">是</option>
    		<option value="0">否</option>
    	</select><br/>
    	
    	<input id="edit_bar_code" name="edit_bar_code">
    	<button id= "submit">修改品牌带logo</button>
    	
    </form>
    
    </div>
    
    <hr/>
    
    <button id="brandTest">测试Brand分页</button>
    
    <br/><hr/>
    <form action="AccountCard/addAccountCard" method="post" enctype="multipart/form-data">
    	正面图片:<input type="file" id="font_picture" name="font_picture">
    	背面图片:<input type="file" id= "back_picture" name="back_picture"><br/>
    	用户id:<input name="account_id"> 卡号：<input name="card_id"> 品牌：<input name="brand_id">
    	备注：<input name="remark">  
    	<input type="submit" value="提交用户卡添加">
    </form>
    
    
    <hr/>
   
    删除的会员卡id<input id = "user_id"> <button id="delete">删除</button>
    <hr/>
    页数:<input id="currentPage">
 每页大小：<input id="pageSize">
    <button id="selectAccountInPage"> 分页查询AccountCard</button>
    
    <hr/>
   
    用户id：<input id="user_id2">
    account编号：<input id="account_id2">
    卡号：<input id="card_id2">
    品牌好：<input id="brand_id2">
    备注：<input id="remark2">
    <button id="updateAccountCardWithoutPicture"> 不带图片的更新</button>
    <hr/>
    <form action="AccountCard/updateAccountCardPicture" method="post" enctype="multipart/form-data">
    	用户id<input name="update_user_id">
    	正面图片:<input type="file" id="update_font_picture" name=" update_font_picture">
    	背面图片:<input type="file" id= "update_back_picture" name="update_back_picture"><br/>
   	<input type="submit" value="提交">
    </form>
    
    <hr/>
    <form action="AccountCard/updateAccountCardWithPictureByUserId" method="post" enctype="multipart/form-data">
    	用户id<input name="user_id_update">
    	accountid:<input name="account_id_update">
    	cardid:<input name="card_id_update">
    	品牌号：<input name="brand_id_update">
    	备注：<input name="remark_update">
    	
    	正面图片:<input type="file" id="font_picture_update" name=" font_picture_update">
    	背面图片:<input type="file" id= "back_picture_update" name="back_picture_update"><br/>
    	
   	<input type="submit" value="修改图片和卡片信息">
    
    </form>
    
   <hr/>
    
        品牌号：<input name="edit_brand_id22" id="edit_brand_id22">
		背景颜色：    
    	<input id="edit_brand_backgorund_color2" name="edit_brand_backgorund_color2"><br/>
    	是否存在二维码：
    	<select id= "isExist_code2" name="eidt_isExist_code2">
    		<option value="1">是</option>
    		<option value="0">否</option>
    	</select><br/>
    	
    	<input id="edit_bar_code2" name="edit_bard_code2">
    	<button id= "editBrandWithoutPicture">更新品牌库无logo</button>
    	
    <hr/>
    <form action="CountCardController/editBrandLogo" method="post" enctype="multipart/form-data">
       品牌号：<input name="edit_brand_id2233" id="edit_brand_id2233">
    <input type="file" name="edit_brand_logo" >
  <input type="submit"  id= "editBrandOnlyPicture" value="更新品牌库logo">
    </form>
    
    
    <button id="testCache">测试缓存</button>
    
    <button id="UpdateAppUseJsonPicture">测试json修改图片</button>
    <button id="UpdateAccountCardAppUseJsonPicture">测试修改json格式的用户卡</button>
    
    <button id="uploadFile">导入文件到数据库</button>
    <br/>
    
    <button id="importBrand"> 导入品牌信息</button>
    
    <br/>
    
    <img alt="" src="accountPicture/201511142345521201f2b8bb4-3897-4279-86f0-252734a83ffa.jpg">
    <img alt="" src="accountPicture/20151114234749553dd7484f8-1d1e-4048-9fdf-f73accc7c9df.jpg">
 
 
 </body>
</html>

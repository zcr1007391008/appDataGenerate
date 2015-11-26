<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test2.jsp' starting page</title>
    
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
	$("#paging").click(function(){
		$.post('testBrandStore/getDataInPage',{},function(data){
			alert(data);
			
		});	
	});
	
	$("#selectBrandById").click(function(){
		$.post('testBrandStore/getBrandStoreById',{},function(data){
			alert(data);
			
		});	
	});
	
	$("#updateBrand").click(function(){
		$.post('testBrandStore/updateBrand',{},function(data){
			alert(data);
			
		});	
	});
	
	$("#addBrandStore").click(function(){
		$.post('testBrandStore/addBrandStore',{
		 name : $("#name").val(),
		 barcode_format : $("#barcode_format").val(),
		 barcode : $("#barcode").val(),
		 homepage : $("#homepage").val(),
		 regions : $("#regions").val(),
		 other_stores : $("#other_stores").val(),
		 typos : $("#typos").val(),
		
		 logo : $("#logo").val(),
		 background_color : $("#background_color").val(),
		 download_message : $("#download_message").val(),
		},function(data){
			alert(data);
			
		});	
	});
	
	
	
	
	$("#setCache").click(function(){
		$.post('testBrandStore/cacheCity',{},function(data){
			alert(data);
			
		});	
	});
	
	$("#getCache").click(function(){
		$.post('mytests/testGetCache',{},function(data){
			alert(data);
			
		});	
	});
	
	
	
	$("#testAddAccountCardUseJson").click(function(){
		$.post('AccountCard/testAddAccountCardUseJson',{},function(data){
			
		});	
	});
	
	$("#testGenerateFile").click(function(){
		$.post('mytests/testGenerateFile',{},function(data){
			
		});	
	});
	
	$("#updateAccountCardWithPictureByUserIdUseJson").click(function(){
		$.post('AccountCard/updateAccountCardWithPictureByUserIdUseJson',{},function(data){
			
		});	
	});
	
	
	$("#getAccountCardInPage").click(function(){
		$.post('AccountCard/getAccountCardInPage',{
			iCurrentPage:$("#brandPage").val(),
			iPageSize:$("#brandPageSize").val()
			},function(data){
			
		});	
	});
		$("#deleteAccountCard").click(function(){
		$.post('AccountCard/deleteAccountCard',{
			user_id:$("#deleteAccountCardId").val()
		},function(data){
			
		});	
	});
	
	$("#importDataToDBandGenerateExcel").click(function(){
		$.post('mytests/testGenerateExcelAndImportToDB',{
			
		},function(data){
			
		});	
	});
	
	
	$("#importDataToDBandGeneratePDF").click(function(){
		$.post('mytests/importDataToDBandGeneratePDF',{
			
		},function(data){
			
		});	
	});
	
});

</script>


  </head>
  
  <body>
  <h5>测试品牌库</h5>
  <button id="paging">测试分页</button> 
   <button id="selectBrandById">测试根据id获得值</button>
    <button id="updateBrand">测试更新Brand</button>
   <br/>
   
		
		品牌名字：<input id = "name">
		编码格式：<input id="barcode_format">
		二维码：<input id="barcode">
		主页：<input id="homepage">
		地域：<input id="regions">
		其他商店:<input id="other_stores">
		stypos:<input id="typos">
		logo:<input id="logo">
		background_color:<input id="background_color">
		 download_message:<input id="download_message">
		 <button id = "addBrandStore">添加品牌库</button>
   
    
  <hr>
      
    <h5>测试会员卡</h5>
  
    <button id="testAddAccountCardUseJson">测试通过json 格式添加用户卡</button>
     <button id="updateAccountCardWithPictureByUserIdUseJson">测试通过json格式修改用户卡</button>
     <br/>
     开始页：<input id="brandPage">  每页大小：<input id="brandPageSize">
     <button id="getAccountCardInPage">测试用户卡分页</button><br/>
     删除的id:<input id="deleteAccountCardId">
     <button id="deleteAccountCard">测试删除</button>
    <br/>
    
    <hr/>
     <button id="getCache">取缓存</button><button id="testGenerateFile">测试生成文件</button><button id= "setCache">缓存</button> 
     
     <button id = "importDataToDBandGenerateExcel">测试excel并将数据保存到数据库</button>
      <button id = "importDataToDBandGeneratePDF">测试pdf并将数据保存到数据库</button>
<!--      <img alt="" src="brandLogo/android_banner_url/20151121205412708dcadd067-5b98-426b-8731-dcbd18283004.jpg">
    <img alt="" src="brandLogo/ios_banner_url/2015112120541274800b4ed5e-eca0-48f3-a7e7-c9c3287f1efa.jpg">
    <img alt="" src="brandLogo/ios_logo_url/2015112120541275925154da0-bc49-4c15-98dd-11e81ac7a589.jpg"> -->
  </body>
</html>

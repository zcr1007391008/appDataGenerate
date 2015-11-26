

var Globalctx="";


//获取项目根目录
function getProjextctx(arg1)
{
	Globalctx = arg1;
	alert("项目根目录"+Globalctx);
};

//查询所有用户
function searchAllUser()
{
	$("#myTable").empty();
	$.ajax({
		type: "POST",
		url: "searchAllUser",
		dataType: "json",
	    data:{},
		success: function(returnedData){
			var Users = eval(returnedData);
			var html = "";
			html += '<table id="table5" border="0" cellpadding="4" cellspacing="0" align="center" width="50%" >'
			+'<thead>'
			+'<th align="center">账号</th>'
			+'<th align="center">密码</th>'
			+'<th align="center">年龄</th>'
			+'<th align="center">住址</th>'
			+'<th align="center" width="30">View</th>'
			+'<th align="center" width="30">Edit</th>'
			+'<th align="center" width="30">Delete</th>'
		+'</thead>';
			for(var i=0;i<Users.length;i++)
			{
				html +='<tr>'
				+'<td align="left">'+Users[i].username+'</td>'
				+'<td>'+Users[i].userpassword+'</td>'
				+'<td>'+Users[i].userage+'</td>'
				+'<td>'+Users[i].useradress+'</td>'
				
				+'<td align="center"><a onclick="showAddup(300,200);"><img src="./images/magnifier.png" border="0"></a></td>'
				+'<td align="center"><a id="'+Users[i].userID+'" onclick="showPopup(300,200,this);"><img src="./images/page_white_edit.png" border="0"></a></td>'
				+'<td align="center"><a  id="'+Users[i].userID+'" onclick="deleteUser(this);"><img src="./images/publish_r.png" border="0"></a></td>'
				+'</tr>';
				
				
			}
			html +="</table>";
			$("#myTable").html(html);
			
			
			
			$('table#table5').styleTable({
				th_bgcolor: '#BCD4EC',
				th_image: './images/center.jpg',
				th_color: '#000000',
				th_border_color: '#333333',
				tr_odd_image: './images/formbg.gif',
				tr_even_image: './images/hnav-a-bg-black.jpg',
				tr_hover_image: './images/s_bg.jpg',
				tr_even_color: '#ffffff',
				tr_border_color: '#cccccc'
			});
			
		}
	});
	
}

//删除用户
function deleteUser(eve)
{
	console.info("删除的用户id为："+eve.id);
	var data = "";
	data += "userID="+eve.id;
	
	$.ajax({
		type: "POST",
		url: "deleteUser",
		dateType: "json",
	    data:data,
		success: function(returnedData){
			searchAllUser();
			alert("删除成功");
			
		}
	});
	return false;
}





//修改用户信息

function updateUser(id)
{
	console.info("更新的用户id为："+id);
	var username = $("#updateUsername").val();
	var password = $("#updatePassword").val();
	var age = $("#updateAge").val();
	var adress = $("#updateAdress").val();
	var data = "";
	data += "userID="+id+"&username="+username+"&userPassword="+password+"&userAge="+age+"&userAdress="+adress;
	console.info("更新的用户信息："+data);
	$.ajax({
		type: "POST",
		url: "updateUser",
		dateType: "json",
	    data:data,
		success: function(returnedData){
			searchAllUser();
			alert("更新成功");
			
		}
	});
	return false;
}




function addUser()
{
	
	var username = $("#addUsername").val();
	var password = $("#addPassword").val();
	var age = $("#addAge").val();
	var adress = $("#addAdress").val();
	var data3 = "";
	
	
	data3 += "username="+username+"&userPassword="+password+"&userAge="+age+"&userAdress="+adress;
	
	console.info("更新的用户信息："+data3);
	console.info("fdf");
	$.ajax({
		type: "POST",
		url: "addUser",
		dateType: "json",
	    data:data3,
		success: function(returnedData){
			searchAllUser();
			alert("添加成功");
			
		}
	});
	return false;
}









var baseText = null;
var updateuserID = "";
function showPopup(w,h,eve){
	updateuserID = eve.id;
	
	
	
	var popUp = document.getElementById("popupcontent");    popUp.style.top = "200px";  

	popUp.style.left = "200px";

	popUp.style.width = w + "px";

	popUp.style.height = h + "px";

	if (baseText == null)

	baseText = popUp.innerHTML;

	popUp.innerHTML = baseText + "<div id=\"statusbar\"><button onclick=\"hidePopup();\">提交<button></div>"
							   + "<div id=\"statusbar\"><button onclick=\"closePop();\">关闭<button></div>";
    
	var sbar = document.getElementById("statusbar");   

	sbar.style.marginTop = (parseInt(h)-160) + "px";   

	 
	
	popUp.style.visibility = "visible";
	
	
	$.ajax({
		type: "POST",
		url: "selectUserByID",
		dataType: "json",
	    data:{userID:updateuserID},
		success: function(returnedData){
			console.info(returnedData);
			console.info(returnedData.username);
			$("#updateUsername").val("123");
			$("#updatePassword").val(returnedData.userpassword);
			$("#updateAge").val(returnedData.userage);
			$("#updateAdress").val(returnedData.useradress);
		}
	});
	
	
	
	
	
	return false;
	}
	
function hidePopup(){ 

	
	

	var popUp = document.getElementById("popupcontent"); 

	 

	popUp.style.visibility = "hidden"; 
	 updateUser(updateuserID);
	
	
	
return false;
	} 


//隐藏div
 function closePop()
 {
		var popUp = document.getElementById("popupcontent"); 

		 

		popUp.style.visibility = "hidden"; 
 }





function showAddup(w,h){
	
	
	
	var popUp = document.getElementById("userAdd");    popUp.style.top = "200px";  

	popUp.style.left = "200px";

	popUp.style.width = w + "px";

	popUp.style.height = h + "px";

	if (baseText == null)

	baseText = popUp.innerHTML;

	popUp.innerHTML = baseText + "<div id=\"statusbar\"><button onclick=\"hideAddup();\">添加<button></div>"
	+ "<div id=\"statusbar\"><button onclick=\"closeAddup();\">取消<button></div>";

	var sbar = document.getElementById("statusbar");   

	sbar.style.marginTop = (parseInt(h)-160) + "px";   

	 
	
	popUp.style.visibility = "visible";
	
	return false;
	}
	





function hideAddup(){ 

	
	

	var popUp = document.getElementById("userAdd"); 

	 

	popUp.style.visibility = "hidden"; 
	addUser();
	
	
	
	return false;
	} 



$(function(){
	$("#addUser").click(function(){
		
		showAddup(300,200);
	});
});


function  closeAddup()
{
	var popUp = document.getElementById("userAdd"); 

	 

	popUp.style.visibility = "hidden"; 
}





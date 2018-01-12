<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="webo.bean.UserBean,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery/jquery-3.2.0.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<div id="menuTree">
		<div>
			<h1>WeboGSW</h1>
		</div>
		<ul>
			<li id="userInfor">User Information</li>
			<li id="productInfor">Product Information</li>
		</ul>
	</div>
	<div id="content">
		<table id="table"></table>
		<div id="changeContent"></div>
	</div>
	<script type="text/javascript">
	function getTable(arg1,arg2,arg3){
		$.ajax({
			type:arg1,
			url:arg2,
			dataType:"json",
			data:arg3,
			success:function(data){
				$("#table").empty();
				$("#table").append("<tr><th>ID</th>" + 
						"<th>User Name</th>" + 
						"<th>Password</th>" + 
						"<th>firstName</th>" + 
						"<th>middleName</th>"+
						"<th>Last Name</th>"+
						"<th>birthday</th>"+
						"<th>email</th>"+
						"<th></th></tr>")
				$(data).each(function(i){
					var $tr =$("<tr></tr>");
					var $td =$("<td></td>");
					$tr.append($td.clone().text(data[i].id));
					$tr.append($td.clone().text(data[i].username));
					$tr.append($td.clone().text(data[i].password));
					$tr.append($td.clone().text(data[i].firstName));
					$tr.append($td.clone().text(data[i].middleName));
					$tr.append($td.clone().text(data[i].lastName));
					$tr.append($td.clone().text(data[i].birthday));
					$tr.append($td.clone().text(data[i].email));
					$tr.append("<td><a class=\"change\">Change</a></td>")
					$tr.appendTo($("#table"));
				})
			}
		})
	}
	//this is for getting user information table from database
	$("#userInfor").click(function(){
		getTable("post","RetrieveUsersServlet",null);
	});
	//this is for clicking edit button to edit the current data
	$("#table").on("click",".change",function(){
		$("#changeContent").empty();
		var list = new Array(); 
		var td = $(this).parent().siblings();
		for(var i=0 ; i < td.length;i++){
			list[i]=td.eq(i).html();
		}
		$("#changeContent").html(
		+ "<form action=\"ChangeServlet\" method=\"post\">"
		+ "Username:  <input type=\"text\" name=\"firstName\"  value=\"" + list[3] +"\"/><br>"
		+ "First Name:<input type=\"text\" name=\"middleName\" value=\"" + list[4] +"\"/><br>"
		+ "Middle Name:<input type=\"text\" name=\"lastName\" value=\"" + list[5] +"\"/><br>"
		+ "Birthday:<input type=\"text\" name=\"birthday\" value=\"" + list[6]+"\"/><br>" 
		+ "Email:<input type=\"text\" name=\"email\" value=\"" + list[7] + "\"/><br>"
		+ "<input type=\"submit\" value=\"OK\">"
		+ "<button onclick=\"getTable(\"get\",\"RetrieveUsersServlet\",null)\">Cancel</button>"
		+ "</form>");
	})
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="webo.bean.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="jquery/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>WeboGSW</title>
<style type="text/css">
#logo {
	height: 100%
}

.navbar-brand {
	padding: 5px 5px;
}

.bookscat {
	font-family: 'Georgia', Arial, serif;
	font-style: italic;
	font-size: 16px;
	line-height: 17px;
	color: #0871b3;
	font-weight: normal;
	border-bottom: solid 4px #eaeaea;
	padding: 0 0 8px 1px;
}
.subcat{margin: 20px auto;cursor: pointer;}
.subcatul{padding-left:15px;}
.subcatul li{border-bottom: solid 1px #d8d8d8;padding-top:5px;padding-bottom:5px;}
</style>
</head>
<body>
	<!-- modal -->
	<div class="modal fade" id="signin" tabindex="-1" role="dialog"
		aria-labelledby="signinModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="signinModalLabel">Log In</h4>
				</div>
				<div class="modal-body">
					<form class="account">
						<div class="form-group">
							<label>Username</label> <input id="username" type="text"
								class="form-control" placeholder="Username">
						</div>
						<div class="form-group">
							<label>Password</label> <input id="password" type="password"
								class="form-control" placeholder="Password">
						</div>
						<div class="checkbox">
							<label><input type="checkbox"> Remember me </label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="signinbutton" type="button" class="btn btn-primary">Sign
						In</button>
				</div>
			</div>
		</div>
	</div>
	<span id="saveid" class="hidden"><%=session.getAttribute("userid")%></span>
	<script>
		var userid;
		$("#signinbutton").click(
				function() {
					var username = $("#username").val();
					var password = $("#password").val();
					$
							.ajax({
								type : "get",
								url : "SignInServlet",
								data : {
									username : username,
									password : password
								},
								success : function(result) {
									if (result == "false") {
										$("#username").addClass("bg-danger");
										alert("error");
									} else {
										var data = eval(result);
										$("#saveid").text(data.userid);
										$("#cart").attr(
												"href",
												"CheckCartServlet?useridforcart="
														+ data.userid)
										$("#badge").removeClass("hide").text(
												data.itemnum);
										$("#frontbutton").empty().removeClass(
												"form-inline navbar-form")
												.append(
														"<p class=\"navbar-text\">Welcome "
																+ data.username
																+ "</p>");
										$("body").removeClass("modal-open")
												.css("padding-right", "0px");
										$(".modal").removeClass("in").css(
												"display", "none");
										$(".modal-backdrop").removeClass("in")
												.remove();

									}
								}
							});
				})
	</script>
	<!-- This is the top part -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><img id="logo"
					alt="logo" src="image/logo.png"></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.jsp"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;HomePage
							<span class="sr-only">(current)</span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span class="glyphicon glyphicon-list"
							aria-hidden="true"></span>&nbsp;Category<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="GetBookServlet?category=MATH">MATH</a></li>
							<li><a href="GetBookServlet?category=Science">Science</a></li>
							<li><a href="GetBookServlet?category=Art">Art</a></li>
						</ul></li>
				</ul>
				<!-- Search bar -->
				<form class="navbar-form navbar-left" action="#">
					<div class="input-group">
						<div class="input-group-btn">
							<button id="catbtn" type="button"
								class="btn btn-default dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								All <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a class="searchoption">Author</a></li>
								<li><a class="searchoption">ISBN</a></li>
								<li><a class="searchoption">Title</a></li>
							</ul>
						</div>
						<!-- /btn-group -->
						<input id="searchcontent" type="text" class="form-control"
							placeholder="Search">
					</div>
					<a id="search" class="btn btn-default"> <span
						class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</a>
					<script type="text/javascript">
						$(".searchoption").click(function() {
							$(".searchoption").removeClass("searchthis");
							$(this).addClass("searchthis");
							$("#catbtn").text($(".searchthis").text());
						})
						//Didn't solve this yet.
						$("#searchcontent").keypress(function(e) {
							if (e.which == 13) {
								e.preventDefault();
								var cat = $(".searchthis").text();
								var infor = $("#searchcontent").val();
								var url = getUrl(cat, infor);
								$("#search").attr("href", url).click();
							}
						})
						$("#searchcontent").on("blur", function() {
							var cat = $(".searchthis").text();
							var infor = $("#searchcontent").val();
							$("#search").attr("href", getUrl(cat, infor));
						})
						function getUrl(cat, infor) {
							var url;
							switch (cat) {
							case "ISBN":
								url = "QueryBookServlet?bookisbn=" + infor;
								break;
							case "Author":
								url = "GetBookServlet?author=" + infor;
								break;
							case "Title":
								url = "GetBookServlet?title=" + infor;
							default:
								break;
							}

							return url;
						}
					</script>
				</form>
				<div class="form-inline navbar-form navbar-right">
					<a id="cart" class="btn" style="color: black;"
						href="CheckCartServlet?useridforcart=<%=session.getAttribute("userid")%>">
						<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
						<span id="badge" style="background-color: red;"
						class="badge <%if (session.getAttribute("currentUser") == null) {%>hide<%}%>"><%=session.getAttribute("productnum")%>
					</span>
					</a>
				</div>
				<!-- Account -->
				<div id="account"
					class="btn-group form-inline navbar-form navbar-right"
					style="margin-right:-30px;display:<%if (session.getAttribute("currentUser") == null) {%>none<%}%>">
					<button type="button" class="btn dropdown-toggle"
						style="background-color: #f8f8f8;" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">Change</a></li>
						<li><a href="#">Action</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="UserLogOutServlet">Log Out</a></li>
					</ul>
				</div>
				<%
					if (session.getAttribute("currentUser") != null) {
				%>
				<div id="loggin" style="float: right">
					<p class="navbar-text">
						Welcome &nbsp;<%=session.getAttribute("currentUser")%></p>
				</div>

				<%
					} else {
				%>
				<div id="frontbutton" class=" navbar-right">
					<a class="navbar-text" data-target="#signin" data-toggle="modal"
						style="cursor: pointer">Sign In</a> <a class="navbar-text"
						href="Register.jsp">Sign Up</a>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</nav>

	<div class="container" style="margin-top: 60px;">
		<ol class="breadcrumb">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">${requestScope.cat }</li>
		</ol>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<h4 class="bookscat text-center">${requestScope.cat }</h4>
				<ul class="subcatul" style="width: 100%;">
					<li><a class="subcat">Architecture</a></li>
					<li><a class="subcat">Music</a></li>
					<li><a class="subcat">Painting</a></li>
					<li><a class="subcat">Performing Arts</a></li>
					<li><a class="subcat">Vehicle Pictorials</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<c:forEach items="${requestScope.booklist }" var="book">
					<div class="col-xs-6 col-md-3">
						<div class="thumbnail">
							<img src="image/books/${book.bookPicRef }.jpg" alt="..." style="widows: 200px;height: 300px;">
							<div class="caption">
								<h5>
									<a href="QueryBookServlet?bookisbn=${book.bookISBN }"><c:out
											value="${book.bookName }"></c:out></a>
								</h5>
								<small> <a href="#"><c:out
											value="${book.bookAuthName }"></c:out></a>
								</small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-12">
				<nav aria-label="Page navigation" style="text-align: center;">
					<ul class="pagination">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<li class="active"><span>1<span class="sr-only">(current)</span></span></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>

</body>
</html>
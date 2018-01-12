<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="webo.bean.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="jquery/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<title>WeboGSW</title>
<style type="text/css">
#logo {height: 100%
}

.navbar-brand {
	padding: 5px 5px;
}

#best-sellers h3 {
	padding: 0 0 7px 3px;
	border-bottom: solid 4px #eaeaea;
	margin-bottom: 12px;
	font-family: 'Georgia', Arial, serif;
	font-style: italic;
	font-size: 22px;
	line-height: 24px;
}

.jcarousel {
	position: relative;
	overflow: hidden;
}

.jcarousel ul {
	width: 20000em;
	position: relative;
	list-style: none;
	margin: 0;
	padding: 0;
}

.jcarousel li {
	float: left;
}

.jcarousel-prev, .jcarousel-next {
	width: 28px;
	height: 47px;
	background: url(image/directions.png) no-repeat;
	position: absolute;
	top: 60px;
	cursor: pointer;
}

.product {
	padding: 20px 30px;
}

.product a:hover {
	text-decoration: none;
	opacity: 0.7;
}

.product img {
	display: block;
}

.product .book-name {
	display: block;
}

.product .author {
	display: block;
}

.booktitle {
	font-family: 'Georgia', Arial, serif;
	font-style: italic;
	font-size: 22px;
	color: #fff;
}

.bookdes {
	font-family: 'Georgia', Arial, serif;
	color: #fff;
}
.social:hover {
     -webkit-transform: scale(1.1);
     -moz-transform: scale(1.1);
     -o-transform: scale(1.1);
 }
 .social {
     -webkit-transform: scale(0.8);
     /* Browser Variations: */
     
     -moz-transform: scale(0.8);
     -o-transform: scale(0.8);
     -webkit-transition-duration: 0.5s;
     -moz-transition-duration: 0.5s;
     -o-transition-duration: 0.5s;
 }

/*
    Multicoloured Hover Variations
*/
 
 #social-fb:hover {
     color: #3B5998;
 }
 #social-tw:hover {
     color: #4099FF;
 }
 #social-gp:hover {
     color: #d34836;
 }
 #social-em:hover {
     color: #f39c12;
 }
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
										$("#account").css("display", "inline");
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
								<li><a class="searchoption">Publisher</a></li>
								<li><a class="searchoption">CRN</a></li>
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
					<button type="button" class="btn dropdown-toggle"  style="background-color: #f8f8f8;"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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

	<div class="jumbotron"
		style="margin-top: 50px; background-color: #588fe8;">
		<div class="row" style="margin-left: 0px;margin-right: 0px;">
			<div id="promotion" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#promotion" data-slide-to="0" class="active"></li>
					<li data-target="#promotion" data-slide-to="1"></li>
					<li data-target=#promotion data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<div class="col-md-1"></div>
						<div class="col-md-4">
							<img src="image/promotion.png" alt="...">
						</div>
						<div class="col-md-6">
							<h3 class="booktitle">Special Offers</h3>
							<p class="bookdes" style="font-size: 15px;">When scholars
								write the history of the world twenty years from now, and they
								come to the chapter "Y2K to March 2004," what will they say was
								the most crucial development? The attacks on the World Trade
								Center on 9/11 and the Iraq war?</p>
						</div>
						<div class="col-md-1"></div>
					</div>
					<div class="item">
						<div class="col-md-1"></div>
						<div class="col-md-4">
							<img src="image/promotion.png" alt="...">
						</div>
						<div class="col-md-6">
							<h3 class="booktitle">Special Offers</h3>
							<p class="bookdes" style="font-size: 15px;">When scholars
								write the history of the world twenty years from now, and they
								come to the chapter "Y2K to March 2004," what will they say was
								the most crucial development? The attacks on the World Trade
								Center on 9/11 and the Iraq war?</p>
						</div>
						<div class="col-md-1"></div>
					</div>
					<div class="item">
						<div class="col-md-1"></div>
						<div class="col-md-4">
							<img src="image/promotion.png" alt="...">
						</div>
						<div class="col-md-6">
							<h3 class="booktitle">Special Offers</h3>
							<p class="bookdes" style="font-size: 15px;">When scholars
								write the history of the world twenty years from now, and they
								come to the chapter "Y2K to March 2004," what will they say was
								the most crucial development? The attacks on the World Trade
								Center on 9/11 and the Iraq war?</p>
						</div>
						<div class="col-md-1"></div>
					</div>
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#promotion" role="button"
					data-slide="prev" style="background-image: none;"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#promotion" role="button"
					data-slide="next" style="background-image: none;"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>

	<div class="container"
		style="margin-top: 50px; font-family: 'Georgia', Arial, serif; font-style: italic;">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<blockquote class="blockquote-reverse">
					<p>Whenever you read a good book, somewhere in the world a door
						opens to allow in more light.</p>
					<footer>Vera Nazarian</footer>
				</blockquote>
			</div>
			<div class="col-md-1"></div>
		</div>

	</div>
	<!-- this is for best sellers part -->
	<div class="container" style="margin-top: 50px;">
		<div class="row">
			<div id="best-sellers">
				<h3>Best Sellers</h3>
				<div class="col-md-1 text-right">
					<a class="jcarousel-prev" href="#"
						style="background-position: 0 0;"></a>
				</div>
				<div class="col-md-10">
					<div class="jcarousel">
						<ul>
							<li>
								<div class="product">
									<a href="#"> <img src="image/1.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/2.jpg" alt="" /> <span
										class="book-name">Book Name </span><span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/3.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/4.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/1.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/2.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/3.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
							<li>
								<div class="product">
									<a href="#"> <img src="image/4.jpg" alt="" /> <span
										class="book-name">Book Name </span> <span class="author">by
											John Smith</span>
									</a>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-1 text-left">
					<a class="jcarousel-next" href="#"
						style="background-position: right 0;"></a>
				</div>
			</div>
			<script>
				$(function() {
					$('.jcarousel').jcarousel({
						wrap : 'circular'
					}).jcarouselAutoscroll({
						interval : 3000,
						target : '+=1',
						autostart : true
					});
					$('.jcarousel-prev').jcarouselControl({
						target : '-=1'
					});

					$('.jcarousel-next').jcarouselControl({
						target : '+=1'
					});
				});
			</script>
			<!-- End Best-sellers -->
		</div>
	</div>
	
	<!-- footer -->
	
	<div class="container" style="margin-top: 50px;">
		<div class="row">
			<div class="col-md-6">
				<h4 class="footertitle" style="font-size: 16px;line-height: 18px;border-bottom: solid 4px #57b4d9;margin-bottom: 3px;padding-bottom: 6px;font-family: 'Georgia', Arial, serif;font-style: italic;">About WeboGSW</h4>
				<p style="font-size: 12px;line-height: 18px;">WeboGSW is an online book store which will provide better and more convenient customer services aimed directly at GSW student.In addition, WeboGSW will provide study notes in convenient manner to students. Student also can sell their used book or notes to WeboGSW with a reasonable price or exchange the book or notes with equivalent price.</p>
			</div>
			<div class="col-md-2" >
				<h4 style="font-size: 16px;line-height: 18px;border-bottom: solid 4px #57b4d9;margin-bottom: 3px;padding-bottom: 6px;font-family: 'Georgia', Arial, serif;font-style: italic;">Store</h4>
				<ul style="list-style: none;">
					<li>Home</li>
					<li>Contact us</li>
					<li>Account</li>
				</ul>
			</div>
			<div class="col-md-4">
				<h4 style="font-size: 16px;line-height: 18px;border-bottom: solid 4px #57b4d9;margin-bottom: 3px;padding-bottom: 6px;font-family: 'Georgia', Arial, serif;font-style: italic;">Follow us</h4>
				<a href="#"><i id="social-fb" class="fa fa-facebook-square fa-3x social"></i></a>
	            <a href="#"><i id="social-tw" class="fa fa-twitter-square fa-3x social"></i></a>
	            <a href="#"><i id="social-gp" class="fa fa-google-plus-square fa-3x social"></i></a>
	            <a href="#"><i id="social-em" class="fa fa-envelope-square fa-3x social"></i></a>
			</div>
		</div>
	</div>
	
	<div class="container" style="background-color:#78b8d2;">
		<div class="row">
			<small>© WeboGSW.com. Design by Kai,Aslam,and Alecia</small>
		</div>
	</div>
</body>
</html>
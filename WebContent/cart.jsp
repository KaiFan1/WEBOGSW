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
						<span id="badge" style="background-color: red;" class="badge hide"><%=session.getAttribute("productnum")%>
					</span>
					</a>
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
				<div id="frontbutton" class="form-inline navbar-form navbar-right">
					<a class="btn btn-primary" type="button" data-target="#signin"
						role="button" data-toggle="modal"><span
						class="glyphicon glyphicon-user" aria-hidden="true"></span>
						&nbsp;Login In&nbsp; </a>
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
			<li><a href="#">Art</a></li>
			<li><a href="#">Book</a></li>
			<li class="active">Cart</li>
		</ol>
	</div>

	<div class="container">
		<div class="row">
			<div id="productpanel" class="panel panel-primary">
				<div class="panel-heading">
					<h1>Product List</h1>
				</div>
				<div id="propanbody" class="panel-body">
					<div class="container">
						<div class="col-md-8">
							<h4>Product</h4>
						</div>
						<div class="col-md-1">
							<h4>Price</h4>
						</div>
						<div class="col-md-2">
							<h4 class="text-center">Quantity</h4>
						</div>
						<div class="col-md-1"></div>
					</div>

					<hr style="width: 95%; border-top: 2px solid #eee;">

					<c:forEach items="${addedbook}" var="book">
						<div class="container" style="margin-top: 20px;">
							<div class="col-md-8">
								<div class="row">
									<div class="col-md-3">
										<img alt="" src="image/books/${book.value.bookPicRef }.jpg"
											style="width: 100px; height: 150px">
										<h6>
											ISBN:<span id="bookisbn" class="isbn"><c:out
													value="${book.value.bookISBN }"></c:out></span>
										</h6>
									</div>
									<div class="col-md-9">
										<h4>
											<c:out value="${book.value.bookName }"><</c:out>
										</h4>
										<h5>
											Stock:<span class="text-danger"><c:out
													value="${book.value.bookStock }"></c:out></span>
										</h5>
									</div>
								</div>
							</div>
							<div class="col-md-1 pricebox">
								<h5 class="text-danger">
									$<span id="price"><c:out
											value="${book.value.bookPrice }"></c:out></span>
								</h5>
							</div>
							<div class="col-md-2 text-center quantitybox">
								<a id="" class="sub btn btn-default" role="button"><span
									class="glyphicon glyphicon-minus"></span></a>&nbsp; <input
									id="quantity" class="quantity text-center" type="text"
									name="quantity"
									value='<c:out value="${book.key.bookQuantity }"></c:out>'
									style="width: 30px;" />&nbsp; <a id=""
									class="add btn btn-default" role="button"><span
									class="glyphicon glyphicon-plus"></span></a>
							</div>
							<div class="col-md-1">
								<button class="delete btn btn-default">Delete</button>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="panel-footer ">
					<div class="row">
						<div class="col-md-8">
							<h4 id="timealert" class="hide">
								You have <span id="time" class="text-danger">10</span> minutes
								to finish your shopping!
							</h4>
						</div>
						<div class="col-md-1">
							<h4 class="text-center">Subtotal:</h4>
						</div>
						<div class="col-md-1">
							<h4 class="text-center text-danger">
								$<span id="totalprice">${requestScope.price}</span>
							</h4>
						</div>
						<div class="col-md-2 text-center">
							<button id="confirmproduct" class="btn btn-success">Confirm</button>
							<button id="cancelproduct" class="btn btn-warning hide">Cancel</button>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$(".add").click(
							function() {
								var p = $(this).parent().siblings(
										"div.pricebox").children().children()
										.text() * 1;
								var q = $(this).siblings("input.quantity")
										.val() * 1 + 1;
								var totalprice = $("#totalprice").text() * 1;
								$(this).siblings("input.quantity").val(q);
								var subtotalprice = totalprice + p;
								$("#totalprice").text(
										parseFloat(subtotalprice).toFixed(2));
							})
					$(".sub").click(
							function() {
								var p = $(this).parent().siblings(
										"div.pricebox").children().children()
										.text() * 1;
								var q = $(this).siblings("input.quantity")
										.val() * 1 - 1;
								var totalprice = $("#totalprice").text() * 1;
								if (q < 0) {
									$(this).siblings("input.quantity").val(0);
									$("#totalprice").text(totalprice);
								} else {
									$(this).siblings("input.quantity").val(q);
									var subtotalprice = totalprice - p;
									$("#totalprice").text(
											parseFloat(subtotalprice)
													.toFixed(2));
								}
							})
					$(".delete").click(
							function() {
								var q = $(this).parent().siblings(
										"div.quantitybox").children(
										"input.quantity").val() * 1;

								var p = $(this).parent().siblings(
										"div.pricebox").children().children()
										.text() * 1;
								var totalprice = $("#totalprice").text() * 1;
								var isbn = $(this).parent().siblings().find(
										"span.isbn").text();
								var userid = $("#saveid").text();
								var badge = $("#badge").text();
								$(this).parent().parent().empty();
								$("#totalprice").text(
										parseFloat(totalprice - p * q).toFixed(
												2));
								
								$.ajax({
									type : "get",
									url : "DeleteRecordServlet",
									data : {
										userid : userid,
										isbn : isbn,
										badge : badge
									},
									success : function(result) {

									}
								})
							})
					var quantity;//the number before inputing 
					$(".quantity").focus(function() {
						quantity = $(this).val() * 1;

					})
					$(".quantity").blur(
							function() {
								var p = $(this).parent().siblings(
										"div.pricebox").children().text() * 1;
								var blurq = $(this).val() * 1;
								var totalprice = $("#totalprice").text() * 1;
								var def = blurq - quantity;
								if (blurq < 0) {
									$(this).val(quantity);
									$("#totalprice").text(totalprice);
								} else {
									$("#totalprice").text(
											parseFloat(totalprice + p * def)
													.toFixed(2));
								}
							});
					//
					var count;
					$("#confirmproduct").click(function() {
						var isbnSet = new Array();
						var quantitySet = new Array();
						var books = new Array();
						$(".isbn").each(function() {
							isbnSet.push($(this).text());
						})
						$(".quantity").each(function() {
							quantitySet.push($(this).val());
						})
						for (var i = 0; i < isbnSet.length; i++) {
							books.push({
								"isbn" : isbnSet[i],
								"quantity" : quantitySet[i]
							});
						}
						$.ajax({
							type : "post",
							url : "CartServlet",
							data : {
								test : JSON.stringify(books)
							},
							dataType : 'json',
							success : function(result) {
								alert("result doesn't work!")

							}
						})

						$("#propanbody").slideUp(600, function() {
							$("#shippanel").slideDown(500);
						});
						$(this).addClass("hide");
						$("#cancelproduct").removeClass("hide");
						$("#timealert").removeClass("hide");
						count = setInterval("TimeOut();", 10000);
					})
					$("#cancelproduct").click(function() {
						clearInterval(count);
						$("#time").text("10")
						$("#timealert").addClass("hide");
						$("#propanbody").slideDown(600);
						$("#confirmproduct").removeClass("hide");
						$(this).addClass("hide");
						$.post( "CancelPayServlet?userid="+ <%=session.getAttribute("userid")%>);
					});
					function TimeOut() {
						var s = document.getElementById("time");
						if (s.innerHTML == 0) {
							window.location.href = 'CancelPayServlet?userid='
									+
				<%=session.getAttribute("userid")%>
					;
							return false;
						}
						s.innerHTML = s.innerHTML * 1 - 1;
					}
				</script>
			</div>

			<div id="shippanel" class="panel panel-primary"
				style="display: none;">
				<div class="panel-heading">
					<h1>Shipping Information</h1>
				</div>
				<div id="shippanelbody" class="panel-body">
					<div class="row">
						<div class="col-md-6">
							<label>Shipping Address</label> <input id="shipaddress"
								type="text" class="form-control" placeholder="Shipping Address">
						</div>
						<div class="col-md-3">
							<label>Apartment</label> <input id="shipapt" type="text"
								class="form-control" placeholder="Apartment">
						</div>
						<div class="col-md-3">
							<label>Zip Code</label> <input id="shipcode" type="text"
								class="form-control" placeholder="Zip Code">
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<div class="col-md-10"></div>
						<div class="col-md-2 text-center">
							<button id="confirmship" class="btn btn-success">Confirm</button>
							<button id="cancelship" class="btn btn-warning hide">Cancel</button>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$("#confirmship").click(function() {
						$("#shippanelbody").slideUp(600, function() {
							$("#paypanel").slideDown(500);
						})
						$(this).addClass("hide");
						$("#cancelship").removeClass("hide");

						$.ajax({
							type : "post",
							url : "CheckCartServlet",
							data : {
								userid : $("#saveid").text(),
								shipAdd : $("#shipaddress").val(),
								shipCode : $("#shipcode").val(),
								shipapt : $("#shipapt").val(),
							},
							success : function(result) {
								var text = $('#invoice').attr('action') + result;
								$('#invoice').attr('action',text);
							}
						})
					})
					$("#cancelship").click(function() {
						$("#shippanelbody").slideDown(600);
						$(this).addClass("hide");
						$("#confirmship").removeClass("hide");
					});
				</script>
			</div>

			<div id="paypanel" class="panel panel-primary" style="display: none;">
				<div class="panel-heading">
					<h1>Payment Information</h1>
				</div>
				<form id="invoice" method="post" action="InvoiceServlet?userid=<%=session.getAttribute("userid")%>&shipnum=">
				<div id="paypanelbody" class="panel-body">
					<div class="row">
						<div class="col-md-5">
							<label>Payment Address</label> <input id="payadd" type="text"
								class="form-control" name="payadd" placeholder="Payment Address">
						</div>
						<div class="col-md-2">
							<label>Apartment</label> <input id="payapt" type="text"
								class="form-control" name="payapt" placeholder="Apartment">
						</div>
						<div class="col-md-2">
							<label>Zip Code</label> <input id="paycode" type="text"
								class="form-control" name="paycode" placeholder="Zip Code">
						</div>
						<div class="col-md-3">
							<label>Phone Number</label> <input id="payphone" type="text"
								class="form-control" name="payphone" placeholder="Phone Number">
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row">
						<div class="col-md-10"></div>
						<div class="col-md-2">
							<button id="confirmpay" type="submit" class="btn btn-success"
								style="width: 100%">Pay</button>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
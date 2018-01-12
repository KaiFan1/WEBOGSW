<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="webo.bean.UserBean,java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="jquery/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>WeboGSW Managment System</title>
</head>
<body>
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
				<a class="navbar-brand">WeboGSW Admin 1.0</a>
			</div>
			<div id="loggin" style="float: right">
				<p class="navbar-text">
					Welcome&nbsp;<%=session.getAttribute("currentSessionUser")%></p>
				<a class="btn btn-primary" role="button" href="LogOutServlet"
					style="margin: 10px auto;">Log Out</a>
			</div>
		</div>
	</nav>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Import A New Book</h4>
				</div>
				<div class="modal-body row">
					<div class="col-md-12">
						<form class="form">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label text-center">ISBN number</label> <input
										type="text" class="form-control" id="bookisbn"
										placeholder="ISBN number" />
								</div>
								<div class="form-group">
									<label class="control-label text-center">Author First
										Name</label> <input type="text" id="bookauthfname" class="form-control"
										placeholder="Author First Name" />
								</div>
								<div class="form-group">
									<label class="control-label text-center">Book Price</label> <input
										type="text" id="bookprice" class="form-control"
										placeholder="book price" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label text-center">Publish Name</label> <input
										type="text" class="form-control" id="publishname"
										placeholder="Publish Name" />
								</div>
								<div class="form-group">
									<label class="control-label text-center">Author Middle
										Name</label> <input type="text" id="bookauthmname" class="form-control"
										placeholder="Author Middle Name" />
								</div>
								<div class="form-group">
									<label class=" control-label text-center">Book Stock</label> <input
										type="text" id="bookstock" class="form-control"
										placeholder="book stock" />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label text-center">Publish Date</label> <input
										type="text" id="bookyear" class="form-control"
										placeholder="Publish Date" />
								</div>
								<div class="form-group">
									<label class="control-label text-center">Author Last
										Name</label> <input type="text" id="bookauthlname" class="form-control"
										placeholder="Author Last Name" />
								</div>
								<div class="form-group">
									<label class=" control-label text-center">Book Category</label>
									<select id="bookcat" class="form-control">
										<option value="Art">Art</option>
										<option value="History">History</option>
										<option value="opel">Opel</option>
										<option value="audi">Audi</option>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label text-center">Book Name </label> <input
										type="text" id="bookname" class="form-control"
										placeholder="book name" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label text-center">The book's
										picture</label> <input type="file" id="bookpicref" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitNewBook" type="button" class="btn btn-primary">Submit</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row" style="margin-top: 54px;">
		<div class="col-md-2">
			<div class="btn-group-vertical" role="group" style="width: 100%;">
				<a class="btn btn-default" href="AdmTableServlet" onclick="clear()"
					role="button"><span class="glyphicon glyphicon-user"
					aria-hidden="true"></span> Users Table</a> <a class="btn btn-default"
					href="BookTableServlet" onclick="clear()" role="button"><span
					class="glyphicon glyphicon-book" aria-hidden="true"></span> Books
					Table</a>
			</div>
		</div>
		<div class="col-md-10">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="page-header">
						<h1>Dashboard</h1>
					</div>
					<div class="panel-body">
						<c:if test="${requestScope.display == 'inline-block' }">
							<button id="newbook" class="btn btn-success"
								style="display:${requestScope.display}" aria-haspopup="true"
								aria-expanded="false" data-toggle="modal" data-target="#myModal">Add
								a New Book</button>
						</c:if>
						<div style="margin-top: 30px;"></div>

						<table id="table" class="table table-bordered">
							<tr>
								<c:forEach items="${requestScope.titleforuser }" var="title">
									<th class="tabletitle text-center"><c:out value="${title}"></c:out></th>
								</c:forEach>
							</tr>
							<c:forEach items="${requestScope.tableforuser }" var="user">
								<tr>
									<td class="userid text-center"><c:out value="${user.id }"></c:out></td>
									<td class="userfir text-center"><c:out
											value="${user.firstName }"></c:out></td>
									<td class="usermid text-center"><c:out
											value="${user.middleName }"></c:out></td>
									<td class="userlas text-center"><c:out
											value="${user.lastName }"></c:out></td>
									<td class="userbir text-center"><c:out
											value="${user.birthday }"></c:out></td>
									<td class="useremail text-center"><c:out
											value="${user.email }"></c:out></td>
									<td class="username text-center"><c:out
											value="${user.username }"></c:out></td>
									<td class="userpass text-center"><c:out
											value="${user.password }"></c:out></td>
									<td class="text-center"><a
										class="edit btn btn-success btn-xs" role="button">Edit</a></td>
									<td class="text-center"><a
										class="delete btn btn-success btn-xs" role="button">Delete</a></td>
								</tr>
							</c:forEach>

							<tr>
								<c:forEach items="${requestScope.titleforbook }" var="title">
									<th class="tabletitle text-center"><c:out value="${title}"></c:out></th>
								</c:forEach>
							</tr>
							<c:forEach items="${requestScope.tableforbook }" var="book">
								<tr>
									<td class="text-center"><c:out value="${book.bookISBN }"></c:out></td>
									<td class="text-center"><c:out value="${book.bookName }"></c:out></td>
									<td class="text-center"><c:out value="${book.bookAuthFName }"></c:out></td>
									<td	class="text-center"><c:out value="${book.bookAuthMName }"></c:out></td>
									<td	class="text-center"><c:out value="${book.bookAuthLName }"></c:out></td>
									<td class="text-center"><c:out value="${book.bookYear }"></c:out></td>
                                    <td class="text-center"><c:out value="${book.publishName }"></c:out></td>
									<td class="text-center"><c:out value="${book.bookStock }"></c:out></td>
									<td class="text-center"><c:out value="${book.bookCat }"></c:out></td>
									<td class="text-center"><c:out value="${book.bookPrice }"></c:out></td>
									<td class="text-center"><a class="edit btn btn-success btn-xs">Edit</a></td>
									<td class="text-center"><a class="delete btn btn-success btn-xs">Delete</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#submitNewBook").click(
				function() {
					var sendData = {
						"bookISBN" : $("#bookisbn").val(),
						"publishID" : $("#publishid").val(),
						"bookName" : $("#bookname").val(),
						"bookAuthFName" : $("#bookauthfname").val(),
						"bookAuthMName" : $("#bookauthmname").val(),
						"bookAuthLName" : $("#bookauthlname").val(),
						"bookYear" : $("#bookyear").val(),
						"bookPicRef" : $("#bookpicref").val(),
						"bookStock" : $("#bookstock").val(),
						"bookCat" : $("#bookcat").val(),
						"bookPrice" : $("#bookprice").val()
					}
					$
							.ajax({
								type : "POST",
								url : "NewBookServlet",
								data : {
									ds : JSON.stringify(sendData)
								},
								dataType : "json",
								success : function(result) {
									if (result == true) {
										$("#myModal").css("display", "none");
										$("body").removeClass("modal-open")
												.css("padding-right", "0px");
										$(".modal").removeClass("in").css(
												"display", "none");
										$(".modal-backdrop").removeClass("in")
												.remove();
										window.location.reload();
									}
								}
							})
				});
	</script>
	<script type="text/javascript">
		var bookibsn = null;
		function clear() {
			$("#table").empty();
		}
		//to change the row into input element
		$("#table")
				.on(
						"click",
						".edit",
						function() {
							var cell = Array();
							var title = Array();
							var row = $(this).parent().siblings();
							for (var i = 0; i < row.length; i++) {
								cell[i] = $(this).parent().siblings().eq(i)
										.text();
								title[i] = $(".tabletitle").eq(i).text()
							}
							bookibsn = cell[0];
							for (var i = 0; i < (cell.length - 1); i++) {
								if (cell[i] == '') {
									cell[i] = "null";
								}
								row
										.eq(i)
										.replaceWith(
												"<td class=\"text-center\"><input class=\""
														+ title[i]
														+ " form-control\" type=\"text\" name=\""
														+ title[i]
														+ "\" value=\""
														+ cell[i]
														+ "\" style=\"width:100%\"/></td>");
							}
							$(this).parent().siblings().find("input.ID").attr(
									"disabled", "disabled");
							$(this)
									.parent()
									.next()
									.children("a.delete")
									.replaceWith(
											"<a class=\"cancel btn btn-success btn-xs\" >Cancel</a>");
							$(this)
									.replaceWith(
											"<a class=\"ok btn btn-success btn-xs\">Ok</a>");
						})

		$("#table")
				.on(
						"click",
						".ok",
						function() {
							var table = [];
							var row = $(this).parent().siblings();
							var title = $(".tabletitle").eq(0).text();
							var url;
							for (var i = 0; i < row.length - 1; i++) {
								table[i] = $(this).parent().siblings().find(
										"input").eq(i).val();
							}
							;
							if (title == "ID") {
								url = "ChangeUserTable";
							} else if (title == "ISBN") {
								url = "ChangeBookTableServlet"
								table.push(bookibsn);
							}
							$.ajax({
								type : "post",
								url : url,
								dataType : "json",
								data : {
									table : table
								},
							});
							var input = $(this).parent().siblings().children(
									"input");
							for (var i = 0; i < row.length - 1; i++) {
								input[i].replaceWith(table[i]);
							}
							;
							$(this)
									.parent()
									.next()
									.children("a.cancel")
									.replaceWith(
											"<a class=\"delete btn btn-success btn-xs\" >Delete</a>");
							$(this)
									.replaceWith(
											"<a class=\"edit btn btn-success btn-xs\" >Edit</a>");
						})

		$("#table")
				.on(
						"click",
						".cancel",
						function() {
							var table = [];
							var row = $(this).parent().siblings();
							var input = $(this).parent().siblings().children(
									"input")
							for (var i = 0; i < row.length - 1; i++) {
								table[i] = $(this).parent().siblings().find(
										"input").eq(i).val();
							}
							for (var i = 0; i < row.length - 1; i++) {
								input[i].replaceWith(table[i]);
							}
							$(this)
									.parent()
									.prev()
									.children("a.ok")
									.replaceWith(
											"<a class=\"edit btn btn-success btn-xs\" >Edit</a>")
							$(this)
									.replaceWith(
											"<a class=\"delete btn btn-success btn-xs\" >Delete</a>")
						})

		$("#table").on("click", ".delete", function() {
			var id = $(this).parent().siblings().eq(0).text();
			var title = $(".tabletitle").eq(0).text();
			var url;
			if (title == "ID") {
				url = "ChangeUserTable";
			} else if (title == "ISBN") {
				url = "ChangeBookTableServlet"
			}

			var r = confirm("Are you sure to delete this record?");
			if (r == true) {
				$.ajax({
					type : "get",
					url : url,
					dataType : "json",
					data : {
						id : id
					},
				});
				$(this).parent().parent().remove();
			} else {

			}
		})
	</script>
</body>
</html>
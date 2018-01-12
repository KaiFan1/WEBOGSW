<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript" src="jquery/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>WeboGSW</title>
</head>
<body>
	<div class="container" style="margin-top: 40px;margin-bottom: 40px;">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6 wrapper">
				<h1 class="title">Sign Up</h1>
				<form action="RegisterServlet" method="post">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" name="username" id="username" placeholder="Username">
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<label for="firstname">First Name:</label> <input type="text"
									class="form-control" name="firstName" id="firstname"
									placeholder="First Name">
							</div>
							<div class="col-md-4">
								<label for="middlename">Middle Name:</label> <input type="text"
									class="form-control" name="middleName" id="middlename"
									placeholder="Middle Name">
							</div>
							<div class="col-md-4">
								<label for="lastname">Last Name:</label> <input type="text"
									class="form-control" name="lastName" id="lastname"
									placeholder="Last Name">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="birthday">Birthday</label> <input type="text"
							class="form-control" name="birthday" id="birthday"
							placeholder="eg.1999-01-01">
					</div>
					<div class="form-group">
						<label for="email">Email</label> <input type="email"
							class="form-control" name="email" id="email"
							placeholder="eg.xxx@xxxx.xxx">
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" name="password" id="password"
							placeholder="password">
					</div>
					<div class="form-group">
						<label>Confirm Password</label><input type="password"
							class="form-control" placeholder="Confirm Password">
					</div>
					<div class="form-group button">
						<button type="submit" class="btn btn-default">Sign Up</button>
						<a class="text-right" style="display:block;" href="index.jsp">maybe later..</a>
					</div>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>
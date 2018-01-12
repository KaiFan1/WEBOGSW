<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="jquery/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- CSS for this webpage -->
<style type="text/css">
body{background-image:url(image/adm-bg.jpg); background-size:cover;}
#loginbox{background:transparent;background-color:rgba(255,255,255,0.15);padding-top: 30px;}
</style>
<title>WeboGSW</title>
</head>
<body>
	<div class="page-header">
		<h1 class="text-center text-default">Welcome to WeboGSW Managment System</h1>
	</div>

	<div class="container" style="margin-top: 10% ">
		<div class="row">
			<div class="col-md-3"></div>
			<div id="loginbox" class="col-md-6 img-rounded" >
				<form class="form-horizontal" action="LoginServlet">
					<div class="form-group">
						<label class="col-sm-2 control-label">username</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="username"
								placeholder="username">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="password"
								placeholder="Password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label><input type="checkbox"> Remember me</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-default" value="Sign in">
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-3"></div>

		</div>
	</div>
</body>
</html>

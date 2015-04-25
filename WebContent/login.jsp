<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log In here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
	body {
		background-color:#E6E6FF;
		color: #110022;
	}
	.form-signin{
		max-width: 330px;
		padding: 15px;
		margin: 0 auto;
	}
	.sr-only{
		position: absolute;
		width: 1px;
  		height: 1px;
  		padding: 0;
  		margin: -1px;
  		overflow: hidden;
  		clip: rect(0,0,0,0);
 	 	border: 0;
	}
</style>
</head>
<body>
	<div class="container">
		<form class="form-signin" action='Login'>
			<h2>Log In</h2>
			<div style="color: #FF0000;">${errorMessage}</div>
			<div class="form-group">
			<label for="username" class="sr-only">Username:</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="Username">

			<label for="pwd" class="sr-only">Password:</label>
			<input type="password" class="form-control" id="pwd" name="password" placeholder="Password">
			</div>
			<div class="form-group">
				<input type="radio" name="type" value="user" checked>&nbsp;User
				<input type="radio" name="type" value="admin">&nbsp;Admin
			</div>	
				
			<div class="form-group">
				<button class="btn btn-success" name="action" value="login">Login</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn btn-primary" href="register.jsp">Create account</a>
			</div>
		</form>
	</div>
</body>
</html>